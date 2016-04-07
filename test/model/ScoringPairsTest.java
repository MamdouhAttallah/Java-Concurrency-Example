package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jim
 */
public class ScoringPairsTest implements CribbageConstants {

    public ScoringPairsTest() {
    }

    @Test
    public void test2a() {
        Hand hand = new Hand("4c", "4d");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.pairs(hand));
    }

     @Test
    public void test2e() {
        Hand hand = new Hand("4c", "4d", "5h");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test2f() {
        Hand hand = new Hand("4c", "5d", "4h");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test2i() {
        Hand hand = new Hand("kc", "2c", "js", "2d", "4c");
         assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test2j() {
        Hand hand = new Hand("kc", "3d", "js", "3h");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test3d() {
        Hand hand = new Hand("kc", "2c", "js", "2d", "4s");
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test4a() {
        Hand hand = new Hand("jc", "jd", "4s", "4c");
        assertEquals(4, Scoring.score(hand, false));
        assertEquals(4, Scoring.pairs(hand));
    }

    @Test
    public void test4b() {
        Hand hand = new Hand("jc", "5d", "4s", "4c");
         assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test4c() {
        Hand hand = new Hand("jc", "jd", "4s", "4c", "6s");
        assertEquals(4, Scoring.score(hand, false));
        assertEquals(4, Scoring.pairs(hand));
    }

    @Test
    public void test4d() {
        Hand hand = new Hand("jc", "jd", "7s", "8c", "2h");
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test5b() {
        Hand hand = new Hand("3c", "3d", "ts", "9c", "jh");
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test6a() {
        Hand hand = new Hand("2c", "2d", "2h", "4c", "tc");
        assertEquals(6, Scoring.score(hand, false));
        assertEquals(6, Scoring.pairs(hand));
    }

    @Test
    public void test6b() {
        Hand hand = new Hand("5c", "5d", "2h", "4c", "tc");
        assertEquals(2, Scoring.pairs(hand));
    }

    @Test
    public void test12a() {
        Hand hand = new Hand("4c", "4d", "4h", "3c", "3d");
        assertEquals(8, Scoring.pairs(hand));
    }

    @Test
    public void test12b() {
        Hand hand = new Hand("2c", "2d", "3h", "8c", "2s");
        assertEquals(6, Scoring.pairs(hand));
    }

    @Test
    public void test14a() {
        Hand hand = new Hand("3c", "3d", "6h", "6c", "9s");
        assertEquals(4, Scoring.pairs(hand));
    }

    @Test
    public void test14b() {
        Hand hand = new Hand("4c", "4d", "7h", "7c", "7s");
        assertEquals(8, Scoring.pairs(hand));
    }

    @Test
    public void test15a() {
        Hand hand = new Hand("ac", "3d", "3h", "3c", "3s");
        assertEquals(12, Scoring.pairs(hand));
    }

     @Test
    public void test20b() {
        Hand hand = new Hand("3c", "3d", "3h", "3s", "6c");
        assertEquals(12, Scoring.pairs(hand));
    }

}
