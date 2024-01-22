package org.example.pubsub.blockingqueue_impl;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Publisher implements Runnable {
    private BlockingQueue<String> messageQueue;

    public Publisher(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                synchronized (messageQueue) {
                    messageQueue.put("Message: " + i);
                    System.out.println("Published: Message: " + i + " " + Thread.currentThread().getName());
                    System.out.println("Queue size: " + messageQueue.size());
                    messageQueue.notify();
                    Thread.sleep(new Random().nextInt(2000));
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
