package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2Test2 {

    private static int result = 19690720;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day2_test2.txt"))) {
            String intcode = reader.readLine();
            final List<Integer> intcodeList = Arrays.stream(intcode.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            int noun = 0;
            int verb = 0;
            List<Integer> workingList = new ArrayList<>();
            for (int n = 0; n < 100; n ++) {
                for (int v = 0; v < 100; v ++) {
                    workingList.clear();
                    workingList.addAll(intcodeList);
                    workingList.set(1, n);
                    workingList.set(2, v);
                    for (int i = 0; i < workingList.size(); i = i + 4) {
                        List<Integer> positionValueList = Day2Test1.processOpcodeFragment(workingList, workingList.subList(i, i + 4));
                        if (positionValueList.get(1) == -1) {
                            break;
                        } else {
                            workingList.set(positionValueList.get(0), positionValueList.get(1));
                        }
                    }
                    if (workingList.get(0) == result) {
                        noun = n;
                        verb = v;
                        break;
                    }
                }
                if (noun != 0 && verb != 0) {
                    break;
                }
            }
            System.out.println("result " + workingList.get(0));
            System.out.println("noun " + noun);
            System.out.println("verb " + verb);
            System.out.println("answer " + (100 * noun + verb));
        }
    }

}
