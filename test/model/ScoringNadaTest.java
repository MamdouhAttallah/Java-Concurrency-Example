package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This suite of tests checks hands that should be worth zero.
 * 
 * @author jim
 */
public class ScoringNadaTest implements CribbageConstants {

    public ScoringNadaTest() {
    }

    // too few cards
    @Test
    public void test0a() {
        Hand hand = new Hand("4c");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    // cards that don't add up to anything
    @Test
    public void test0b() {
        Hand hand = new Hand("4c", "5d");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    // an empty hand
    @Test
    public void test0c() {
        Hand hand = new Hand();
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    // 3 cards that don't score
    @Test
    public void test0d() {
        Hand hand = new Hand("4c", "3d", "9s");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    // 4 cards that don't score
    @Test
    public void test0e() {
        Hand hand = new Hand("4c", "2d", "6h", "8s");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    // 5 cards that don't score
    @Test
    public void test0f() {
        Hand hand = new Hand("4c", "2d", "6h", "8s", "tc");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }
    
// too many cards
    @Test
    public void test0g() {
        Hand hand = new Hand("5c", "5d", "5h", "js", "qc", "kh");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }
    
 
}
