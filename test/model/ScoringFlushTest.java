package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Check that flushes are counted correctly.
 *
 * @author jim
 */
public class ScoringFlushTest implements CribbageConstants {

    public ScoringFlushTest() {
    }

    @Test
    public void test4g() {
        Hand hand = new Hand("jc", "4c", "7c", "2c", "th");

        assertEquals(0, Scoring.score(hand, true));
        assertEquals(4, Scoring.score(hand, false));
    }

    @Test
    public void test4g2() {
        Hand hand = new Hand("qc", "4c", "th", "7c", "2c");

        assertEquals(0, Scoring.score(hand, true));
        assertEquals(0, Scoring.score(hand, false));
    }

    @Test
    public void test5e() {
        Hand hand = new Hand("tc", "4c", "7c", "2c", "jc");
        assertEquals(5, Scoring.flush(hand, true));
        assertEquals(5, Scoring.score(hand, true));
        assertEquals(5, Scoring.score(hand, false));
    }

    @Test
    public void test5f() {
        Hand hand = new Hand("tc", "4c", "7c", "2c", "jh");
        assertEquals(4, Scoring.flush(hand, false));
        assertEquals(4, Scoring.score(hand, false));
        assertEquals(0, Scoring.score(hand, true));
    }
}
