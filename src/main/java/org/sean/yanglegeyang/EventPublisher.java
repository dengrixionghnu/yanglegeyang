package org.sean.yanglegeyang;

import java.util.concurrent.BlockingQueue;

public class EventPublisher {
    private BlockingQueue<Event> eventQueue;

    public EventPublisher(BlockingQueue<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public void publishEvent(Event event){
        try {
            this.eventQueue.put(event);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
