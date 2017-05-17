package com.edu.cnu.poker;

/**
 * Created by 백승진 on 2017-04-24.
 */
public enum EnumRule {

    NOTHING(0),
    ONE_PAIR(1),
    TWO_PAIR(2),
    TRIPLE(3),
    STRAIGHT(4),
    BACK_STRAIGHT(5),
    MOUNTAIN(6),
    FLUSH(7),
    FULL_HOUSE(8),
    FOUR_CARD(9),
    STRAIGHT_FLUSH(10),
    BACK_STRAIGHT_FLUSH(11),
    ROYAL_STRAIGHT_FLUSH(12);


    private  int priority;

    EnumRule(int priority) {
        this.priority = priority;
    }

}