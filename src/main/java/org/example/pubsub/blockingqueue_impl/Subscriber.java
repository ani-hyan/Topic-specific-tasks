package org.example.pubsub.blockingqueue_impl;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Subscriber implements Runnable {
    private final BlockingQueue<String> messageQueue;

    public Subscriber(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (messageQueue) {
                    if(messageQueue.isEmpty())
                        messageQueue.wait();
                    String message = messageQueue.take();
                    System.out.println("Received: " + message + " " + Thread.currentThread().getName());
                    System.out.println("Queue size: " + messageQueue.size());
                }
                Thread.sleep(new Random().nextInt(2000));

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
