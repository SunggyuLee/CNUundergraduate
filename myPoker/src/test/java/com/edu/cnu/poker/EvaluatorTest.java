package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {
    @Test
    public void SUIT가_5개가동일하면_플러시다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(7,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(6,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FLUSH));
    }
    @Test
    public void 덱이_2개_이상일때_SUIT가_5개동일하고_원페어이면_플러시다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(6,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FLUSH));
    }

    @Test
    public void RANK가_2개가_동일한게_하나있으면_원페어다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(7,Suit.DIAMONDS),
                new Card(2,Suit.DIAMONDS),
                new Card(7,Suit.HEARTS),
                new Card(13,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.ONE_PAIR));
    }
    @Test
    public void RANK가_2개가_동일한게_두개있으면_투페어다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2,Suit.CLUBS),
                new Card(7,Suit.DIAMONDS),
                new Card(2,Suit.DIAMONDS),
                new Card(7,Suit.HEARTS),
                new Card(13,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.TWO_PAIR));
    }
    @Test
    public void RANK가_3개가_동일한게_하나있으면_트리플이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2,Suit.CLUBS),
                new Card(1,Suit.HEARTS),
                new Card(13,Suit.DIAMONDS),
                new Card(1,Suit.DIAMONDS),
                new Card(1,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.TRIPLE));
    }
    @Test
    public void RANK가_2개가_동일한게_하나있고_3개가_동일한게_하나있으면_풀하우스다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(2,Suit.CLUBS),
                new Card(2,Suit.DIAMONDS),
                new Card(7,Suit.DIAMONDS),
                new Card(7,Suit.HEARTS),
                new Card(7,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FULL_HOUSE));
    }
    @Test
    public void RANK가_3개가_동일한게_하나있고_2개가_동일한게_하나있으면_풀하우스다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(8,Suit.CLUBS),
                new Card(8,Suit.DIAMONDS),
                new Card(8,Suit.DIAMONDS),
                new Card(3,Suit.HEARTS),
                new Card(3,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FULL_HOUSE));
    }
    @Test
    public void RANK가_연속되면_스트레이트다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(10,Suit.CLUBS),
                new Card(8,Suit.DIAMONDS),
                new Card(12,Suit.DIAMONDS),
                new Card(11,Suit.HEARTS),
                new Card(9,Suit.SPADES)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.STRAIGHT));
    }
    @Test
    public void RANK가_연속되고_플러시면_스트레이트플러시다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.DIAMONDS),
                new Card(5,Suit.DIAMONDS),
                new Card(6,Suit.DIAMONDS),
                new Card(7,Suit.DIAMONDS),
                new Card(8,Suit.DIAMONDS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.STRAIGHT_FLUSH));
    }


    @Test
    public void rank4개가_동일하면_포카드이다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.CLUBS),
                new Card(3, Suit.DIAMONDS),
                new Card(3, Suit.SPADES),
                new Card(3, Suit.HEARTS),
                new Card(5, Suit.CLUBS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FOUR_CARD));
    }
    @Test
    public void 플러시이면서_포카드면_포카드다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(3, Suit.DIAMONDS),
                new Card(3, Suit.DIAMONDS),
                new Card(3, Suit.DIAMONDS),
                new Card(3, Suit.DIAMONDS),
                new Card(5, Suit.DIAMONDS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.FOUR_CARD));
    }
    @Test
    public void RANK를_1_2_3_4_5로_가지면_백스트레이트다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.HEARTS),
                new Card(3,Suit.DIAMONDS),
                new Card(2,Suit.CLUBS),
                new Card(5,Suit.DIAMONDS),
                new Card(4,Suit.DIAMONDS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.BACK_STRAIGHT));
    }
    @Test
    public void RANK를_1_2_3_4_5로_가지고_플러시면_백스트레이트플러시다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.HEARTS),
                new Card(3,Suit.HEARTS),
                new Card(2,Suit.HEARTS),
                new Card(5,Suit.HEARTS),
                new Card(4,Suit.HEARTS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.BACK_STRAIGHT_FLUSH));
    }

    @Test
    public void RANK를_1_10_11_12_13으로_가지고_플러시면_로얄스트레이트플러시다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(13,Suit.HEARTS),
                new Card(10,Suit.HEARTS),
                new Card(11,Suit.HEARTS),
                new Card(1,Suit.HEARTS),
                new Card(12,Suit.HEARTS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.ROYAL_STRAIGHT_FLUSH));
    }

    @Test
    public void RANK를_1_10_11_12_13으로_가지면_마운틴이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(13,Suit.DIAMONDS),
                new Card(10,Suit.HEARTS),
                new Card(11,Suit.SPADES),
                new Card(1,Suit.HEARTS),
                new Card(12,Suit.CLUBS)
        );
        Rule result = evaluator.evaluate(cardList);
        assertThat(result.getPriority(), is(EnumRule.MOUNTAIN));
    }

}