/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim
 */
public class GameTest implements CribbageConstants {

    Card c1, c2, c3, c4, c5, c6;
    Card c11, c12, c13, c14, c15, c16;
    Hand hand1, hand2;

    public GameTest() {
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
        // and some hands
        hand1 = new Hand(c1, c2, c3, c4, c5, c6);
        hand2 = new Hand(c11, c12, c13, c14, c15, c16);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of no arg constructor, of class Player.
     */
    @Test
    public void testNoArgConstructor() {
        System.out.println("Game");
        Game instance = new Game();
        assertEquals(null, instance.getPlayer1().getName());
        assertEquals(null, instance.getPlayer2().getName());
        assertEquals(true, instance.isPlayer1Deals());
    }

    /**
     * Test of convenience constructor, of class Player.
     */
    @Test
    public void testConvenienceConstructor() {
        System.out.println("Game...");
        Player p1 = new Player("Jim");
        p1.setHand(hand1);
        Player p2 = new Player("George");
        p2.setHand(hand2);
        Game instance = new Game(p1, p2);
        assertEquals("Jim", instance.getPlayer1().getName());
        assertEquals("George", instance.getPlayer2().getName());
        assertEquals(true, instance.isPlayer1Deals());
    }

    /**
     * Test of setPlayer1 method, of class Game.
     */
    @Test
    public void testSetPlayer1() {
        System.out.println("setPlayer1");
        Game instance = new Game();
        Player p1 = new Player("Jim");
        p1.setHand(hand1);
        Player p2 = new Player("George");
        p2.setHand(hand2);
        assertEquals(null, instance.getPlayer1().getName());
        instance.setPlayer1(p1);
        assertEquals("Jim", instance.getPlayer1().getName());
    }

    /**
     * Test of setPlayer2 method, of class Game.
     */
    @Test
    public void testSetPlayer2() {
        System.out.println("setPlayer2");
        Game instance = new Game();
        Player p1 = new Player("Jim");
        p1.setHand(hand1);
        Player p2 = new Player("George");
        p2.setHand(hand2);
        assertEquals(null, instance.getPlayer2().getName());
        instance.setPlayer1(p1);
        assertEquals(null, instance.getPlayer2().getName());
        instance.setPlayer2(p2);
        assertEquals("George", instance.getPlayer2().getName());
    }

    /**
     * Test of setPlayer1Deals method, of class Game.
     */
    @Test
    public void testSetPlayer1Deals() {
        System.out.println("setPlayer1Deals");
        boolean expected = true;
        Game instance = new Game();
        assertEquals(expected, instance.isPlayer1Deals());
        expected = false;
        instance.setPlayer1Deals(expected);
        assertEquals(expected, instance.isPlayer1Deals());
        expected = true;
        instance.setPlayer1Deals(expected);
        assertEquals(expected, instance.isPlayer1Deals());

    }

    /**
     * Test of toString method, of class Game.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Game instance = new Game();
        Player p1 = new Player("Jim");
        p1.setHand(hand1);
        Player p2 = new Player("George");
        p2.setHand(hand2);
        assertEquals("Game between null (0) and null (0)", instance.toString());
        instance.setPlayer1(p1);
        assertEquals("Game between Jim (0) and null (0)", instance.toString());
        instance.setPlayer2(p2);
        assertEquals("Game between Jim (0) and George (0)", instance.toString());
    }
}
