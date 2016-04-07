package model;

import java.util.ArrayList;
import java.util.Collections;
import static model.CribbageConstants.ACE;
import static model.CribbageConstants.JACK;
import static model.CribbageConstants.QUEEN;

/**
 * The start of a cribbage scoring class.
 *
 * For now, it just scores hands, taking into account 15s, pairs, runs and
 * flushes. Oh, and his nibs!
 *
 * @author jim
 */
public class Scoring implements CribbageConstants {

    public static boolean DEBUG = false;

    /**
     * Determine the score for a hand
     *
     * @param hand The hand to score; should have 4 or 5 cards
     * @param isCrib Count this hand as a crib (for flush)
     * @return The number of positions the player holding this hand can peg
     */
    public static int score(Hand hand, boolean isCrib) {
        int result = 0;
        if (hand != null && hand.getCards().size() > 1 && hand.getCards().size() < 6) {
            result += fifteens(hand);
            result += pairs(hand);
            result += runs(hand);
            result += nibs(hand);
            result += flush(hand, isCrib);
            debug("... " + hand + "; 15s-" + fifteens(hand) + "; pairs-" + pairs(hand) + "; runs-" + runs(hand) + "; nibs-" + nibs(hand) + "; flush-" + flush(hand, isCrib));
        }
        return result;
    }

