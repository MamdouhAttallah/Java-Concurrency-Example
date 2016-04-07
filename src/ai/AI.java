package ai;

import model.Card;
import model.Hand;
import model.Player;

/**
 * This interface represents the behaviour expected of a cribbage AI.
 *
 * Your want to implement this interface, and provide some real logic for your
 * AI!
 *
 * @author jim
 */
public interface AI {

    /**
     * Inform the AI who it is playing for, in case it is interested.
     *
     * This should only ever happen once, as an AI is not allowed to switch
     * loyalty during a game.
     *
     * @param player The player you are providing the intelligence for
     */
    public void setPlayer(Player player);

    /**
     * Inform the AI of a new hand just dealt.
     *
     * Make a local copy of this hand for your purposes.
     *
     * @param hand The hand dealt to you by the game
     * @param dealing Whether you would be considered the dealer in human play
     */
    public void setHand(Hand hand, boolean dealing);

    /**
     * Ask the AI for a card to discard for the current crib.
     *
     * @return The card you are currently discarding (after removing it from
     * your hand)
     */
    public Card discard();

    /**
     * Ask the AI for a card to play during pegging.
     *
     * @param played The current sequence of cards played, in order; they will
     * add up to less than 31.
     * @param countSoFar Point value total of cards played so far
     * @return The card you want to play next, null for a pass.
     */
    public Card peggingPlay(Hand played);

    /**
     * Return the hand we currently have left
     */
    public Hand getHand();
}
