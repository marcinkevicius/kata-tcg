package de.kimminich.kata.tcg;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void playerShouldHave30InitialHealth() {
        assertThat(player.getHealth(), is(equalTo(30)));
    }

    @Test
    public void playerShouldHaveZeroInitialMana() {
        assertThat(player.getMana(), is(equalTo(0)));
    }

    @Test
    public void cardDeckShouldContainInitialCards() {
        assertThat(player.getNumberOfCardsWithManaCost(0), is(equalTo(2)));
        assertThat(player.getNumberOfCardsWithManaCost(1), is(equalTo(2)));
        assertThat(player.getNumberOfCardsWithManaCost(2), is(equalTo(3)));
        assertThat(player.getNumberOfCardsWithManaCost(3), is(equalTo(4)));
        assertThat(player.getNumberOfCardsWithManaCost(4), is(equalTo(3)));
        assertThat(player.getNumberOfCardsWithManaCost(5), is(equalTo(2)));
        assertThat(player.getNumberOfCardsWithManaCost(6), is(equalTo(2)));
        assertThat(player.getNumberOfCardsWithManaCost(7), is(equalTo(1)));
        assertThat(player.getNumberOfCardsWithManaCost(8), is(equalTo(1)));
    }

    @Test
    public void playerStartsWithEmptyHand() {
        assertThat(player.getNumberOfHandCards(), is(equalTo(0)));
    }

    @Test
    public void drawingACardShouldPutOneCardFromDeckIntoHand() {
        // given
        Player player = new FakePlayer().withCardsInDeck(0).withNoCardsInHand();
        // when
        player.drawCard();
        // then
        assertThat(player.getNumberOfHandCards(), is(equalTo(1)));
        assertThat(player.getNumberOfCardsWithManaCost(0), is(equalTo(0)));
    }

    private class FakePlayer extends Player {

        public FakePlayer() {
            this.deck = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            this.hand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        public FakePlayer withCardsInDeck(int... manaCost) {
            for (int cost : manaCost) {
                deck[cost] = deck[cost] + 1;
            }
            return this;
        }

        public FakePlayer withNoCardsInDeck() {
            this.deck = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            return this;
        }

        public FakePlayer withCardsInHand(int... manaCost) {
            for (int cost : manaCost) {
                hand[cost] = hand[cost] + 1;
            }
            return this;
        }

        public FakePlayer withNoCardsInHand() {
            this.hand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            return this;
        }

    }

}
