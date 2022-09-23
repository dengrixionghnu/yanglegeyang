package org.sean.yanglegeyang;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {

    public void run(){
        int x=3;
        int y=4;
        int size = 15;
        int slotSize = 5;
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
        EventPublisher eventPublisher = new EventPublisher(eventQueue);
        EventRegister eventRegister = new EventRegister(eventQueue);
        CardRepository cardRepository = new CardRepository(x,y,size,eventPublisher);
        cardRepository.init();
        CardSlot slot = new CardSlot(slotSize,eventPublisher);
        Displayer displayer = new Displayer(cardRepository,slot,x,y);
        eventRegister.registerListener(displayer);
        eventPublisher.publishEvent(Event.UPDATE);
        Player player = new Player(cardRepository,slot);
        while (true){
            player.play();
        }
    }
}
