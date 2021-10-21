package com.kuldeep.design.callcenter;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class CallCenter implements CallEscalationObserver {
    private final List<Employee> employees;
    private final BlockingQueue<Call> queue = new ArrayBlockingQueue<>(3);
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private CallCenter(List<Employee> employees) {
        this.employees = employees;
        registerAsObserver(this.employees);
    }

    private void registerAsObserver(List<Employee> employees) {
        employees.forEach(e -> e.register(this));
    }

    public static CallCenter of(List<Employee> employees) {
        return new CallCenter(employees);
    }

    public CompletableFuture<Void> handleCalls(List<Call> calls, int timeout, TimeUnit timeUnit) {
        CompletableFuture.runAsync(() -> calls.forEach(this::dispatch))
                .orTimeout(timeout, timeUnit);

        return CompletableFuture.runAsync(new CallListener(queue, employees, executorService))
                .orTimeout(timeout, timeUnit);
    }

    public void tearDown() {
        executorService.shutdown();
    }

    @Override
    public void notifyEscalation(Call call) {
        dispatch(call);
    }

    private void dispatch(Call call) {
        boolean isQueued = queue.offer(call);
        while (!isQueued) {
            isQueued = queue.offer(call);
        }
    }

    static class CallListener implements Runnable {
        private final BlockingQueue<Call> queue;
        private final ExecutorService executorService;
        private final List<Employee> employees;

        CallListener(BlockingQueue<Call> queue, List<Employee> employees, ExecutorService executorService) {
            this.queue = queue;
            this.employees = employees;
            this.executorService = executorService;
        }


        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    List<Employee> operators = employees.stream()
                                                        .filter(e -> e.getRole() == Role.OPERATOR)
                                                        .collect(toList());
                    assignCalls(operators);

                    List<Employee> supervisors = employees.stream()
                                                          .filter(e -> e.getRole() == Role.SUPERVISOR)
                                                          .collect(toList());
                    assignCalls(supervisors);

                    List<Employee> directors = employees.stream()
                                                        .filter(e -> e.getRole() == Role.DIRECTOR)
                                                        .collect(toList());
                    assignCalls(directors);

                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        private void assignCalls(List<Employee> employees) throws InterruptedException {
            for (Employee e : employees) {
                executorService.submit(new CallDurationHandler(e, queue.take()));
            }
        }
    }

    static class CallDurationHandler implements Callable<Void> {
        private final Employee employee;
        private final Call call;

        CallDurationHandler(Employee employee, Call call) {
            this.employee = employee;
            this.call = call;
        }

        @Override
        public Void call() throws Exception {
            Duration duration = call.duration();
            int i = 0;

            if (!employee.assign(call)) return null;

            while (duration.minus(Duration.ofSeconds(i)).getSeconds() > 0) {
                i++;
                TimeUnit.SECONDS.sleep(1);
            }

            employee.free();

            return null;
        }
    }
}
