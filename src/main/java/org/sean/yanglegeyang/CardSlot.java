package org.sean.yanglegeyang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.sean.yanglegeyang.Event.UPDATE;

public class CardSlot {
    private List<Card> slot = new ArrayList<>();
    private int size;

    private EventPublisher eventPublisher;

    public CardSlot(int size, EventPublisher eventPublisher) {
        this.size = size;
        this.eventPublisher = eventPublisher;
    }

    public void add(Card card) {
        slot.add(card);
        Collections.sort(slot, new Comparator<Card>() {

            @Override
            public int compare(Card o1, Card o2) {
                return o1.getType().hashCode() - o2.getType().hashCode();
            }
        });

        List<Card> remove = new ArrayList<>();
        int index = 0;
        String type = "";
        for (int i = 0; i < slot.size(); i++) {
            Card item = slot.get(i);
            if (!item.getType().equals(type)) {
                index = 1;
                type = item.getType();
            } else {
                index++;
                if (index >= 3) {
                    remove.add(slot.get(i));
                    remove.add(slot.get(i - 1));
                    remove.add(slot.get(i - 2));
                    index = 0;
                }
            }
        }
        if (remove.size() > 0) {
            remove.forEach(item -> {
                slot.remove(item);
            });
        }

        if (slot.size() >= size) {
            eventPublisher.publishEvent(Event.GAME_OVER);
        }else{
            eventPublisher.publishEvent(UPDATE);
        }
    }

    public List<Card> getSlot(){
        return slot;
    }
}
