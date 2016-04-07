package ai;

import java.util.ArrayList;
import model.Card;
import model.CribbageConstants;
import model.Hand;
import model.Player;
import model.Scoring;

/**
 * This is a cribbage player AI. It implements the formula prescribed in the 
 * assignment writeup :)
 *
 * @author jim
 */
public class ByTheBook implements AI, CribbageConstants {

    private Player player;
    private Hand hand;
    private boolean dealing;
    private int nextDiscard;
    // all combinations of four card from six
    private int[][] combos = {
        {0, 1, 2, 3}, {0, 1, 2, 4}, {0, 1, 2, 5}, {0, 1, 3, 4}, {0, 1, 3, 5},
        {0, 1, 4, 5}, {0, 2, 3, 4}, {0, 2, 3, 5}, {0, 2, 4, 5}, {0, 3, 4, 5},
        {1, 2, 3, 4}, {1, 2, 3, 5}, {1, 2, 4, 5}, {2, 3, 4, 5}
    };
    // the corresponding combinations of two left over
    private int[][] leftovers = {
        {4, 5}, {3, 5}, {3, 4}, {2, 5}, {2, 4},
        {2, 3}, {1, 5}, {1, 4}, {1, 3}, {1, 2},
        {0, 5}, {0, 4}, {0, 3}, {0, 2}
    };

    /**
     * Default constructor.
     *
     * Initialize our fields.
     *
     * An AI must have one of these of the driver will not work.
     */
    public ByTheBook() {
        player = null;
        hand = null;
        dealing = false;
        nextDiscard = -1;   // we haven't calculated discards yet
    }

    /**
     * Ask the AI for a card to discard for the current crib.
     *
     * Examine all combinations of four cards that can be made from your hand. –
     * For each combination, count the score of the four cards... PLUS the score
     * for the two cards left over, if this is for your crib LESS the score for
     * the two cards left over, if this is for your opponent's crib. – Choose
     * the highest valued set of cards to keep, and remember the two to discard.
     * – Pick one of the two, remove it from your hand, and return it. Remember
     * the other
     *
     * @return The card you are currently discarding (after removing it from
     * your hand)
     */
    public Card discard() {
        // coming back for second card?
        if (nextDiscard >= 0) {
            Card cardToThrow = hand.getCard(nextDiscard);
            hand.removeCard(nextDiscard);
            nextDiscard = -1; // make sure we handle next decision properly
            return cardToThrow;
        }

        // what are we holding?
        ArrayList<Card> cards = hand.getCards();
        // starting point for our hunt
        int bestCombo = -1;
        int bestScore = -1;

        // go thru the combos, remember the one with the highest score
        for (int i = 0; i < combos.length; i++) {
            // Build the hand for this combo and what would be discarded
            Hand kept = new Hand(cards.get(combos[i][0]), cards.get(combos[i][1]), cards.get(combos[i][2]), cards.get(combos[i][3]));
            Hand thrown = new Hand(cards.get(leftovers[i][0]), cards.get(leftovers[i][1]));
            // score the hand we are keeping
            int score = Scoring.score(kept, false); // turnover card not in yet, so deal doesn't matter
            // adjust for our discards
            score += dealing ? Scoring.score(thrown, dealing) : -Scoring.score(thrown, dealing);

            // is this better than what we had?
            if (score > bestScore) {
                bestCombo = i;
                bestScore = score;
            } 
        }

        // remember the lower (first) of the two to discard
        nextDiscard = leftovers[bestCombo][0];
        // and throw away the other leftover
        Card theOne = hand.getCard(leftovers[bestCombo][1]);
        hand.removeCard(leftovers[bestCombo][1]);
        return theOne;
    }

    /**
     * Ask the AI for a card to play during pegging.
     *
     * Given the cards played so far, determine which of the cards remaining in
     * your hand will give the highest pegging value when played next. You will
     * need to compute the value of the possible sequences that would result
     * from adding each card left in your hand to the sequence.
     *
     * @param played The current sequence of cards played, in order; they will
     * add up to less than 31.
     * @return The card you want to play next, null for a pass.
     */
    public Card peggingPlay(Hand played) {
        // save hand reference for cleaner code
        ArrayList<Card> playedCards = played.getCards();
        ArrayList<Card> ourCards = hand.getCards();
        // quick reality check
        if (ourCards.size() < 1) {
            return null;
        }

        // assume we will discard nothing
        int winner = -1;
        int bestScore = -1;

        // Let's try each of the combinations
        for (int i = 0; i < ourCards.size(); i++) {
            // make sure we can play this card
            if (countSoFar(played) + ourCards.get(i).value() > PEGGING_MAX) {
                continue;
            }
            // what would we get if we played this one?
            Hand possible = new Hand(playedCards);
            possible.addCard(ourCards.get(i));
            int score = Scoring.score(possible, false); // "dealing" is false so we don't do a flush improperly
            if (score > bestScore) {
                bestScore = score;
                winner = i;
            } 
        }

        // can't play :(
        if (winner < 0) {
            return null;
        }

        // play our chosen card
        Card theOne = hand.getCard(winner);
        hand.removeCard(winner);
        return theOne;
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
