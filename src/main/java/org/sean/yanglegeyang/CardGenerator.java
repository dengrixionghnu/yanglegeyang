package org.sean.yanglegeyang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardGenerator {

    Random random = new Random();
    private String[] typeDict = new String[]{"a","b","c","d","e","f","g"};
    private int typeNum = typeDict.length;

    public List<Card> generateCard(int size){
        List<Card> cards = new ArrayList<>();
        for(int i=0;i<size;i++){
            String type = typeDict[random.nextInt(typeNum)];
            cards.add(new Card(type));
            cards.add(new Card(type));
            cards.add(new Card(type));
        }
        Collections.shuffle(cards);
        return cards;
    }
}
