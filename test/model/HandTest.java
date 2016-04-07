package model;

import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim
 */
public class HandTest implements CribbageConstants {

    Card c1, c2, c3, c4, c5, c6;
    Card c11, c12, c13, c14, c15, c16;

    public HandTest() {
    }

    @Before
    public void setUp() {
        // Make some cards
        c1 = new Card("4" + HEARTS);
        c2 = new Card("2" + CLUBS);
        c3 = new Card(JACK + SPADES);
        c4 = new Card("3" + HEARTS);
        c5 = new Card("3" + DIAMONDS);
        c6 = new Card("5" + SPADES);
        c11 = new Card("7" + HEARTS);
        c12 = new Card("8" + CLUBS);
        c13 = new Card(QUEEN + SPADES);
        c14 = new Card(ACE + HEARTS);
        c15 = new Card("4" + DIAMONDS);
        c16 = new Card("4" + SPADES);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of no arg constructor, of class Hand.
     */
    @Test
    public void testNoArgConstructor() {
        System.out.println("Hand");
        Hand instance = new Hand();
        assertEquals(null, instance.getCard(0));
        assertEquals(null, instance.getCard(1));
        assertEquals(null, instance.getCard(2));
        assertEquals(null, instance.getCard(3));
        assertEquals(null, instance.getCard(4));
        assertEquals(null, instance.getCard(5));
    }

    /**
     * Test of convenience constructor, of class Card.
     */
    @Test
    public void testConvenienceConstructor() {
        System.out.println("Hand...");
        Hand instance = new Hand(c1, c2, c3, c6, c5, c4);
        assertEquals(6, instance.getCards().size());
        assertEquals("4H", instance.getCard(0).toString());
        assertEquals("2C", instance.getCard(1).toString());
        assertEquals("JS", instance.getCard(2).toString());
        assertEquals("5S", instance.getCard(3).toString());
        assertEquals("3D", instance.getCard(4).toString());
        assertEquals("3H", instance.getCard(5).toString());
    }

    /**
     * Test of ArrayList convenience constructor, of class Card.
     */
    @Test
    public void testArrayListConvenienceConstructor() {
        System.out.println("Hand from ArrayList...");
        ArrayList<Card> starter = new ArrayList<Card>();
        starter.add(c1);
        starter.add(c2);
        Hand instance = new Hand(starter);
        assertEquals(starter.size(), instance.getCards().size());
        assertEquals("4H", instance.getCard(0).toString());
        assertEquals("2C", instance.getCard(1).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test1StringConvenienceConstructor() {
        System.out.println("Hand from 1 string...");
        Hand instance = new Hand(c1.toString());
        assertEquals(1, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test2StringConvenienceConstructor() {
        System.out.println("Hand from 2 string...");
        Hand instance = new Hand(c1.toString(), c2.toString());
        assertEquals(2, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
        assertEquals(c2.toString(), instance.getCard(1).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test3StringConvenienceConstructor() {
        System.out.println("Hand from 3 string...");
        Hand instance = new Hand(c1.toString(), c2.toString(), c3.toString());
        assertEquals(3, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
        assertEquals(c2.toString(), instance.getCard(1).toString());
        assertEquals(c3.toString(), instance.getCard(2).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test4StringConvenienceConstructor() {
        System.out.println("Hand from 4 string...");
        Hand instance = new Hand(c1.toString(), c2.toString(), c3.toString(), c4.toString());
        assertEquals(4, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
        assertEquals(c2.toString(), instance.getCard(1).toString());
        assertEquals(c3.toString(), instance.getCard(2).toString());
        assertEquals(c4.toString(), instance.getCard(3).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test5StringConvenienceConstructor() {
        System.out.println("Hand from 5 string...");
        Hand instance = new Hand(c1.toString(), c2.toString(), c3.toString(), c4.toString(), c5.toString());
        assertEquals(5, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
        assertEquals(c2.toString(), instance.getCard(1).toString());
        assertEquals(c3.toString(), instance.getCard(2).toString());
        assertEquals(c4.toString(), instance.getCard(3).toString());
        assertEquals(c5.toString(), instance.getCard(4).toString());
    }

    /**
     * Make sure that the string constructors are there
     */
    @Test
    public void test6StringConvenienceConstructor() {
        System.out.println("Hand from 6 string...");
        Hand instance = new Hand(c1.toString(), c2.toString(), c3.toString(), c4.toString(), c5.toString(), c6.toString());
        assertEquals(6, instance.getCards().size());
        assertEquals(c1.toString(), instance.getCard(0).toString());
        assertEquals(c2.toString(), instance.getCard(1).toString());
        assertEquals(c3.toString(), instance.getCard(2).toString());
        assertEquals(c4.toString(), instance.getCard(3).toString());
        assertEquals(c5.toString(), instance.getCard(4).toString());
        assertEquals(c6.toString(), instance.getCard(5).toString());
    }

    /**
     * Test of addCard method, of class Hand.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        Hand instance = new Hand(c1.toString(), c2.toString());
        assertEquals(2, instance.getCards().size());
        instance.addCard(c11.toString());
        assertEquals(3, instance.getCards().size());
        assertEquals(c11.toString(), instance.getCard(2).toString());
        instance.addCard(c11.toString());  // this shouldn't work
        assertEquals(3, instance.getCards().size());
    }

    /**
     * Test of addCard method, of class Hand.
     */
    @Test
    public void testOtherAddCard() {
        System.out.println("Other addCard");
        Hand instance = new Hand(c1.toString(), c2.toString());
        assertEquals(2, instance.getCards().size());
        instance.addCard(c11);
        assertEquals(3, instance.getCards().size());
        assertEquals(c11, instance.getCard(2));
        instance.addCard(c11);  // this shouldn't work
        assertEquals(3, instance.getCards().size());
    }

    /**
     * Test of removeCard method, of class Hand.
     */
    @Test
    public void testRemoveCard() {
        System.out.println("removeCard");
               Hand instance = new Hand(c1.toString(), c2.toString(), c3.toString(), c4.toString(), c5.toString(), c6.toString());
        assertEquals(6, instance.getCards().size());
        instance.removeCard(2);
        assertEquals(5, instance.getCards().size());
        assertEquals(c4,instance.getCard(2));
    }

  
    /**
     * Test of toString method, of class Hand.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Hand instance = new Hand(c1, c2, c3, c6, c5, c4);
        assertEquals("4H, 2C, JS, 5S, 3D, 3H", instance.toString());

    }
}
