package com.edu.cnu.poker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cse on 2017-04-17.
 */
public class Hand {

    private Deck deck;
    private PokerType pokerType;
    private List<Card> cardList;
    private int removeCount = 2;

    public Hand(Deck deck, PokerType pokerType) {
        this.deck = deck;
        this.pokerType = pokerType;
        cardList = new ArrayList<Card>();
        for (int i = 0; i < pokerType.getNumberOfCard(); i++) {
            cardList.add(deck.drawCard());
        }
    }

    public boolean removeCard(int num) {
        if (removeCount != 0) {
            cardList.remove(num - 1);
            removeCount--;
            if (removeCount == 0)
                return false;
            return true;
        }
        return false;
    }

    public int getTotalCard() {
        return cardList.size();
    }

    public List<Card> getMyCardList() {
        return this.cardList;
    }

    public void showMyCardList() {
        int i = 1;
        Iterator<Card> tempCardList = cardList.iterator();
        while (tempCardList.hasNext()) {
            Card tempCard = tempCardList.next();
            System.out.println(i++ + ". [모양 : " + tempCard.getSuit() + " 숫자 : " + tempCard.getRank() + ']');
        }
        System.out.println();
    }
}
