package com.diattack.emailsreceiver;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.var;

public class UsingAtomicVariables {

    /*
     * A Counter using AtomicInteger
     */
    static class AtomicCounter {
        private AtomicInteger atomicInteger = new AtomicInteger(0);

        public void increment() {
            atomicInteger.incrementAndGet();
        }

        public void decrement() {
            atomicInteger.decrementAndGet();
        }

        public int get() {
            return atomicInteger.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var counter = new AtomicCounter();
        var cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100_000; i++) {
            cachedThreadPool.execute(() -> counter.increment());
        }
        cachedThreadPool.shutdown();
        cachedThreadPool.awaitTermination(5000, TimeUnit.SECONDS);
        System.out.println("Actual result is: " + counter.get());
    }

}
