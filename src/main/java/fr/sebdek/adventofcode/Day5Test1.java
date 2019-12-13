package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5Test1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day5_test1.txt"))) {
            String intcode = reader.readLine();
            int input = 1;
            List<Integer> outputList = new ArrayList<>();
            List<Integer> intcodeList = processIntcode(intcode, input, outputList);
            System.out.println(intcodeList);
        }
    }

    public static List<Integer> processIntcode(String intcode, int input, List<Integer> outputList) {
        List<Integer> intcodeList = Arrays.stream(intcode.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int opcodePosition = 0;
        while (opcodePosition < intcodeList.size()) {
            int fragmentLength = 0;
            List<Integer> opcodeFragment = new ArrayList<>();
            int instruction = intcodeList.get(opcodePosition);
            int donePart = Math.floorDiv(instruction, (int) Math.pow(10, 4));
            for (int j = 3; j >= 0; j --) {
                int currentDigit = Math.floorDiv(instruction, (int) Math.pow(10, j)) - donePart *10;
                if (donePart > 0 || j <= 1) {
                    opcodeFragment.add(currentDigit);
                    fragmentLength ++;
                }
                donePart = donePart * 10 + currentDigit;
            }
            List<Integer> positionValueList = processOpcodeFragment(intcodeList,
                    intcodeList.subList(opcodePosition, opcodePosition + fragmentLength), fragmentLength, opcodeFragment, input);
            if (positionValueList.get(1) == -1) {
                opcodePosition = intcodeList.size();
            } else {
                if (positionValueList.get(0) != 0) {
                    intcodeList.set(positionValueList.get(0), positionValueList.get(1));
                } else {
                    outputList.add(positionValueList.get(1));
                }
            }
            opcodePosition = opcodePosition + fragmentLength;
        }
        return intcodeList;
    }

    protected static List<Integer> processOpcodeFragment(List<Integer> intcodeList, List<Integer> data, int fragmentLength, List<Integer> instructions, int input) {
        int position = getPositionFromData(data, fragmentLength);
        int result = getResultFromOpcode(intcodeList, data, instructions, fragmentLength, input);
        List<Integer> positionValueList = new ArrayList<>();
        positionValueList.add(position);
        positionValueList.add(result);
        return positionValueList;
    }

    private static int getPositionFromData(List<Integer> data, int fragmentLength) {
        if (fragmentLength >= 4) {
            return data.get(3);
        } else if (fragmentLength == 2) {
            return data.get(1);
        }
        return 0;
    }

    private static int getResultFromOpcode(List<Integer> intcodeList, List<Integer> data, List<Integer> instructions, int fragmentLength, int input) {
        int result = 0;
        int opcode = instructions.get(fragmentLength - 1);
        switch (opcode) {
            case 1:
                if (fragmentLength >= 4) {
                    result = getParameter(intcodeList, data, instructions, fragmentLength, 1)
                            + getParameter(intcodeList, data, instructions, fragmentLength, 2);
                }
                break;
            case 2:
                if (fragmentLength >= 4) {
                    result = getParameter(intcodeList, data, instructions, fragmentLength, 1)
                            * getParameter(intcodeList, data, instructions, fragmentLength, 2);
                }
                break;
            case 3:
                result = input;
                break;
            case 4:
                result = intcodeList.get(data.get(1));
                break;
            case 99:
                result = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        return result;
    }

    private static Integer getParameter(List<Integer> intcodeList, List<Integer> data, List<Integer> instructions, int fragmentLength, int parameterNumber) {
        if (instructions.get(fragmentLength - (parameterNumber + 1)) == 0) {
            return intcodeList.get(data.get(parameterNumber));
        } else if (instructions.get(fragmentLength - (parameterNumber + 1)) == 1) {
            return data.get(parameterNumber);
        }
        return 0;
    }

}
