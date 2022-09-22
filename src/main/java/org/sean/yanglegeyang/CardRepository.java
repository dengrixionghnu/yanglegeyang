package org.sean.yanglegeyang;
import java.util.List;
import java.util.Objects;

public class CardRepository {
    private int x;
    private int y;
    private int size;
    public CardQueue [][] cardArray;

    private EventPublisher publisher;

    public CardRepository(int x, int y, int size,EventPublisher publisher) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.publisher= publisher;
    }

    public void init(){
        cardArray = new CardQueue[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                cardArray[i][j] =  new CardQueue();
            }
        }
        List<Card> cars = new CardGenerator().generateCard(size);
        int index_x =0;
        int index_y =0;
        for(int i =0;i<cars.size();i++){
            if(index_y>=y){
                index_y = 0;
                index_x = index_x+1;
                if(index_x>=x){
                    index_x = 0;
                }
            }
            cardArray[index_x][index_y].put(cars.get(i));
            index_y = index_y+1;
        }
    }

    public Card[][] visibleCards(){
        Card[][] visibleCards = new Card[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                visibleCards[i][j]=cardArray[i][j].peek();
            }
        }
        return visibleCards;
    }

    public Card pick(int x,int y){
        Card card = cardArray[x][y].poll();
        if(isCompleted()){
            publisher.publishEvent(Event.COMPLETED);
        }

        return card;
    }

    private boolean isCompleted(){
        boolean completed = true;
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(Objects.nonNull(cardArray[i][j].peek())){
                    completed=false;
                    break;
                }
            }
            if(!completed){
                break;
            }
        }
        return completed;
    }
}
