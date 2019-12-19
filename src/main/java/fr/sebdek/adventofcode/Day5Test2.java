package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static fr.sebdek.adventofcode.Day5Test1.getParameter;

public class Day5Test2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day5_test1.txt"))) {
            String intcode = reader.readLine();
            int input = 5;
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
            switch (opcode) {
                case 1:
                case 2:
                case 7:
                case 8:
                    lastIndex = opcodePosition + 4;
                    break;
                case 3:
                case 4:
                    lastIndex = opcodePosition + 2;
                    break;
                case 5:
                case 6:
                    lastIndex = opcodePosition + 3;
                    break;
                default:
                    lastIndex = opcodePosition + 1;
                    break;
            }
            List<Integer> positionValueList = processOpcodeFragment(intcodeList,
                    intcodeList.subList(opcodePosition, lastIndex), fragmentLength, opcodeFragment, input, opcode);
            if (positionValueList.get(1) == -2) {
                opcodePosition = intcodeList.size();
            } else {
                if (positionValueList.get(0) == -1) {
                    outputList.add(positionValueList.get(1));
                } else if (positionValueList.get(0) != -2) {
                    intcodeList.set(positionValueList.get(0), positionValueList.get(1));
                }
                opcodePosition = positionValueList.get(0) == -2 && positionValueList.get(1) >= 0 ? positionValueList.get(1) : lastIndex;
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
        switch (opcode) {
            case 1:
            case 2:
            case 7:
            case 8:
                return data.get(3);
            case 3:
                return data.get(1);
            case 5:
            case 6:
                return -2;
            default:
                return -1;
        }
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
            case 5:
                result = getParameter(intcodeList, data, instructions, 1).equals(0) ? -1 : getParameter(intcodeList, data, instructions, 2);
                break;
            case 6:
                result = getParameter(intcodeList, data, instructions, 1).equals(0) ? getParameter(intcodeList, data, instructions, 2) : -1;
                break;
            case 7:
                result = getParameter(intcodeList, data, instructions, 1) < getParameter(intcodeList, data, instructions, 2) ? 1 : 0;
                break;
            case 8:
                result = getParameter(intcodeList, data, instructions, 1).equals(getParameter(intcodeList, data, instructions, 2)) ? 1 : 0;
                break;
            case 99:
                result = -2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + opcode);
        }
        return result;
    }

}
