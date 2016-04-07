package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim
 */
public class CardTest implements CribbageConstants {

    public CardTest() {
    }

    /**
     * Test of no arg constructor, of class Card.
     */
    @Test
    public void testNoArgConstructor() {
        System.out.println("Card");
        Card instance = new Card();
        assertEquals(null, instance.getRank());
        assertEquals(null, instance.getSuit());
        assertEquals(0, instance.value());
    }

    /**
     * Test of convenience constructor, of class Card.
     */
    @Test
    public void testConvenienceConstructor() {
        System.out.println("Card...");
        // test what should be good values
        String[] good = {"AC", "TS", "4D", "KS", "ks", "aC"};
        for (String expResult : good) {
            Card instance = new Card(expResult);
            assertEquals("Good card (" + expResult + ") rejected", expResult.toUpperCase(), instance.toString());
        }
        // test what should be bad values
        String[] bad = {"AXC", "AX", "K", "KK", "XX", "ax"};
        for (String expResult : bad) {
            Card instance = new Card(expResult);
            assertEquals("Bad card (" + expResult + ") not rejected", null, instance.toString());
        }
    }

    /**
     * Test of setRank method, of class Card.
     */
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        // test what should be good values
        String[] good = {ACE, "2", KING};
        for (String expResult : good) {
            Card instance = new Card("4C");
            instance.setRank(expResult);
            assertEquals("Good name (" + expResult + ") rejected", expResult, instance.getRank());
        }
        // test what should be bad values
        String[] bad = {"AXC", "z", null, "KK", "c4"};
        for (String expResult : bad) {
            Card instance = new Card("4C");
            assertEquals("Bad name (" + expResult + ") not rejected", "4", instance.getRank());
        }
    }

    /**
     * Test of setSuit method, of class Card.
     */
    @Test
    public void testSetSuit() {
        System.out.println("setSuit");
        // test what should be good values
        String[] good = {CLUBS, DIAMONDS, HEARTS, SPADES};
        for (String expResult : good) {
            Card instance = new Card();
            instance.setSuit(expResult);
            assertEquals("Good suit (" + expResult + ") rejected", expResult, instance.getSuit());
        }
        // test what should be bad values
        String[] bad = {"AXC", "z", null, "KK", "c4"};
        for (String expResult : bad) {
            Card instance = new Card("4C");
            instance.setSuit(expResult);
            assertEquals("Bad suit (" + expResult + ") not rejected", CLUBS, instance.getSuit());
        }
    }

    /**
     * Test of setValue method, of class Card.
     */
    @Test
    public void testValue() {
        System.out.println("value");
        // test what should be good values
        String[] good = {TEN, JACK, QUEEN, KING};
        for (String expResult : good) {
            Card instance = new Card();
            instance.setRank(expResult);
            assertEquals("Good value (" + expResult + ") rejected", 10, instance.value());
        }
        // test the ace
        Card instance2 = new Card();
        instance2.setRank(ACE);
        assertEquals("Good value (" + ACE + ") rejected", 1, instance2.value());
        // test the regular cards
        int[] others = {2, 3, 4, 5, 6, 7, 8, 9};
        for (int expResult : others) {
            Card instance = new Card();
            instance.setRank("" + expResult);
            assertEquals("Good value (" + expResult + ") rejected", expResult, instance.value());
        }
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card();
        assertEquals(null, instance.toString());
        instance = new Card("4D");
        assertEquals("4D", instance.toString());

    }
}
