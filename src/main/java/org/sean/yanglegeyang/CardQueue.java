package org.sean.yanglegeyang;

import java.util.LinkedList;
import java.util.Queue;

public class CardQueue {
    private Queue<Card> queue = new LinkedList<>();

    public void put(Card card) {
        this.queue.add(card);
    }

    public Card peek() {
        return this.queue.peek();
    }

    public Card poll() {
        return this.queue.poll();
    }

    public int size(){
        return this.queue.size();
    }
}
