package com.edu.cnu.poker;

/**
 * Created by 백승진 on 2017-04-27.
 */
public class Player {
    private Hand hand;
    private static int money;
    private static int level;
    private boolean life;

    Player(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Player.money = money;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Player.level = level;
    }
}
