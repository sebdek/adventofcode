package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1Test2 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day1_test2.txt"))) {
            int requiredFuel = 0;
            String line = reader.readLine();
            while (line != null) {
                requiredFuel += calculateAdjustedFuelRequirementForMass(Integer.parseInt(line));
                line = reader.readLine();
            }
            System.out.println(requiredFuel);
        }
    }

    private static int calculateAdjustedFuelRequirementForMass(int masse) {
        int total = 0;
        int requiredFuel = Day1Test1.calculateFuelRequirementForMass(masse);
        while (requiredFuel > 0) {
            total += requiredFuel;
            requiredFuel = Day1Test1.calculateFuelRequirementForMass(requiredFuel);
        }
        return total;
    }

}
