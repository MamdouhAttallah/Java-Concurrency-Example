package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim
 */
public class PlayerTest implements CribbageConstants {

    Card c1, c2, c3, c4, c5, c6;
    Card c11, c12, c13, c14, c15, c16;
    Hand hand1, hand2;

    public PlayerTest() {
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
        System.out.println("Player");
        Player instance = new Player();
        assertEquals(null, instance.getName());
        assertEquals(0, instance.getPosition());
        assertEquals(0, instance.getHand().getCards().size());
    }

    /**
     * Test of convenience constructor, of class Player.
     */
    @Test
    public void testConvenienceConstructor() {
        System.out.println("Player...");
        Player instance = new Player("Henry");
        assertEquals("Henry", instance.getName());
        assertEquals(0, instance.getPosition());
        Hand hand = instance.getHand();
        assertEquals(0, hand.getCards().size());
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        // test what should be good values
        String[] good = {"Larry", "Curly", "Moe"};
        for (String expResult : good) {
            Player instance = new Player();
            instance.setName(expResult);
            assertEquals("Good name rejected", expResult, instance.getName());
        }
        // test what should be bad values
        String[] bad = {null, ""};
        String expResult = "Henry";
        for (String oopsy : bad) {
            Player instance = new Player(expResult);
            instance.setName(oopsy);
            assertEquals("Bad name not caught", expResult, instance.getName());
        }
    }

    /**
     * Test of setHand method, of class Player.
     */
    @Test
    public void testSetHand() {
        System.out.println("setHand");
        Hand[] good = {hand1, hand2};
        for (Hand expResult : good) {
            Player instance = new Player();
            instance.setHand(expResult);
            assertEquals("Good hand rejected", expResult, instance.getHand());
        }
        // test what should be bad values
        Hand[] bad = {null};
        Hand expResult = hand1;
        for (Hand oopsy : bad) {
            Player instance = new Player("Henry");
            instance.setHand(hand1);
            instance.setHand(oopsy);
            assertEquals("Bad hand not caught", expResult, instance.getHand());
        }
    }

    /**
     * Test of setPosition method, of class Player.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        // test what should be good values
        int[] good = {0, 1, 2, 10, 120};
        for (int expResult : good) {
            Player instance = new Player("George");
            instance.setPosition(expResult);
            assertEquals("Good position rejected", expResult, instance.getPosition());
        }
         // test what should be bad values
        int[] bad = {-100,-2,-1,122,150,500};
        int expResult = 50;
        for (int oopsy : bad) {
            Player instance = new Player("Henry");
            instance.setPosition(expResult);
            instance.setPosition(oopsy);
            assertEquals("Bad position not caught", expResult, instance.getPosition());
        }
   }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Player instance = new Player();
        assertEquals(null, instance.toString());
        instance.setName("Alexander");
        instance.setHand(hand2);
        instance.setPosition(50);
        assertEquals("Alexander (7H, 8C, QS, AH, 4D, 4S) @ 50", instance.toString());
    }
}
