package com.user.userinfo.learntest;

import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorClass {


    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Callable<Integer> callable = () -> {
            System.out.println("Callable running on " + Thread.currentThread().getName());
            return 42;
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);

//        for (int i = 0; i < 10; i++) {
//            int taskId = i;
//
//            executor.submit(() -> {
//
//                System.out.println(
//                        "Task " + taskId +
//                                " running on " +
//                                Thread.currentThread().getName()
//                );
//
//                for (int j = 0; j < 1000; j++) {
//                    count.incrementAndGet();
//                }
//
//            });
//        }

        Future<Integer> future= executor.submit(callable);
        try{
            System.out.println(future.get());
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final Count = " + count.get());

//        for (int i = 0; i < 10; i++) {
//            int taskId=i;
//
//            executor.submit(()->{
//                System.out.println(
//                        "Task " + taskId +
//                                " running on " +
//                                Thread.currentThread().getName()
//                );
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        executor.shutdown();


    }

}
