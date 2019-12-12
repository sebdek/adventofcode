package fr.sebdek.adventofcode.test;

import org.junit.Test;

import static fr.sebdek.adventofcode.Day4Test1.matchCriteria;
import static fr.sebdek.adventofcode.Day4Test2.matchGroupCriteria;

public class Day4Test2Test {

    @Test
    public void dataTest1() {
        assert matchGroupCriteria(112233);
    }

    @Test
    public void dataTest2() {
        assert !matchGroupCriteria(123444);
    }

    @Test
    public void dataTest3() {
        assert matchGroupCriteria(111122);
    }

}