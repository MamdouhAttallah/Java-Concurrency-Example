package unused;

import ai.AI;
import java.util.ArrayList;
import model.Card;
import model.CribbageConstants;
import model.Hand;
import model.Player;

/**
 * This is a dumber cribbage player. It always selects the first card in a hand
 * for discarding, or to play in pegging.
 *
 * @author jim
 */
public class Dumber implements AI, CribbageConstants {

    private Player player;
    private Hand hand;
    private boolean dealing;

    /**
     * Default constructor.
     *
     * Initialize our fields.
     *
     * An AI must have one of these of the driver will not work.
     */
    public Dumber() {
        player = null;
        hand = null;
        dealing = false;
    }

    /**
     * Inform the AI who it is playing for, in case it is interested.
     *
     * @param player The player you are providing the intelligence for
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Inform the AI of a new hand just dealt.
     *
     * @param hand The hand dealt to you by the game
     * @param dealing Whether you would be considered the dealer in human play
     */
    public void setHand(Hand playerHand, boolean dealing) {
        ArrayList<Card> myCards = new ArrayList<Card>();
        for (Card card : playerHand.getCards()) {
            myCards.add(card);
        }
        this.hand = new Hand(myCards);
        this.dealing = dealing;
    }

    /**
     * Ask the AI for a card to discard for the current crib.
     *
     * Our superior algorithm: return the first card in our hand!
     *
     * @return The card you are currently discarding (after removing it from
     * your hand)
     */
    public Card discard() {
        Card theOne = hand.getCard(0);
        hand.removeCard(0);
        return theOne;
    }

    /**
     * Ask the AI for a card to play during pegging.
     *
     * Our unbeatable algorithm: pick the first card!
     *
     * @param played The current sequence of cards played, in order; they will
     * add up to less than 31.
     * @return The card you want to play next, null for a pass.
     */
    public Card peggingPlay(Hand played) {
        if (hand.getCards().size() < 1) {
            return null;
        }
        Card theOne = hand.getCard(0);
        if (countSoFar(played) + theOne.value() > PEGGING_MAX) {
            return null; // pass
        }
        hand.removeCard(0);
        return theOne;
    }

    /**
     * Work method to add the values of the cards in a sequence
     */
    protected int countSoFar(Hand sequence) {
        int answer = 0;
        for (Card card : sequence.getCards()) {
            answer += card.value();
        }
        return answer;
    }

    /**
     * Return the hand we have left
     */
    public Hand getHand() {
        return hand;
    }
}
