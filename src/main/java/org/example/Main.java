package org.example;

import org.example.pubsub.average.RandomNumbersAverage;
import org.example.pubsub.blockingqueue_impl.Publisher;
import org.example.pubsub.blockingqueue_impl.Subscriber;
import org.example.pubsub.pseudorandom.LockFreePseudoRandom;


import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;


public class Main {
    public static void main(String[] args)  {
//        BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(5);
//
//        Publisher p = new Publisher(messageQueue);
//        Subscriber s = new Subscriber(messageQueue);
//
//        new Thread(p).start();
//        new Thread(s).start();
//        Thread publisher2 = new Thread(new Publisher(messageQueue));
//        Thread subscriber2 = new Thread(new Subscriber(messageQueue));
//
//        publisher2.start();
//        subscriber2.start();

//        LockFreePseudoRandom random = new LockFreePseudoRandom();
//
//
//        for (int i = 1; i <= 10; i++) {
//            int randomNumber = random.nextInt(6);
//            System.out.println("Random Number " + i + ": " + randomNumber);
//        }


        System.out.println(RandomNumbersAverage.average());

    }


}