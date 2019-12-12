package fr.sebdek.adventofcode;

import fr.sebdek.adventofcode.Day3Test1.Position;
import fr.sebdek.adventofcode.Day3Test1.Wire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fr.sebdek.adventofcode.Day3Test1.generatePositions;

public class Day3Test2 {

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("day3_test2.txt"))) {
            List<Wire> wires = lines
                    .map(Wire::new)
                    .collect(Collectors.toList());
            Map<Integer, List<Position>> positions1 = generatePositions(wires.get(0));
            Map<Integer, List<Position>> positions2 = generatePositions(wires.get(1));
            Position leastStepIntersection = findLeastStepPosition(positions1, positions2);
            System.out.println(leastStepIntersection);
        }
    }

    public static Position findLeastStepPosition(Map<Integer, List<Position>> positions1, Map<Integer, List<Position>> positions2) {
        Optional<Position> leastStepIntersection = positions1.entrySet().stream()
                .map(entry -> {
                    Position leastStepCommonPoint = null;
                    int currentDistance = entry.getKey();
                    if (positions2.get(currentDistance) != null) {
                        AtomicInteger commonPositionIndex = new AtomicInteger();
                        Optional<Position> onePosition = positions2.get(currentDistance).stream()
                                .filter(position -> {
                                    commonPositionIndex.set(entry.getValue().indexOf(position));
                                    return commonPositionIndex.get() != -1;
                                })
                                .findFirst();
                        if (onePosition.isPresent()) {
                            leastStepCommonPoint = onePosition.get();
                            leastStepCommonPoint.setStep(leastStepCommonPoint.getStep() + entry.getValue().get(commonPositionIndex.get()).getStep());
                        }
                    }
                    return leastStepCommonPoint;
                })
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(Position::getStep));
        return leastStepIntersection.orElse(null);
    }

}
