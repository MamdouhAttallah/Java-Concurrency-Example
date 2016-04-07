package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Check that runs are counted correctly.
 *
 * @author jim
 */
public class ScoringRunsTest implements CribbageConstants {

    public ScoringRunsTest() {
    }

    @Test
    public void test3x() {
        Hand hand = new Hand("kc", "qc");
        assertEquals(0, Scoring.runs(hand));
    }

    @Test
    public void test3y() {
        Hand hand = new Hand("kc", "qc", "ad", "2d", "3d", "4d");
        assertEquals(0, Scoring.runs(hand));
    }

    @Test
    public void test3a() {
        Hand hand = new Hand("kc", "qc", "js");
        assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test3b() {
        Hand hand = new Hand("kc", "qc", "jc");
         assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test3c() {
        Hand hand = new Hand("jc", "qc", "ks");
        assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test3e() {
        Hand hand = new Hand("jc", "qc", "ks", "4c");
        assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test3f() {
        Hand hand = new Hand("jc", "qc", "ks", "4c", "6s");
         assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test5c() {
        Hand hand = new Hand("8c", "9d", "ts", "jc", "qh");
        assertEquals(5, Scoring.runs(hand));
    }

    @Test
    public void test5d() {
        Hand hand = new Hand("7c", "9d", "ts", "jh", "qh");
        assertEquals(4, Scoring.runs(hand));
    }

    @Test
    public void test6d() {
        Hand hand = new Hand("ac", "2d", "3s", "4c", "7h");
        assertEquals(4, Scoring.runs(hand));
    }

    @Test
    public void test6f() {
        Hand hand = new Hand("3c", "4d", "6c", "5s");
        assertEquals(4, Scoring.runs(hand));
    }

    @Test
    public void test13a() {
        Hand hand = new Hand("ac", "ad", "6h", "7c", "8s");
        assertEquals(3, Scoring.runs(hand));
    }

    @Test
    public void test15a() {
        Hand hand = new Hand("ac", "2d", "3h", "3c", "3s");
        assertEquals(9, Scoring.runs(hand));
    }

    @Test
    public void test16a() {
        Hand hand = new Hand("ac", "ad", "2h", "2c", "3s");
        assertEquals(12, Scoring.runs(hand));
    }

    @Test
    public void test16b() {
        Hand hand = new Hand("2c", "2d", "3h", "4c", "3s");
        assertEquals(12, Scoring.runs(hand));
    }

    @Test
    public void test16c() {
        Hand hand = new Hand("2c", "6d", "7h", "7c", "8s");
        assertEquals(6, Scoring.runs(hand));
    }

    @Test
    public void test17a() {
        Hand hand = new Hand("2c", "3d", "3h", "3s", "4s");
        assertEquals(9, Scoring.runs(hand));
    }

    @Test
    public void test17b() {
        Hand hand = new Hand("2c", "3d", "4h", "4c", "4s");
        assertEquals(9, Scoring.runs(hand));
    }

    @Test
    public void test21a() {
        Hand hand = new Hand("3c", "3d", "3h", "4s", "5c");
        assertEquals(9, Scoring.runs(hand));
    }

    @Test
    public void test24e() {
        Hand hand = new Hand("4c", "4d", "5d", "5h", "6s");
        assertEquals(12, Scoring.runs(hand));
    }

    @Test
    public void test24f() {
        Hand hand = new Hand("4c", "4d", "5d", "6h", "6s");
        assertEquals(12, Scoring.runs(hand));
    }

    @Test
    public void test24g() {
        Hand hand = new Hand("6c", "7d", "9d", "8h", "8s");
        assertEquals(8, Scoring.runs(hand));
    }
}
