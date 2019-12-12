package fr.sebdek.adventofcode.test;

import org.junit.Test;

import static fr.sebdek.adventofcode.Day4Test1.matchCriteria;

public class Day4Test1Test {

    @Test
    public void basicTest() {
        int test = 123456;
        int previous = Math.floorDiv(test, 100000);
        assert previous == 1;
        int current = Math.floorDiv(test, 10000) - previous * 10;
        previous = previous * 10 + current;
        assert current == 2;
        current = Math.floorDiv(test, 1000) - previous * 10;
        previous = previous * 10 + current;
        assert current == 3;
        current = Math.floorDiv(test, 100) - previous * 10;
        previous = previous * 10 + current;
        assert current == 4;
        current = Math.floorDiv(test, 10) - previous * 10;
        previous = previous * 10 + current;
        assert current == 5;
        current = Math.floorDiv(test, 1) - previous * 10;
        assert current == 6;
    }

    @Test
    public void dataTest1() {
        assert matchCriteria(111111);
    }

    @Test
    public void dataTest2() {
        assert !matchCriteria(223450);
    }

    @Test
    public void dataTest3() {
        assert !matchCriteria(123789);
    }

    @Test
    public void dataTest4() {
        assert matchCriteria(234566);
    }

}