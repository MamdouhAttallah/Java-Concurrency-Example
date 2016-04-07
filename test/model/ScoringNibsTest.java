package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Check if his "nibs" is scored correctly.
 * 
 * @author jim
 */
public class ScoringNibsTest implements CribbageConstants {

    public ScoringNibsTest() {
    }

    @Test
    public void test1a() {
        Hand hand = new Hand("4c", "jh", "6h", "8h", "th");
        assertEquals(1, Scoring.score(hand, false));
        assertEquals(1, Scoring.score(hand, true));
    }

    @Test
    public void test1b() {
        Hand hand = new Hand("4c", "js", "6h", "8h", "th");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

    @Test
    public void test1c() {
        Hand hand = new Hand("4c", "jc", "6h", "8h", "ts");
        assertEquals(0, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }

}
