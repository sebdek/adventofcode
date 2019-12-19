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
            System.out.println(outputList);
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
            if (instruction == 99) {
                fragmentLength = 1;
                opcodeFragment.add(instruction);
            } else {
                int donePart = Math.floorDiv(instruction, (int) Math.pow(10, 4));
                for (int j = 3; j >= 0; j--) {
                    int currentDigit = Math.floorDiv(instruction, (int) Math.pow(10, j)) - donePart * 10;
                    if (donePart > 0 || (donePart == 0 && currentDigit != 0)) {
                        opcodeFragment.add(currentDigit);
                        fragmentLength++;
                    }
                    donePart = donePart * 10 + currentDigit;
                }
            }
            int lastIndex = 0;
            int opcode = opcodeFragment.get(fragmentLength - 1);
            if (opcode == 1 || opcode == 2) {
                lastIndex = opcodePosition + 4;
            } else if (instruction == 99) {
                lastIndex = opcodePosition + 1;
            } else if (opcode == 3 || opcode == 4) {
                lastIndex = opcodePosition + 2;
            }
            List<Integer> positionValueList = processOpcodeFragment(intcodeList,
                    intcodeList.subList(opcodePosition, lastIndex), fragmentLength, opcodeFragment, input, opcode);
            if (positionValueList.get(1) == -1) {
                opcodePosition = intcodeList.size();
            } else {
                if (positionValueList.get(0) == -1) {
                    outputList.add(positionValueList.get(1));
                } else {
                    intcodeList.set(positionValueList.get(0), positionValueList.get(1));
                }
                opcodePosition = lastIndex;
            }
        }
        return intcodeList;
    }

    protected static List<Integer> processOpcodeFragment(List<Integer> intcodeList, List<Integer> data, int fragmentLength, List<Integer> instructions, int input, int opcode) {
        int position = getPositionFromData(data, opcode);
        int result = getResultFromOpcode(intcodeList, data, instructions, fragmentLength, input, opcode);
        List<Integer> positionValueList = new ArrayList<>();
        positionValueList.add(position);
        positionValueList.add(result);
        return positionValueList;
    }

    private static int getPositionFromData(List<Integer> data, int opcode) {
        if (opcode == 1 || opcode == 2) {
            return data.get(3);
        } else if (opcode == 3) {
            return data.get(1);
        }
        return -1;
    }

    private static int getResultFromOpcode(List<Integer> intcodeList, List<Integer> data, List<Integer> instructions, int fragmentLength, int input, int opcode) {
        int result;
        if (fragmentLength >= 2) {
            opcode += instructions.get(fragmentLength - 2) * 10;
        }
        switch (opcode) {
            case 1:
                result = getParameter(intcodeList, data, instructions, 1)
                        + getParameter(intcodeList, data, instructions, 2);
                break;
            case 2:
                result = getParameter(intcodeList, data, instructions, 1)
                        * getParameter(intcodeList, data, instructions, 2);
                break;
            case 3:
                result = input;
                break;
            case 4:
                result = getParameter(intcodeList, data, instructions, 1);
                break;
            case 99:
                result = -1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        return result;
    }

    private static Integer getParameter(List<Integer> intcodeList, List<Integer> data, List<Integer> instructions, int parameterNumber) {
        int position = instructions.size() - parameterNumber - 2;
        if (instructions.size() > 1) {
            if (position < 0 || instructions.get(position) == 0) {
                return intcodeList.get(data.get(parameterNumber));
            } else {
                return data.get(parameterNumber);
            }
        } else {
            return intcodeList.get(data.get(parameterNumber));
        }
    }

}
