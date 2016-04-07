package ai;

import unused.Dumber;
import java.util.Scanner;
import model.Card;
import model.Hand;
import model.Player;

/**
 * This is a "human" cribbage player. It prompts the user for cards to discard
 * or play. It extends DUmber, only providing the logic that is different.
 *
 * @author jim
 */
public class Human extends Dumber {

    /**
     * Default constructor.
     *
     * Initialize our fields.
     *
     * An AI must have one of these of the driver will not work.
     */
    public Human() {
        super();
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
        // save hand reference for cleaner code
        Hand hand = getHand();

        // prompt
        System.out.println(">>> Your hand: " + hand);
        System.out.print(">>> Discard? ");

        // get the human player's choice
        Scanner sin = new Scanner(System.in);
        String gogo = sin.nextLine().toUpperCase();

        // if it isn't in the hand, forget it
        Card theOne = new Card(gogo);
        if (!theOne.toString().equals(gogo)) {
            return null;
        }

        // find it
        int pos = hand.getCards().indexOf(theOne);

        // and remove it
        hand.removeCard(pos);
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
        // save hand reference for cleaner code
        Hand hand = getHand();

        // prompt
        System.out.println(">>> Your hand: " + hand);
        System.out.print(">>> Play? ");

        // get the human player's choice
        Scanner sin = new Scanner(System.in);
        String gogo = sin.nextLine().toUpperCase();

        // if it isn't in the hand, forget it
        Card theOne = new Card(gogo);
        if (theOne == null || theOne.toString() == null || !theOne.toString().equals(gogo)) {
            return null;
        }

        // find it
        int pos = hand.getCards().indexOf(theOne);

        // and remove it
        hand.removeCard(pos);
        return theOne;
    }
}
