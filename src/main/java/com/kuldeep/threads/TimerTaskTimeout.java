package com.kuldeep.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimerTaskTimeout {
    public static void main(String[] args)
    {
        String response = apiCallController();
        System.out.println(response);
    }

    public static String apiCallController() {
        System.out.println("creepy...\n");

        String response = "Call aborted...";

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Callable<String> r = () -> {
            try {
                // To mimick the actual call
                TimeUnit.SECONDS.sleep(5);
                System.out.println("call successful...");
                return "Some response";
            } catch (InterruptedException e) {
                System.out.println("Timeout reached, aborting... (This is where I want everything to stop without killing JVM/Tomcat)");
                throw e;
            }
        };

        Future<String> task = executor.submit(r);

        try
        {
            System.out.println("Start http/SSH stuff...");

            response = task.get(3, TimeUnit.SECONDS);

            System.out.println("End http/SSH stuff...");

            response = "Call successful...";

        }
        catch(InterruptedException | TimeoutException e)
        {
            // cancelling a task, either it was interrupted (sleep call can be interrupted) or its timeout
            task.cancel(true);
        }catch (ExecutionException e) {
            //Something went wrong horribly
            e.printStackTrace();
        }

        System.out.println("\npasta...");

        // Need to shutdown executor (think of it is master thread here)
        // You may want to control this behaviour outside of this function call
        executor.shutdown();

        return "\n" + response;
    }
}
