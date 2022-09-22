package org.sean.yanglegeyang;

import java.util.Scanner;

public class Player {

    private CardRepository repository;
    private CardSlot slot;
    private Scanner scanner= new Scanner(System.in);


    public Player(CardRepository repository, CardSlot slot) {
        this.repository = repository;
        this.slot = slot;
    }

    public void play(){
        System.out.println("请输入:");
        String input =  scanner.next();
        if(input!=null && input.length()>0){
            String[] part = input.split(",");
            slot.add(repository.pick(Integer.valueOf(part[0]),Integer.valueOf(part[1])));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
