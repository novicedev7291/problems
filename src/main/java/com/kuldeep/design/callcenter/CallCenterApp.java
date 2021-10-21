package com.kuldeep.design.callcenter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class CallCenterApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Employee operator = new Operator("Kuldeep");
        Employee supervisor = new SuperVisor("Aman");
        Employee director = new Director("Sandeep");

        List<Call> calls = List.of(
                Call.of(1),
                Call.of(2),
                Call.of(3),
                Call.of(4),
                Call.of(5),
                Call.of(6)
        );
        CallCenter callCenter = CallCenter.of(List.of(operator, supervisor, director));

        int maxTime = 30;
        CompletableFuture<Void> onGoingTask = callCenter.handleCalls(calls, maxTime, TimeUnit.SECONDS);

        callCenter.tearDown();
    }
}
