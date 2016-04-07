package model;

/**
 * Here we define generally useful constants for the game of Cribbage.
 *
 * Assignment 2: added a contstant for the "ten" card.
 *
 * @author jim
 */
public interface CribbageConstants {

    // the suits
    public final static String CLUBS = "C";
    public final static String DIAMONDS = "D";
    public final static String HEARTS = "H";
    public final static String SPADES = "S";
    public final static String[] allSuits = {CLUBS, DIAMONDS, HEARTS, SPADES};
    // some of the card names
    public final static String ACE = "A";
    public final static String TEN = "T";
    public final static String JACK = "J";
    public final static String QUEEN = "Q";
    public final static String KING = "K";
    public final static String[] allRanks = {ACE, "2", "3", "4", "5", "6", "7", "8", "9", TEN, JACK, QUEEN, KING};
    // the board
    public final static int FINISH = 121;
    // some card stats
    public final static int DECK_SIZE = 52;
    public final static int HAND_SIZE = 6;
    public final static int HIS_NIBS = 1;
    public final static int HIS_NOBS = 2;
    public final static int PEGGING_MAX = 31;
    public final static int FIFTEEN = 15;
}
