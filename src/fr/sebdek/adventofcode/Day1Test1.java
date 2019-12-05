package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day1Test1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day1_test1.txt"))) {
            int requiredFuel = 0;
            String line = reader.readLine();
            while (line != null) {
                requiredFuel += calculateFuelRequirementForMass(Integer.parseInt(line));
                line = reader.readLine();
            }
            System.out.println(requiredFuel);
        }
    }

    protected static int calculateFuelRequirementForMass(int masse) {
        return Math.floorDiv(masse, 3) - 2;
    }
}
