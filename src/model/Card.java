package model;

/**
 * Model for a card in a standard bridge deck.
 *
 * @author jim
 */
public class Card implements CribbageConstants {

    private String rank;
    private String suit;
    private int value;

    //--------------- Constructors --------------------
    //
    /**
     * No argument constructor. Set the default state to an Ace of Clubs.
     */
    public Card() {
        this.rank = null;
        this.suit = null;;
        this.value = 0;
    }

    /**
     * Convenience constructor. Set the initial state to the specified rank,
     * suit & value.
     *
     * @param which The rank of the card
     * @param suit The suit of the card
     * @param value The numeric value of the card, for cribbage
     */
    public Card(String which) {
        // invoke the default constructor
        this();
        // test plausibility
        if (which == null) {
            return;
        }
        if (which.length() != 2) {
            return;
        }
        which = which.toUpperCase();
        // try to extract the card value segments
        setRank(which.substring(0, 1));
        setSuit(which.substring(1, 2));
        // check for either one of these being bad, and zap the other
        if (rank == null) {
            suit = null;
        }
        if (suit == null) {
            rank = null;
        }
    }

    //--------------- field accessors & mutators --------------------
    //
    /**
     * Name accessor.
     *
     * @return the rank
     */
    public String getRank() {
        return rank;
    }

    /**
     * Name mutator.
     *
     * business rules: - should be in the range A, 1, ..., 9, T, J, Q, K
     *
     * @param rank the rank to set
     */
    public void setRank(String rank) {
        // make sure the rank isn't null
        if (rank == null) {
            return;
        }
        // make sure the rank isn't too long or too short
        if (rank.length() != 1) {
            return;
        }
        rank = rank.toUpperCase();
        // check if the rank is one of the allowed ones
        if ("A23456789TJQK".contains(rank)) {
            this.rank = rank;
            // is this an ace?
            if (rank.equals(ACE)) {
                this.value = 1;
            } else // perhaps it is a face card?
            if ((TEN + JACK + QUEEN + KING).contains(rank)) {
                this.value = 10;
            } else {
                // it must be a regular card
                this.value = Integer.parseInt(rank);
            }
        }
    }

    /**
     * Suit accessor.
     *
     * @return the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Suit mutator.
     *
     * @param suit the suit to set
     */
    public void setSuit(String suit) {
        // make sure the suit isn't null
        if (suit == null) {
            return;
        }
        // make sure the suit is of the right length
        if (suit.length() != 1) {
            return;
        }
        suit = suit.toUpperCase();
        // make sure it is an allowed suit
        if ((CLUBS + DIAMONDS + HEARTS + SPADES).contains(suit)) {
            this.suit = suit;
        }
    }

    /**
     * Value accessor.
     *
     * @return the value
     */
    public int value() {
        return value;
    }

    //--------------- Utility methods --------------------
    //
    /**
     * Provide a text representation for a card
     *
     * @return The rank of the card, followed by its suit
     */
    public String toString() {
        if (rank == null || suit == null) {
            return null;
        }
        return rank + suit;
    }

    /**
     * Compare our rank & suit against another card's
     *
     * @param other The other card
     * @return true if they have the same rank and suit
     */
    public boolean equals(Object x) {
        if (x == null || toString() == null) {
            return false;
        }
        Card them = (Card) x;
        return toString().equals(them.toString());
    }
}
