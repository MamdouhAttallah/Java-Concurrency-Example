package model;

/**
 * Model for a cribbage game player.
 *
 * @author jim
 */
public class Player implements CribbageConstants {

    public static final int START_POSITION = 0;
    public static final int FINISH_POSITION = 121;
    private String name;
    private Hand hand;
    private int position;

    //--------------- Constructors --------------------
    //
    /**
     * Default constructor. Set the initial state to an unknown player with a
     * default hand and at board position 0.
     */
    public Player() {
        this.name = null;
        this.hand = new Hand();
        this.position = START_POSITION;
    }

    /**
     * Convenience constructor. Make a default player and set their name.
     *
     * @param name Name of the player
     */
    public Player(String name) {
        this();
        setName(name);
    }

    //--------------- Field accessors & mutators --------------------
    //
    /**
     * Name accessor.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Name mutator.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Hand accessor
     *
     * @return the hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Hand mutator.
     *
     * @param hand the hand to set
     */
    public void setHand(Hand hand) {
        if (hand != null) {
            this.hand = hand;
        }
    }

    /**
     * Position accessor.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Position mutator.
     *
     * @param position the position to set
     */
    public void setPosition(int position) {
        if (position >= START_POSITION && position <= FINISH_POSITION) {
            this.position = position;
        }
    }

    //--------------- Utility methods --------------------
    //
    /**
     * Provide a text representation of a player
     *
     * @return The player's name, hand in parentheses, and position prefixed
     * with an at-sign.
     */
    public String toString() {
        if (name == null) {
            return null;
        }
        return name + " (" + hand + ") @ " + position;
    }
}
