package fr.sebdek.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day3Test1 {

    private static int result = 19690720;

    public static void main(String[] args) throws IOException {
        String line1 = "R8,U5,L5,D3";
        String line2 = "U7,R6,D4,L4";
        Map<Integer, Integer> positions1 = new HashMap<>();
        Map<Integer, Integer> positions2 = new HashMap<>();
        positions1.put(0, 0);
        positions2.put(0, 0);
        Files.lines(Paths.get("day3_test1.txt"));
    }
}
