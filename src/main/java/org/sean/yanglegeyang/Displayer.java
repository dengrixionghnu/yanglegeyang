package org.sean.yanglegeyang;

import java.util.List;
import java.util.Objects;

public class Displayer implements EventListener {

    private CardRepository cardRepository;
    private CardSlot slot;
    private int x;
    private int y;

    public Displayer(CardRepository cardRepository, CardSlot slot, int x, int y) {
        this.cardRepository = cardRepository;
        this.slot = slot;
        this.x = x;
        this.y = y;
    }

    public void display() {
        Card[][] cards = cardRepository.visibleCards();
        System.out.println("您的卡牌");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Card card = cards[i][j];
                if(Objects.nonNull(card)){
                    System.out.print(card.getType()+"("+i+","+j+")"+"    ");
                }else{
                    System.out.print("           ");
                }
            }
            System.out.println();
        }
        System.out.println("您的卡槽");
        List<Card> cardList = slot.getSlot();
        if(cardList.size()>0){
            cardList.forEach(card -> {
                System.out.print(card.getType()+"   ");
            });
        }
        System.out.println();
    }

    @Override
    public void onEvent(Event event) {
        if (Event.COMPLETED.equals(event)) {
            System.out.println("恭喜你，挑战成功");
            System.exit(0);
        } else if (Event.GAME_OVER.equals(event)) {
            System.out.println("对不起，挑战失败，卡槽已经满了，下次加油哦");
            System.exit(0);
        } else {
            display();
        }
    }
}
