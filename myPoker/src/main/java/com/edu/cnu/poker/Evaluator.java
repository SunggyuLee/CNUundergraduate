package com.edu.cnu.poker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {

    Rule onePairRule;
    Rule twoPairRule;
    Rule tripleRule;
    Rule flushRule;

    public Rule evaluate(List<Card> cardList) {

        Map<Suit, Integer> tempMap1 = new HashMap<Suit, Integer>();
        Map<Integer, Integer> tempMap2 = new HashMap<Integer, Integer>();

        for (Card card : cardList) {
            if (tempMap1.containsKey(card.getSuit())) {
                Integer count = tempMap1.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap1.put(card.getSuit(), count);
            } else {
                tempMap1.put(card.getSuit(), new Integer(1));
            }
        }
        for (Card card : cardList) {
            if (tempMap2.containsKey(card.getRank())) {
                Integer count = tempMap2.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                tempMap2.put(card.getRank(), count);
            } else {
                tempMap2.put(card.getRank(), new Integer(1));
            }
        }
        boolean flush = false;
        for (Suit key : tempMap1.keySet()) {
            if (tempMap1.get(key) == 5) {
                flushRule = new Rule(EnumRule.FLUSH, key);
                flush = true;
            }
        }
        boolean triple = false;
        for (int key : tempMap2.keySet()) {
            if (tempMap2.get(key) == 3) {
                if(key == 1)
                    key = 14;
                tripleRule = new Rule(EnumRule.TRIPLE, key);
                triple = true;
            }
        }

        int pairCount = 0;
        for (int key : tempMap2.keySet()) {
            if (tempMap2.get(key) == 4) {
                if(key == 1)
                    key = 14;
                Rule fourCardRule = new Rule(EnumRule.FOUR_CARD, key);
                return fourCardRule;
            }

            if (tempMap2.get(key) == 2) {
                if(key == 1)
                    key = 14;
                switch (pairCount) {
                    case 0:
                        onePairRule = new Rule(EnumRule.ONE_PAIR, key);
                        twoPairRule = new Rule(EnumRule.TWO_PAIR, key);
                        break;
                    case 1:
                        if (twoPairRule.getFirst() < key) {
                            twoPairRule.setSecond(twoPairRule.getFirst());
                            twoPairRule.setFirst(key);
                            break;
                        } else
                            twoPairRule.setSecond(key);
                        break;
                }
                if (triple) {
                    Rule fullHouseRule = new Rule(EnumRule.FULL_HOUSE, tripleRule.getFirst());
                    fullHouseRule.setSecond(key);
                    return fullHouseRule;
                }
                pairCount++;
            }
        }
        if (triple)
            return tripleRule;
        if (!flush) {
            switch (pairCount) {
                case 0:
                    break;
                case 1:
                    return onePairRule;
                case 2:
                    return twoPairRule;
            }
        }

        Collections.sort(cardList);
        boolean straight = false;
        for (int i = 1; i < cardList.size(); i++) {
            if (i == cardList.size() - 1) {
                straight = true;
                break;
            }
            if (cardList.get(i).compareTo(cardList.get(i + 1)) != -1)
                break;
        }
        if (cardList.get(0).getRank() == 1 && straight) {
            if (cardList.get(1).getRank() == 2 && flush) {
                Rule backStraightFlushRule = new Rule(EnumRule.BACK_STRAIGHT_FLUSH, cardList.get(0).getSuit());
                return backStraightFlushRule;
            } else if (cardList.get(1).getRank() == 2) {
                Rule backStraightRule = new Rule(EnumRule.BACK_STRAIGHT, cardList.get(0).getSuit());
                return backStraightRule;
            } else if (cardList.get(cardList.size() - 1).getRank() == 13 && flush) {
                Rule royalStraightRule = new Rule(EnumRule.ROYAL_STRAIGHT_FLUSH, cardList.get(0).getSuit());
                return royalStraightRule;
            } else if (cardList.get(cardList.size() - 1).getRank() == 13) {
                Rule mountainRule = new Rule(EnumRule.MOUNTAIN, cardList.get(0).getSuit());
                return mountainRule;
            }
        } else if (straight)
            if (cardList.get(0).compareTo(cardList.get(1)) == -1 && flush) {
                Rule straightFlushRule = new Rule(EnumRule.STRAIGHT_FLUSH, cardList.get(cardList.size() - 1).getRank());
                straightFlushRule.setSecond(cardList.get(0).getSuit());
                return straightFlushRule;
            } else if (cardList.get(0).compareTo(cardList.get(1)) == -1) {
                Rule straightRule = new Rule(EnumRule.STRAIGHT, cardList.get(cardList.size() - 1).getRank());
                return straightRule;
            }

        if (flush)
            return flushRule;

        Rule nothingRule = new Rule(EnumRule.NOTHING);
        return nothingRule;
    }
}