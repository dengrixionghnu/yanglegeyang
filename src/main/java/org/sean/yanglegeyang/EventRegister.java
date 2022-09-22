package org.sean.yanglegeyang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

public class EventRegister {

    private BlockingQueue<Event> eventQueue;
    private List<EventListener> eventListenerList = new ArrayList<>();

    public EventRegister(BlockingQueue<Event> eventQueue) {
        this.eventQueue = eventQueue;
        initListener();
    }

    public void registerListener(EventListener listener){
        eventListenerList.add(listener);
    }

    private void initListener(){
        Executors.newFixedThreadPool(1).submit(()->{
            while(true){
                try{
                    Event event = eventQueue.take();
                    if(Objects.isNull(event)){
                        continue;
                    }
                    eventListenerList.forEach(listener->{
                        listener.onEvent(event);
                    });

                }catch (Exception e){

                }
            }
        });
    }



}
