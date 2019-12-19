package fr.sebdek.adventofcode.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static fr.sebdek.adventofcode.Day5Test2.processIntcode;


public class Day5Test2Test {

    @Test
    public void dataTest1() {
        final String intcode = "3,9,8,9,10,9,4,9,99,-1,8";
        int input = 8;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
        input = 100;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
    }

    @Test
    public void dataTest2() {
        String intcode = "3,9,7,9,10,9,4,9,99,-1,8";
        int input = 7;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
        input = 12;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
    }

    @Test
    public void dataTest3() {
        String intcode = "3,3,1108,-1,8,3,4,3,99";
        int input = 8;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
        input = 56;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
    }

    @Test
    public void dataTest4() {
        String intcode = "3,3,1107,-1,8,3,4,3,99";
        int input = 3;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
        input = 45;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
    }

    @Test
    public void dataTest5() {
        String intcode = "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9";
        int input = 0;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
        input = 57;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
    }

    @Test
    public void dataTest6() {
        String intcode = "3,3,1105,-1,9,1101,0,0,12,4,12,99,1";
        int input = 0;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 0;
        input = 34;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1;
    }

    @Test
    public void complexDataTest() {
        String intcode = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        int input = 4;
        List<Integer> outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 999;
        input = 8;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1000;
        input = 21;
        outputList = new ArrayList<>();
        processIntcode(intcode, input, outputList);
        assert outputList.get(0) == 1001;
    }

}
