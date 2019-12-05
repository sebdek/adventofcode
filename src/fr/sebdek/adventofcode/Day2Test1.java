package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Test1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day2_test1.txt"))) {
            String intcode = reader.readLine();
            String[] intcodeArray = intcode.split(",");
            for (int i = 0; i < intcodeArray.length; i = i + 4) {
                List<Integer> positionValueList = processOpcodeFragment(intcodeArray, Arrays.copyOfRange(intcodeArray, i, i + 4));
                if (positionValueList.get(1) == -1) {
                    break;
                } else {
                    intcodeArray[positionValueList.get(0)] = String.valueOf(positionValueList.get(1));
                }
            }
            System.out.println(String.join(",", intcodeArray));
        }
    }

    private static List<Integer> processOpcodeFragment(String[] intcodeArray, String[] opcodeFragment) {
        int opcode = Integer.parseInt(opcodeFragment[0]);
        int result = 0;
        switch (opcode) {
            case 1:
                if (opcodeFragment[1] != null && opcodeFragment[2] != null) {
                    result = Integer.parseInt(intcodeArray[Integer.parseInt(opcodeFragment[1])]) + Integer.parseInt(intcodeArray[Integer.parseInt(opcodeFragment[2])]);
                }
                break;
            case 2:
                if (opcodeFragment[1] != null && opcodeFragment[2] != null) {
                    result = Integer.parseInt(intcodeArray[Integer.parseInt(opcodeFragment[1])]) * Integer.parseInt(intcodeArray[Integer.parseInt(opcodeFragment[2])]);
                }
                break;
            case 99:
                result = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        List<Integer> positionValueList = new ArrayList<>();
        if (opcodeFragment[3] != null) {
            positionValueList.add(Integer.parseInt(opcodeFragment[3]));
        } else {
            positionValueList.add(-1);
        }
        positionValueList.add(result);
        return positionValueList;
    }

}
