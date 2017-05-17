package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Created by noble on 2017-04-28.
 */
public class ApplicationTest {
    @Test
    public void 족보가다를때두Rule을비교(){
        PokerApplication app = new PokerApplication();
        Rule myRule = new Rule(EnumRule.FLUSH);
        Rule yourRule = new Rule(EnumRule.FULL_HOUSE);
        boolean result = app.compareTo(myRule,yourRule);
        assertThat(result, is(true));
    }
    @Test
    public void 족보가같고_퍼스트랭크가다른_두Rule을비교(){
        PokerApplication app = new PokerApplication();
        Rule myRule = new Rule(EnumRule.FLUSH, 13);
        Rule yourRule = new Rule(EnumRule.FLUSH, 12);
        boolean result = app.compareTo(myRule,yourRule);
        assertThat(result, is(true));
    }
    @Test
    public void 족보와퍼스트랭크가같고_Suit가_다른_두Rule을비교(){
        PokerApplication app = new PokerApplication();
        Rule myRule = new Rule(EnumRule.FLUSH, 13);
        myRule.setSuit(Suit.CLUBS);
        Rule yourRule = new Rule(EnumRule.FLUSH, 13);
        yourRule.setSuit((Suit.SPADES));
        boolean result = app.compareTo(myRule,yourRule);
        assertThat(result, is(true));
    }
    @Test
    public void getSecond를비교() {
        PokerApplication app = new PokerApplication();
        Evaluator evaluator1 = new Evaluator();
        Evaluator evaluator2 = new Evaluator();
        List<Card> cardList1 = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(1,Suit.DIAMONDS),
                new Card(2,Suit.DIAMONDS),
                new Card(7,Suit.HEARTS),
                new Card(7,Suit.SPADES)
        );
        List<Card> cardList2 = Arrays.asList(
                new Card(1,Suit.HEARTS),
                new Card(1,Suit.SPADES),
                new Card(2,Suit.HEARTS),
                new Card(7,Suit.DIAMONDS),
                new Card(7,Suit.CLUBS)
        );
        Rule myRule = evaluator1.evaluate(cardList1);
        Rule yourRule = evaluator2.evaluate(cardList2);
        boolean result = app.compareTo(myRule,yourRule);
        assertThat(result, is(false));

    }

    @Test
    public void 스티플을_비교() {
        PokerApplication app = new PokerApplication();
        Evaluator evaluator1 = new Evaluator();
        Evaluator evaluator2 = new Evaluator();
        List<Card> cardList1 = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(2,Suit.CLUBS),
                new Card(3,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(5,Suit.CLUBS)
        );
        List<Card> cardList2 = Arrays.asList(
                new Card(1,Suit.HEARTS),
                new Card(2,Suit.HEARTS),
                new Card(3,Suit.HEARTS),
                new Card(4,Suit.HEARTS),
                new Card(5,Suit.HEARTS)
        );
        Rule myRule = evaluator1.evaluate(cardList1);
        Rule yourRule = evaluator2.evaluate(cardList2);
        boolean result = app.compareTo(myRule,yourRule);
        assertThat(result, is(true));
    }
}