    /**
     * Count the 15s in a hand.
     *
     * @param hand The hand
     */
    public static int fifteens(Hand hand) {
        // setup 
        ArrayList<Card> cards = hand.getCards();
        int size = cards.size();
        if (size < 2 || size > 5) {
            return 0;
        }

        // test the full hand
        int count = test15(hand);
        debug("--- Fifteens --- " + hand);
        debug("All: " + count);
        // subdivide and count the other combinations
        if (size == 5) {
            // try all the combinations of 4 from 5
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(2), cards.get(3)));
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(2), cards.get(4)));
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(3), cards.get(4)));
            count += test15(new Hand(cards.get(0), cards.get(2), cards.get(3), cards.get(4)));
            count += test15(new Hand(cards.get(1), cards.get(2), cards.get(3), cards.get(4)));
            debug("After 4 of 5: " + count);
            // and then test the triplets
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(2)));
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(3)));
            count += test15(new Hand(cards.get(0), cards.get(2), cards.get(3)));
            count += test15(new Hand(cards.get(1), cards.get(2), cards.get(3)));
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(4)));
            count += test15(new Hand(cards.get(0), cards.get(2), cards.get(4)));
            count += test15(new Hand(cards.get(1), cards.get(2), cards.get(4)));
            count += test15(new Hand(cards.get(0), cards.get(3), cards.get(4)));
            count += test15(new Hand(cards.get(1), cards.get(3), cards.get(4)));
            count += test15(new Hand(cards.get(2), cards.get(3), cards.get(4)));
            debug("After 3 of 5: " + count);
            // and finally all of the pairs
            count += test15(new Hand(cards.get(0), cards.get(1)));
            count += test15(new Hand(cards.get(0), cards.get(2)));
            count += test15(new Hand(cards.get(0), cards.get(3)));
            count += test15(new Hand(cards.get(0), cards.get(4)));
            count += test15(new Hand(cards.get(1), cards.get(2)));
            count += test15(new Hand(cards.get(1), cards.get(3)));
            count += test15(new Hand(cards.get(1), cards.get(4)));
            count += test15(new Hand(cards.get(2), cards.get(3)));
            count += test15(new Hand(cards.get(2), cards.get(4)));
            count += test15(new Hand(cards.get(3), cards.get(4)));
            debug("After 2 of 5: " + count);
        } else if (size == 4) {
            // try all 3 of 4
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(2)));
            count += test15(new Hand(cards.get(0), cards.get(1), cards.get(3)));
            count += test15(new Hand(cards.get(0), cards.get(2), cards.get(3)));
            count += test15(new Hand(cards.get(1), cards.get(2), cards.get(3)));
            debug("After 3 of 4: " + count);
            // and all the pairs too
            count += test15(new Hand(cards.get(0), cards.get(1)));
            count += test15(new Hand(cards.get(0), cards.get(2)));
            count += test15(new Hand(cards.get(0), cards.get(3)));
            count += test15(new Hand(cards.get(1), cards.get(2)));
            count += test15(new Hand(cards.get(1), cards.get(3)));
            count += test15(new Hand(cards.get(2), cards.get(3)));
            debug("After 2 of 4: " + count);
        } else if (size == 3) {
            // try all 2 of 3
            count += test15(new Hand(cards.get(0), cards.get(1)));
            count += test15(new Hand(cards.get(0), cards.get(2)));
            count += test15(new Hand(cards.get(1), cards.get(2)));
            debug("After 2 of 3: " + count);
        } // if we get here, we have already tested whether the pair adds up to fifteen
        return count;
    }

    /**
     * Test if a hand adds up to fifteen exactly.
     *
     * @return 2 if so, 0 otherwise
     */
    private static int test15(Hand hand) {
        int sum = 0;
        for (Card card : hand.getCards()) {
            sum += card.value();
        }
        if (sum == 15) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Count the pairs in a hand.
     *
     * @param hand The hand
     */
    public static int pairs(Hand hand) {
        int result = 0;
        ArrayList<Card> cards = hand.getCards();
        if (cards.size() == 2) {
            if (cards.get(0).getRank().equals(cards.get(1).getRank())) {
                return 2;
            } else {
                return 0;
            }
        }

        // We need to subivide so we can conquer
        int size = cards.size();
        int count = 0;
        if (size == 5) {
            // try all the combinations of 4 from 5
            count += pairs(new Hand(cards.get(0), cards.get(1)));
            count += pairs(new Hand(cards.get(0), cards.get(2)));
            count += pairs(new Hand(cards.get(0), cards.get(3)));
            count += pairs(new Hand(cards.get(0), cards.get(4)));
            count += pairs(new Hand(cards.get(1), cards.get(2)));
            count += pairs(new Hand(cards.get(1), cards.get(3)));
            count += pairs(new Hand(cards.get(1), cards.get(4)));
            count += pairs(new Hand(cards.get(2), cards.get(3)));
            count += pairs(new Hand(cards.get(2), cards.get(4)));
            count += pairs(new Hand(cards.get(3), cards.get(4)));
        } else if (size == 4) {
            count += pairs(new Hand(cards.get(0), cards.get(1)));
            count += pairs(new Hand(cards.get(0), cards.get(2)));
            count += pairs(new Hand(cards.get(0), cards.get(3)));
            count += pairs(new Hand(cards.get(1), cards.get(2)));
            count += pairs(new Hand(cards.get(1), cards.get(3)));
            count += pairs(new Hand(cards.get(2), cards.get(3)));
        } else if (size == 3) {
            count += pairs(new Hand(cards.get(0), cards.get(1)));
            count += pairs(new Hand(cards.get(0), cards.get(2)));
            count += pairs(new Hand(cards.get(1), cards.get(2)));
        }
        // if we get here, forget it
        return count;
    }

    /**
     * Count the runs in a hand.
     *
     * @param hand The hand
     */
    public static int runs(Hand hand) {
        // now for the real work
        int count = 0;
        ArrayList<Card> cards = hand.getCards();
        if (cards.size() < 3 || cards.size() > 5) {
            return count;
        }

        debug("--- Runs in --- " + hand);
        // is the entire hand a run?
        count = testRun(hand);
        debug("Raw: " + count);
        // if so, don't count any subruns
        if (count > 0) {
            return count;
        }

        debug("Size: " + cards.size());
        // ok. now comes the fun part
        if (cards.size() == 5) {
            // check for runs of 4
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(2), cards.get(3)));
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(2), cards.get(4)));
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(3), cards.get(4)));
            count += testRun(new Hand(cards.get(0), cards.get(2), cards.get(3), cards.get(4)));
            count += testRun(new Hand(cards.get(1), cards.get(2), cards.get(3), cards.get(4)));
            debug("4 of 5: " + count);
            if (count > 0) {
                return count;     // don't count subruns
            }
            // and for runs of 3
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(2)));
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(3)));
            count += testRun(new Hand(cards.get(0), cards.get(2), cards.get(3)));
            count += testRun(new Hand(cards.get(1), cards.get(2), cards.get(3)));
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(4)));
            count += testRun(new Hand(cards.get(0), cards.get(2), cards.get(4)));
            count += testRun(new Hand(cards.get(1), cards.get(2), cards.get(4)));
            count += testRun(new Hand(cards.get(0), cards.get(3), cards.get(4)));
            count += testRun(new Hand(cards.get(1), cards.get(3), cards.get(4)));
            count += testRun(new Hand(cards.get(2), cards.get(3), cards.get(4)));
            debug("3 of 5: " + count);
            if (count > 0) {
                return count;     // don't count subruns
            }
        } else if (cards.size() == 4) {
            // test for subruns of 3

            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(2)));
            count += testRun(new Hand(cards.get(0), cards.get(1), cards.get(3)));
            count += testRun(new Hand(cards.get(0), cards.get(2), cards.get(3)));
            count += testRun(new Hand(cards.get(1), cards.get(2), cards.get(3)));
            debug("3 of 4: " + count);
        }
        debug("Answer: " + count);
        return count;
    }

    /**
     * Test for a run in a hand.
     *
     * @return A count of the run
     */
    private static int testRun(Hand hand) {
        ArrayList<Card> cards = new ArrayList<Card>(hand.getCards());
        if (cards.size() < 3 || cards.size() > 5) {
            return 0;
        }

        // What ranks do we have?
        String test = buildTheRanks(hand);
        debug("-> " + test + " from " + hand);
        int result = 0;

        // test runs of length 5
        if (cards.size() == 5) {
            if (test.equals("A2345")
                    || test.equals("23456")
                    || test.equals("34567")
                    || test.equals("45678")
                    || test.equals("56789")
                    || test.equals("6789T")
                    || test.equals("789TJ")
                    || test.equals("89TJQ")
                    || test.equals("9TJQK")) {
                result = 5;
            }
        } else if (cards.size() == 4) {
            // and then 4
            if (test.equals("A234")
                    || test.equals("2345")
                    || test.equals("3456")
                    || test.equals("4567")
                    || test.equals("5678")
                    || test.equals("6789")
                    || test.equals("789T")
                    || test.equals("89TJ")
                    || test.equals("9TJQ")
                    || test.equals("TJQK")) {
                result = 4;
            }
        } else {
            // and finally 3
            if (test.equals("A23")
                    || test.equals("234") || test.equals("345")
                    || test.equals("456") || test.equals("567")
                    || test.equals("678") || test.equals("789")
                    || test.equals("89T") || test.equals("9TJ")
                    || test.equals("TJQ") || test.equals("JQK")) {
                result = 3;
            }

        }
        debug("Result: " + result);
        return result;
    }

    /**
     * Build a string containing the ranks of all the cards in a hand, ordered
     * by rank.
     *
     * @param hand The hand in question
     * @return A string as above
     */
    private static String buildTheRanks(Hand hand) {
        // start with an empty result
        String result = "";
        // copy the hand to an ArrayList
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : hand.getCards()) {
            cards.add(card);
        }

        // Iterate through the ArrayList, finding the smallest card, and add it to our result
        while (cards.size() > 0) {
            Card smallest = null;
            for (Card card : cards) {
                if (smallest == null) {
                    smallest = card;
                } else if (card.value() < smallest.value()) {
                    smallest = card;
                } else {
                    // do we have a facecard?
                    if (card.value() == smallest.value() && card.value() == 10) {
                        boolean jackers = smallest.getRank().equals(JACK);
                        boolean queeners = smallest.getRank().equals(QUEEN);
                        boolean kingers = smallest.getRank().equals(KING);
                        // convoluted dance to see if we have a new smallest card
                        if (card.getRank().equals("T") && (jackers || queeners || kingers)) {
                            smallest = card;
                        } else if (card.getRank().equals("J") && (queeners || kingers)) {
                            smallest = card;
                        } else if (card.getRank().equals("Q") && (kingers)) {
                            smallest = card;
                        }
                    }
                }
            }
            result += smallest.getRank();
            cards.remove(smallest);
        }

        return result;
    }

    /**
     * Count the nibs in a hand. We need five cards, and one of the first four
     * needs to be a jack of the same suit as the fifth card.
     *
     * @param hand The hand
     */
    public static int nibs(Hand hand) {
        int result = 0;
        if (hand.getCards().size() == 5) {
            String target = hand.getCard(4).getSuit();
            result += checkJack(hand.getCard(0), target);
            result += checkJack(hand.getCard(1), target);
            result += checkJack(hand.getCard(2), target);
            result += checkJack(hand.getCard(3), target);
        }
        return result;
    }

    /**
     * Check for a jack that counts as his nibs
     */
    private static int checkJack(Card card, String suit) {
        String rank = card.getRank();
        if (rank != null && rank.equals(JACK)) {
            String theSuit = card.getSuit();
            if (theSuit != null && theSuit.equals(suit)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Count the flush in a hand.
     *
     * @param isCrib Count this hand as a crib (for flush)
     * @param hand The hand
     */
    public static int flush(Hand hand, boolean isCrib) {
        int result = 0;
        ArrayList<Card> cards = hand.getCards();
        // only score a complete hand
        if (cards.size() > 4) {
            String suit = cards.get(0).getSuit(); // what we are looking for
            if (cards.get(1).getSuit().equals(suit) && cards.get(2).getSuit().equals(suit)
                    && cards.get(3).getSuit().equals(suit)) {
                // we have 4. what about 5?
                result = 4;
                if (cards.get(4).getSuit().equals(suit)) {
                    // all five tghe same
                    result = 5;
                } else if (isCrib) {
                    result = 0;
                }
            }
        }
        return result;
    }

    /**
     * Debugging help
     */
    public static void debug(String message) {
        if (DEBUG) {
            System.out.println(message);
        }
    }
}
