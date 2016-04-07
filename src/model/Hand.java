package model;

import java.util.ArrayList;

/**
 * Model for a hand of cards for cribbage. A hand contains up to six cards.
 *
 * @author jim
 */
public class Hand implements CribbageConstants {
    
    private ArrayList<Card> cards;

    //--------------- Constructors --------------------
    //
    /**
     * No argument constructor. Make a hand with six default cards. Yes, this is
     * hokey, as the cards will all be the same. We will fix this for the next
     * assignment.
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * Convenience constructor. Make a hand with the six cards given.
     *
     * @param c1 The first card
     * @param c2 The second card
     * @param c3 The third card
     * @param c4 The fourth card
     * @param c5 The fifth card
     * @param c6 The sixth card
     */
    public Hand(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6) {
        this();
        addCard(c1);
        addCard(c2);
        addCard(c3);
        addCard(c4);
        addCard(c5);
        addCard(c6);
    }

    /**
     * Convenience constructor. Make a hand with the six cards given.
     *
     * @param c1 The first card
     * @param c2 The second card
     * @param c3 The third card
     * @param c4 The fourth card
     * @param c5 The fifth card
     */
    public Hand(Card c1, Card c2, Card c3, Card c4, Card c5) {
        this();
        addCard(c1);
        addCard(c2);
        addCard(c3);
        addCard(c4);
        addCard(c5);
    }

    /**
     * Convenience constructor. Make a hand with the six cards given.
     *
     * @param c1 The first card
     * @param c2 The second card
     * @param c3 The third card
     * @param c4 The fourth card
     */
    public Hand(Card c1, Card c2, Card c3, Card c4) {
        this();
        addCard(c1);
        addCard(c2);
        addCard(c3);
        addCard(c4);
    }

    /**
     * Convenience constructor. Make a hand with the six cards given.
     *
     * @param c1 The first card
     * @param c2 The second card
     * @param c3 The third card
     *
     */
    public Hand(Card c1, Card c2, Card c3) {
        this();
        addCard(c1);
        addCard(c2);
        addCard(c3);
    }

    /**
     * Convenience constructor. Make a hand with the six cards given.
     *
     * @param c1 The first card
     * @param c2 The second card
     * @param c3 The third card
     * @param c4 The fourth card
     * @param c5 The fifth card
     * @param c6 The sixth card
     */
    public Hand(Card c1, Card c2) {
        this();
        addCard(c1);
        addCard(c2);
    }
    
    public Hand(ArrayList<Card> cards) {
        // copy the hand!
        this.cards = new ArrayList<Card>();
        for (Card card : cards) {
            this.cards.add(card);
        }
    }
    
    public Hand(String which1) {
        this();
        addCard(which1);
    }
    
    public Hand(String which1, String which2) {
        this(which1);
        addCard(which2);
    }
    
    public Hand(String which1, String which2, String which3) {
        this(which1, which2);
        addCard(which3);
    }
    
    public Hand(String which1, String which2, String which3, String which4) {
        this(which1, which2, which3);
        addCard(which4);
    }
    
    public Hand(String which1, String which2, String which3, String which4, String which5) {
        this(which1, which2, which3, which4);
        addCard(which5);
    }
    
    public Hand(String which1, String which2, String which3, String which4, String which5, String which6) {
        this(which1, which2, which3, which4, which5);
        addCard(which6);
    }

    //--------------- Field accessors & mutators --------------------
    //
    /**
     * Return all of the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Card accessor.
     *
     * @return the first card
     */
    public Card getCard(int n) {
        if (n >= cards.size()) {
            return null;
        }
        return cards.get(n);
    }

    /**
     * Card mutator - add another card to the collection, but only if it isn't
     * already there.
     *
     * @param c1 the first card in our hand
     */
    public void addCard(Card card1) {
        if (!cards.contains(card1)) {
            cards.add(card1);
        }
    }

    /**
     * Card remover.
     *
     * @param n Which card to remove
     */
    public void removeCard(int n) {
        if (n <= cards.size()) {
            cards.remove(n);
        }
    }
    
    public void addCard(String which) {
        Card card = new Card(which);
        if (!cards.contains(card)) {
            cards.add(card);
        }
    }

    //--------------- Utility methods --------------------
    //
    /**
     * Provide the text representation of a hand.
     *
     * @return all of the cards in the hand, comma-separated.
     */
    public String toString() {
        String result = "";
        for (Card card : cards) {
            if (result.length() > 0) {
                result += ", ";
            }
            result += card;
        }
        return result;
    }
}