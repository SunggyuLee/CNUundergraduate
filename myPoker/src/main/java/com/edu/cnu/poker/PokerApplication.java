package com.edu.cnu.poker;

import java.util.*;

/**
 * Created by cse on 2017-04-17.
 * CARD - rank, suit
 * DECK
 * HAND
 * EVALUATOR
 * PLAYER
 * GAME
 */
public class PokerApplication {

    public void run() {
        System.out.println("Hello Poker");
        Deck currentDeck = new Deck(1);
        Hand myHand = new Hand(currentDeck, PokerType.SEVEN);
        Hand yourHand = new Hand(currentDeck, PokerType.FIVE);

        Player player1 = new Player(myHand);
        Player player2 = new Player(yourHand);

        player1.getHand().showMyCardList();
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("버리고 싶은 카드를 입력해주세요 (앞에서 부터 1번카드)");
            int input = scan.nextInt();
            try {
                if (!player1.getHand().removeCard(input)) {
                    break;
                }
            } catch (Exception e) {
                continue;
            }
            player1.getHand().showMyCardList();
        }
        player1.getHand().showMyCardList();
        player2.getHand().showMyCardList();

        Rule myResult = new Evaluator().evaluate(player1.getHand().getMyCardList());
        Rule yourResult = new Evaluator().evaluate(player2.getHand().getMyCardList());

        System.out.println(myResult.getPriority());
        System.out.println(yourResult.getPriority());


        if (!this.compareTo(myResult, yourResult)) {
            Map<Integer, Integer> tempMap1 = new HashMap<Integer, Integer>();
            Map<Integer, Integer> tempMap2 = new HashMap<Integer, Integer>();
            List<Card> myCards = myHand.getMyCardList();
            List<Card> yourCards = yourHand.getMyCardList();

            for (Card card : myCards) {
                if (tempMap1.containsKey(card.getRank())) {
                    Integer count = tempMap1.get(card.getRank());
                    count = new Integer(count.intValue() + 1);
                    tempMap1.put(card.getRank(), count);
                } else {
                        tempMap1.put(card.getRank(), new Integer(1));
                }
            }
            for (Card card : yourCards) {
                if (tempMap2.containsKey(card.getRank())) {
                    Integer count = tempMap2.get(card.getRank());
                    count = new Integer(count.intValue() + 1);
                    tempMap2.put(card.getRank(), count);
                } else {
                    tempMap2.put(card.getRank(), new Integer(1));
                }
            }
            int myTop = 0;
            Suit myTopSuit = null;
            int yourTop = 0;
            Suit yourTopSuit = null;

            for(Card card : myCards) {
                if(tempMap1.get(card.getRank()) == 1 && card.getRank() > myTop) {
                    myTop = card.getRank();
                    myTopSuit = card.getSuit();
                }
            }
            for(Card card : yourCards) {
                if(tempMap2.get(card.getRank()) == 1 && card.getRank() > yourTop) {
                    yourTop = card.getRank();
                    yourTopSuit = card.getSuit();
                }
            }
            if(myTop > yourTop)
                System.out.println(myTopSuit+" "+myTop + " Top 승리!");
            else if(myTop < yourTop)
                System.out.println(myTopSuit+" "+myTop + " Top 패배");
            else{
                if(myTopSuit.ordinal() > yourTopSuit.ordinal())
                    System.out.println(myTopSuit+" "+myTop + " Top 승리!");
                else if(myTopSuit.ordinal() < yourTopSuit.ordinal())
                    System.out.println(myTopSuit+" "+myTop + " Top 패배");
            }
        }
    }

    public boolean compareTo(Rule thisRule, Rule otherRule) {
        if (thisRule.getPriorityNum() > otherRule.getPriorityNum()) {
            System.out.println(thisRule.getFirst() + " " + thisRule.getPriority() + " 승리!");
            return true;
        } else if (thisRule.getPriorityNum() < otherRule.getPriorityNum()) {
            System.out.println(thisRule.getPriority() + " 패배!");
            return true;
        } else {
            if (thisRule.getFirst() - otherRule.getFirst() > 0) {
                System.out.println(thisRule.getFirst() + " " + thisRule.getPriority() + " 승리!");
                return true;
            } else if (thisRule.getFirst() - otherRule.getFirst() < 0) {
                System.out.println(thisRule.getPriority() + " 패배!");
                return true;
            } else {
                if (thisRule.getSecond() - otherRule.getSecond() > 0) {
                    System.out.println(thisRule.getFirst() + " " + thisRule.getPriority() + " 승리!");
                    return true;
                } else if (thisRule.getSecond() - otherRule.getSecond() < 0) {
                    System.out.println(thisRule.getPriority() + " 패배!");
                    return true;
                } else if (thisRule.getSuit() == null)
                    return false;
                else if (thisRule.getSuit().ordinal() > otherRule.getSuit().ordinal()) {
                    System.out.println(thisRule.getSuit() + " " + thisRule.getPriority() + " 승리!");
                    return true;
                } else {
                    System.out.println(thisRule.getPriority() + " 패배!");
                    return true;
                }
            }
        }
    }

}
