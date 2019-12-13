package fr.sebdek.adventofcode.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.sebdek.adventofcode.Day5Test1.processIntcode;


public class Day5Test1Test {

    @Test
    public void dataTest1() {
        String intcode = "3,0,4,0,99";
        int input = 3;
        List<Integer> outputList = new ArrayList<>();
        List<Integer> intcodeList = processIntcode(intcode, input, outputList);
        System.out.println(intcodeList);
        assert outputList.get(0) == input;
    }

}
