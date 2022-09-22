package org.sean.yanglegeyang;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {

    public void run(){
        int x=5;
        int y=5;
        int size = 15;
        int slotSize = 7;
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
        EventPublisher eventPublisher = new EventPublisher(eventQueue);
        EventRegister eventRegister = new EventRegister(eventQueue);
        CardRepository cardRepository = new CardRepository(x,y,size,eventPublisher);
        cardRepository.init();
        CardSlot slot = new CardSlot(slotSize,eventPublisher);
        Shower shower = new Shower(cardRepository,slot,x,y);
        eventRegister.registerListener(shower);
        eventPublisher.publishEvent(Event.UPDATE);
        Player player = new Player(cardRepository,slot);
        while (true){
            player.play();
        }
    }
}
