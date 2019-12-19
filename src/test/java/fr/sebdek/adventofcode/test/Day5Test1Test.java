package fr.sebdek.adventofcode.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.sebdek.adventofcode.Day5Test1.processIntcode;


public class Day5Test1Test {

    @Test
    public void dataTest1() {
        String intcode = "3,0,4,0,99";
        int input = 10;
        List<Integer> outputList = new ArrayList<>();
        List<Integer> intcodeList = processIntcode(intcode, input, outputList);
        System.out.println(intcodeList);
        System.out.println(outputList);
        assert outputList.get(0) == input;
    }

    @Test
    public void dataTest2() {
        String intcode = "1002,4,3,4,33";
        int input = 10;
        List<Integer> outputList = new ArrayList<>();
        List<Integer> intcodeList = processIntcode(intcode, input, outputList);
        System.out.println(intcodeList);
        System.out.println(outputList);
        assert outputList.size() == 0;
        assert intcodeList.get(4) == 99;
    }

    @Test
    public void dataTest3() {
        String intcode = "1101,100,-1,4,0";
        int input = 10;
        List<Integer> outputList = new ArrayList<>();
        List<Integer> intcodeList = processIntcode(intcode, input, outputList);
        System.out.println(intcodeList);
        System.out.println(outputList);
        assert outputList.size() == 0;
        assert intcodeList.get(4) == 99;
    }

}
