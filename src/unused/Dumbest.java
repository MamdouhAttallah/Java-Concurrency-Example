package unused;

import ai.AI;
import java.util.ArrayList;
import java.util.Random;
import model.Card;
import model.CribbageConstants;
import static model.CribbageConstants.PEGGING_MAX;
import model.Hand;
import model.Player;

/**
 * This is a dumbest cribbage player. It selects cards to discard or to play
 * during pegging randomly.
 *
 * @author jim
 */
public class Dumbest implements AI, CribbageConstants {

    private Player player;
    private Hand hand;
    private boolean dealing;
    Random random = null;

    /**
     * Default constructor.
     *
     * Initialize our fields.
     *
     * An AI must have one of these of the driver will not work.
     */
    public Dumbest() {
        player = null;
        hand = null;
        dealing = false;
        random = new Random();
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
        int votedOff = random.nextInt(hand.getCards().size());
        Card theOne = hand.getCard(votedOff);
        hand.removeCard(votedOff);
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
        if (hand.getCards().size() == 0) return null; // nothing left to play
        int votedOff = random.nextInt(hand.getCards().size());
        Card theOne = hand.getCard(votedOff);
        if (countSoFar(played) + theOne.value() > PEGGING_MAX) {
            return null; // pass
        }
        hand.removeCard(votedOff);
        return theOne;
    }

    /**
     * Work method to add the values of the cards in a sequence
     */
    private int countSoFar(Hand sequence) {
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
