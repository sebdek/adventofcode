package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Test1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day2_test1.txt"))) {
            String intcode = reader.readLine();
            List<Integer> intcodeList = processIntcode(intcode);
            System.out.println(intcodeList);
        }
    }

    private static List<Integer> processIntcode(String intcode) {
        List<Integer> intcodeList = Arrays.stream(intcode.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        for (int i = 0; i < intcodeList.size(); i = i + 4) {
            List<Integer> positionValueList = processOpcodeFragment(intcodeList, intcodeList.subList(i, i + 4) );
            if (positionValueList.get(1) == -1) {
                break;
            } else {
                intcodeList.set(positionValueList.get(0), positionValueList.get(1));
            }
        }
        return intcodeList;
    }

    protected static List<Integer> processOpcodeFragment(List<Integer> intcodeList, List<Integer> opcodeFragment) {
        int opcode = opcodeFragment.get(0);
        int result = getResultFromOpcode(intcodeList, opcodeFragment, opcode);
        List<Integer> positionValueList = new ArrayList<>();
        if (opcodeFragment.size() > 3) {
            positionValueList.add(opcodeFragment.get(3));
        } else {
            positionValueList.add(-1);
        }
        positionValueList.add(result);
        return positionValueList;
    }

    private static int getResultFromOpcode(List<Integer> intcodeList, List<Integer> opcodeFragment, int opcode) {
        int result = 0;
        switch (opcode) {
            case 1:
                if (opcodeFragment.size() > 2) {
                    result = intcodeList.get(opcodeFragment.get(1)) + intcodeList.get(opcodeFragment.get(2));
                }
                break;
            case 2:
                if (opcodeFragment.size() > 2) {
                    result = intcodeList.get(opcodeFragment.get(1)) * intcodeList.get(opcodeFragment.get(2));
                }
                break;
            case 99:
                result = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        return result;
    }

}
