package fr.sebdek.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day4Test1 {

    public static void main(String[] args) {
        int min = 130254;
        int max = 678275;
        List<Integer> matchingPasswords = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (matchCriteria(i)) {
                matchingPasswords.add(i);
            }
        }
        System.out.println("number matching : " + matchingPasswords.size());
        System.out.println(matchingPasswords);
    }

    public static boolean matchCriteria(int number) {

        //one double present && all increase
        int donePart = Math.floorDiv(number, (int) Math.pow(10, 5));
        int previousInt = donePart;
        boolean doublePresent = false;
        boolean allIncrease = true;
        for (int j = 4; j >= 1; j --) {
            int currentInt = Math.floorDiv(number, (int) Math.pow(10, j)) - donePart *10;
            if (currentInt == previousInt) {
                doublePresent = true;
            }
            if (currentInt < previousInt) {
                allIncrease = false;
            }
            previousInt = currentInt;
            donePart = donePart * 10 + currentInt;
        }

        //all good
        return doublePresent && allIncrease;
    }

}
