package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Check that 15s are properly counted.
 *
 * @author jim
 */
public class Scoring15sTest implements CribbageConstants {

    public Scoring15sTest() {
    }

    @Test
    public void test2b() {
        Hand hand = new Hand("7c", "8d");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test2c() {
        Hand hand = new Hand("5c", "qd");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test2d() {
        Hand hand = new Hand("kc", "5c");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test2g() {
        Hand hand = new Hand("kc", "5c", "as");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test2h() {
        Hand hand = new Hand("kc", "4c", "as");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.fifteens(hand));
    }
    @Test
    public void test2k() {
        Hand hand = new Hand("ac", "2c", "3s", "4h", "5s");
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test4b() {
        Hand hand = new Hand("jc", "5d", "4s", "4c");
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test4d() {
        Hand hand = new Hand("jc", "jd", "7s", "8c", "2h");

        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test4e() {
        Hand hand = new Hand("jc", "5d", "7s", "8c", "ah");

        assertEquals(4, Scoring.fifteens(hand));
    }

    @Test
    public void test4f() {
        Hand hand = new Hand("jc", "5d", "7s", "8c");

        assertEquals(4, Scoring.fifteens(hand));
    }
    @Test
    public void test5b() {
        Hand hand = new Hand("3c", "3d", "ts", "9c", "jh");

        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test5c() {
        Hand hand = new Hand("8c", "9d", "ts", "jc", "qh");

        assertEquals(0, Scoring.fifteens(hand));
    }

    @Test
    public void test6b() {
        Hand hand = new Hand("5c", "5d", "2h", "4c", "tc");
        assertEquals(4, Scoring.fifteens(hand));
    }

    @Test
    public void test6d() {
        Hand hand = new Hand("ac", "2d", "3s", "4c", "7h");
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test6f() {
        Hand hand = new Hand("3c", "4d", "6c", "5s");
        assertEquals(2, Scoring.fifteens(hand));
    }

    @Test
    public void test12a() {
        Hand hand = new Hand("4c", "4d", "4h", "3c", "3d");
        assertEquals(4, Scoring.fifteens(hand));
    }

    @Test
    public void test13a() {
        Hand hand = new Hand("ac", "ad", "6h", "7c", "8s");
        assertEquals(8, Scoring.fifteens(hand));
    }

    @Test
    public void test13b() {
        Hand hand = new Hand("ac", "4d", "4h", "jc", "4c");
        assertEquals(6, Scoring.fifteens(hand));
    }

    @Test
    public void test14a() {
        Hand hand = new Hand("3c", "3d", "6h", "6c", "9s");
        assertEquals(10, Scoring.fifteens(hand));
    }

    @Test
    public void test14b() {
        Hand hand = new Hand("4c", "4d", "7h", "7c", "7s");
        assertEquals(6, Scoring.fifteens(hand));
    }

    @Test
    public void test16c() {
        Hand hand = new Hand("2c", "6d", "7h", "7c", "8s");
        assertEquals(8, Scoring.fifteens(hand));
    }

    @Test
    public void test24a() {
        Hand hand = new Hand("ad", "7c", "7d", "7h", "7s");
        assertEquals(12, Scoring.fifteens(hand));
    }

    @Test
    public void test24b() {
        Hand hand = new Hand("9d", "3c", "3d", "3h", "3s");
        assertEquals(12, Scoring.fifteens(hand));
    }

    @Test
    public void test24e() {
        Hand hand = new Hand("4c", "4d", "5d", "5h", "6s");
        assertEquals(8, Scoring.fifteens(hand));
    }

    @Test
    public void test24g() {
        Hand hand = new Hand("6c", "7d", "7s", "8h", "8s");
        assertEquals(8, Scoring.fifteens(hand));
    }

    @Test
    public void test28a() {
        Hand hand = new Hand("jd", "5d", "5h", "5s", "5c");
        assertEquals(16, Scoring.fifteens(hand));
    }

    @Test
    public void test28b() {
        Hand hand = new Hand("5c", "5d", "5h", "5s", "tc");
        assertEquals(16, Scoring.fifteens(hand));
    }
}
