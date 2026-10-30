package com.user.userinfo.learntest;

import java.util.concurrent.*;

public class NewTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello, World!");

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        System.out.println("Current Thread: " + Thread.currentThread().getName());
        Callable<Integer> task = () -> {
            System.out.println("Thread sleeping for 5 seconds");
            Thread.sleep(5000);
            return 10;
        };
        Future<Integer> future = executorService.submit(task);
        System.out.println("Future Result: " + future.get());

    }

}
