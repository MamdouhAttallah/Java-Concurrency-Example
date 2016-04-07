package model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Check different combinations of hands and their scores.
 * 
 * @author jim
 */
public class ScoringCombosTest implements CribbageConstants {

    public ScoringCombosTest() {
    }

    @Test
    public void test2a() {
        Hand hand = new Hand("4c", "4d");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2b() {
        Hand hand = new Hand("7c", "8d");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2c() {
        Hand hand = new Hand("5c", "qd");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2d() {
        Hand hand = new Hand("kc", "5c");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2e() {
        Hand hand = new Hand("4c", "4d", "5h");
       assertEquals(2, Scoring.pairs(hand));
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2f() {
        Hand hand = new Hand("4c", "5d", "4h");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2g() {
        Hand hand = new Hand("kc", "5c", "as");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2h() {
        Hand hand = new Hand("kc", "4c", "as");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2i() {
        Hand hand = new Hand("kc", "2c", "js", "2d", "4c");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

    @Test
    public void test2j() {
        Hand hand = new Hand("kc", "3d", "js", "3h");
        assertEquals(2, Scoring.score(hand, false));
        assertEquals(2, Scoring.score(hand, true));
    }

//    @Test
//    public void test3a() {
//        Hand hand = new Hand("kc", "qc", "js");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test3b() {
//        Hand hand = new Hand("kc", "qc", "jc");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test3c() {
//        Hand hand = new Hand("jc", "qc", "ks");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test3d() {
//        Hand hand = new Hand("kc", "2c", "js", "2d", "4s");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test3e() {
//        Hand hand = new Hand("jc", "qc", "ks", "4c");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test3f() {
//        Hand hand = new Hand("jc", "qc", "ks", "4c", "6s");
//        assertEquals(3, Scoring.score(hand, false));
//        assertEquals(3, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4a() {
//        Hand hand = new Hand("jc", "jd", "4s", "4c");
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4b() {
//        Hand hand = new Hand("jc", "5d", "4s", "4c");
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4c() {
//        Hand hand = new Hand("jc", "jd", "4s", "4c", "6s");
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4d() {
//        Hand hand = new Hand("jc", "jd", "7s", "8c", "2h");
//
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4e() {
//        Hand hand = new Hand("jc", "5d", "7s", "8c", "ah");
//
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4f() {
//        Hand hand = new Hand("jc", "5d", "7s", "8c");
//
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(4, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test4g() {
//        Hand hand = new Hand("jc", "4c", "7c", "2c", "th");
//
//        assertEquals(4, Scoring.score(hand, false));
//        assertEquals(0, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test5a() {
//        Hand hand = new Hand("jc", "5d", "7s", "8c", "ac");
//
//        assertEquals(5, Scoring.score(hand, false));
//        assertEquals(5, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test5b() {
//        Hand hand = new Hand("3c", "3d", "ts", "9c", "jh");
//
//        assertEquals(5, Scoring.score(hand, false));
//        assertEquals(5, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test5c() {
//        Hand hand = new Hand("8c", "9d", "ts", "jc", "qh");
//
//        assertEquals(5, Scoring.score(hand, false));
//        assertEquals(5, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test5d() {
//        Hand hand = new Hand("7c", "9d", "ts", "jh", "qh");
//
//        assertEquals(5, Scoring.score(hand, false));
//        assertEquals(5, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test5e() {
//        Hand hand = new Hand("tc", "4c", "7c", "2c", "jc");
//
//        assertEquals(5, Scoring.score(hand, false));
//        assertEquals(5, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6a() {
//        Hand hand = new Hand("2c", "2d", "2h", "4c", "tc");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6b() {
//        Hand hand = new Hand("5c", "5d", "2h", "4c", "tc");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6c() {
//        Hand hand = new Hand("jc", "4c", "7c", "2c", "tc");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6d() {
//        Hand hand = new Hand("ac", "2d", "3s", "4c", "7h");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6e() {
//        Hand hand = new Hand("ac", "2c", "6c", "4c", "7h");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test6f() {
//        Hand hand = new Hand("3c", "4d", "6c", "5s");
//        assertEquals(6, Scoring.score(hand, false));
//        assertEquals(6, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test12a() {
//        Hand hand = new Hand("4c", "4d", "4h", "3c", "3d");
//        assertEquals(12, Scoring.score(hand, false));
//        assertEquals(12, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test12b() {
//        Hand hand = new Hand("2c", "2d", "3h", "8c", "2s");
//        assertEquals(12, Scoring.score(hand, false));
//        assertEquals(12, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test13a() {
//        Hand hand = new Hand("ac", "ad", "6h", "7c", "8s");
//        assertEquals(13, Scoring.score(hand, false));
//        assertEquals(13, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test13b() {
//        Hand hand = new Hand("ac", "4d", "4h", "jc", "4c");
//        assertEquals(13, Scoring.score(hand, false));
//        assertEquals(13, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test14a() {
//        Hand hand = new Hand("3c", "3d", "6h", "6c", "9s");
//        assertEquals(14, Scoring.score(hand, false));
//        assertEquals(14, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test14b() {
//        Hand hand = new Hand("4c", "4d", "7h", "7c", "7s");
//        assertEquals(14, Scoring.score(hand, false));
//        assertEquals(14, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test15a() {
//        Hand hand = new Hand("ac", "2d", "3h", "3c", "3s");
//        assertEquals(15, Scoring.score(hand, false));
//        assertEquals(15, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test16a() {
//        Hand hand = new Hand("ac", "ad", "2h", "2c", "3s");
//        assertEquals(16, Scoring.score(hand, false));
//        assertEquals(16, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test16b() {
//        Hand hand = new Hand("2c", "2d", "3h", "4c", "3s");
//        assertEquals(16, Scoring.score(hand, false));
//        assertEquals(16, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test16c() {
//        Hand hand = new Hand("2c", "6d", "7h", "7c", "8s");
//        assertEquals(16, Scoring.score(hand, false));
//        assertEquals(16, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test17a() {
//        Hand hand = new Hand("2c", "3d", "3h", "3s", "4s");
//        assertEquals(17, Scoring.score(hand, false));
//        assertEquals(17, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test18a() {
//        Hand hand = new Hand("3c", "3d", "3h", "6s", "6d");
//        assertEquals(18, Scoring.score(hand, false));
//        assertEquals(18, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test17b() {
//        Hand hand = new Hand("2c", "3d", "4h", "4c", "4s");
//        assertEquals(17, Scoring.score(hand, false));
//        assertEquals(17, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test20a() {
//        Hand hand = new Hand("2c", "2d", "2h", "2s", "9c");
//        assertEquals(20, Scoring.score(hand, false));
//        assertEquals(20, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test20b() {
//        Hand hand = new Hand("3c", "3d", "3h", "3s", "6c");
//        assertEquals(20, Scoring.score(hand, false));
//        assertEquals(20, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test21a() {
//        Hand hand = new Hand("3c", "3d", "3h", "4s", "5c");
//        assertEquals(21, Scoring.score(hand, false));
//        assertEquals(21, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24a() {
//        Hand hand = new Hand("ad", "7c", "7d", "7h", "7s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24b() {
//        Hand hand = new Hand("9d", "3c", "3d", "3h", "3s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24c() {
//        Hand hand = new Hand("3d", "6c", "6d", "6h", "6s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24d() {
//        Hand hand = new Hand("7d", "4c", "4d", "4h", "4s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24e() {
//        Hand hand = new Hand("4c", "4d", "5d", "5h", "6s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24f() {
//        Hand hand = new Hand("4c", "4d", "5d", "6h", "6s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24g() {
//        Hand hand = new Hand("6c", "7d", "7d", "8h", "8s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test24h() {
//        Hand hand = new Hand("7c", "7d", "8d", "8h", "9s");
//        assertEquals(24, Scoring.score(hand, false));
//        assertEquals(24, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test28a() {
//        Hand hand = new Hand("jd", "5d", "5h", "5s", "5c");
//        assertEquals(28, Scoring.score(hand, false));
//        assertEquals(28, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test28b() {
//        Hand hand = new Hand("5c", "5d", "5h", "5s", "tc");
//        assertEquals(28, Scoring.score(hand, false));
//        assertEquals(28, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test28c() {
//        Hand hand = new Hand("5c", "5d", "5h", "5s", "jc");
//        assertEquals(28, Scoring.score(hand, false));
//        assertEquals(28, Scoring.score(hand, true));
//    }
//
//    @Test
//    public void test29a() {
//        Hand hand = new Hand("jc", "5d", "5h", "5s", "5c");
//        assertEquals(29, Scoring.score(hand, false));
//        assertEquals(29, Scoring.score(hand, true));
//    }
}
