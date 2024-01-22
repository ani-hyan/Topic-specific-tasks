package org.example.pubsub.synchronized_impl;

import java.util.LinkedList;
import java.util.Queue;


public class MessageBroker {
    private final Queue<String> messages = new LinkedList<>();
    private volatile boolean isMessageAvailable = false;

    public synchronized void publish(String message) {
        messages.add(message);
        isMessageAvailable = true;
        notify();
    }

    public synchronized String consume() throws InterruptedException {
        while (!isMessageAvailable) {
            wait();
        }
        String message = messages.poll();
        if (messages.isEmpty()) {
            isMessageAvailable = false;
        }
        return message;
    }

    public Queue<String> getMessages() {
        return messages;
    }
}
