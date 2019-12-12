package fr.sebdek.adventofcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.sebdek.adventofcode.Day4Test1.matchCriteria;

public class Day4Test2 {

    public static void main(String[] args) {
        int min = 130254;
        int max = 678275;
        List<Integer> matchingPasswords = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (matchCriteria(i) && matchGroupCriteria(i)) {
                matchingPasswords.add(i);
            }
        }
        System.out.println("number matching : " + matchingPasswords.size());
        System.out.println(matchingPasswords);
    }

    public static boolean matchGroupCriteria(int number) {
        Map<Integer, Integer> numberDistribution = new HashMap<>();
        int donePart = Math.floorDiv(number, 100000);
        numberDistribution.put(donePart, 1);
        for (int j = 10000; j >= 1; j = j / 10) {
            int currentInt = Math.floorDiv(number, j) - donePart *10;
            numberDistribution.merge(currentInt, 1, Integer::sum);
            donePart = donePart * 10 + currentInt;
        }
        return numberDistribution.containsValue(2);
    }

}
