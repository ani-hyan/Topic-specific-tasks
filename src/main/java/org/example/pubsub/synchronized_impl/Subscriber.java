package org.example.pubsub.synchronized_impl;

import java.util.Random;

public class Subscriber implements Runnable{
    private final MessageBroker messageBroker;

    public Subscriber(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                synchronized (messageBroker) {
                    String message = messageBroker.consume();
                    System.out.println("Consumed: " + message + " " + Thread.currentThread().getName());
                    System.out.println("Queue size: " + messageBroker.getMessages().size());
                }
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
