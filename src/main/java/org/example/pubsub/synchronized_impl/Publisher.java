package org.example.pubsub.synchronized_impl;

import java.util.Random;

public class Publisher implements Runnable{
    private final MessageBroker messageBroker;

    public Publisher(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            String message = "Message " + i;
            synchronized (messageBroker) {
                messageBroker.publish(message);
                System.out.println("Published: " + message + " " + Thread.currentThread().getName());
                System.out.println("Queue size: " + messageBroker.getMessages().size());
            }
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
