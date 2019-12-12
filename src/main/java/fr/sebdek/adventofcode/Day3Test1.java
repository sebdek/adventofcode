package fr.sebdek.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3Test1 {

    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("day3_test1.txt"))) {
            List<Wire> wires = lines
                    .map(Wire::new)
                    .collect(Collectors.toList());
            Map<Integer, List<Position>> positions1 = generatePositions(wires.get(0));
            Map<Integer, List<Position>> positions2 = generatePositions(wires.get(1));
            Position closestIntersection = findClosestPosition(positions1, positions2);
            System.out.println(closestIntersection);
        }
    }

    public static Position findClosestPosition(Map<Integer, List<Position>> positions1, Map<Integer, List<Position>> positions2) {
        Optional<Position> closestPosition = positions1.entrySet().stream()
                .map(entry -> {
                    Position closestCommonPoint = null;
                    int currentDistance = entry.getKey();
                    if (positions2.get(currentDistance) != null) {
                        Optional<Position> onePosition = positions2.get(currentDistance).stream()
                                .filter(position -> entry.getValue().indexOf(position) != -1)
                                .findFirst();
                        if (onePosition.isPresent()) {
                            closestCommonPoint = onePosition.get();
                        }
                    }
                    return closestCommonPoint;
                })
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(Position::getDistance));
        return closestPosition.orElse(null);
    }

    public static class Wire {

        List<Movement> movements;

        public Wire(String path) {
            this.movements = Arrays.stream(path.split(","))
                    .map(s -> new Movement(s.substring(0, 1), Integer.parseInt(s.substring(1))))
                    .collect(Collectors.toList());
        }

        public List<Movement> getMovements() {
            return movements;
        }

        public void setMovements(List<Movement> movements) {
            this.movements = movements;
        }
    }

    public static class Movement {

        String direction;
        int value;

        public String getDirection() {
            return direction;
        }

        public int getValue() {
            return value;
        }

        public Movement(String direction, int value) {

            switch (direction) {
                case "R":
                case "U":
                    this.direction = direction;
                    this.value = value;
                    break;
                case "L":
                    this.direction = "R";
                    this.value = -value;
                    break;
                case "D":
                    this.direction = "U";
                    this.value = -value;
                    break;
                default:
                    break;
            }

        }

    }

    public static class Position {

        int x;
        int y;
        int distance;
        int step;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistance() {
            return distance;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public Position(Position previousPosition, Movement movement) {
            if (previousPosition == null) {
                if ("R".equals(movement.getDirection())) {
                    this.x = movement.getValue();
                    this.y = 0;
                } else {
                    this.x = 0;
                    this.y = movement.getValue();
                }
                this.distance = Math.abs(x) +  Math.abs(y);
                this.step = 1;
            } else {
                if ("R".equals(movement.getDirection())) {
                    this.x = previousPosition.getX() + movement.getValue();
                    this.y = previousPosition.getY();
                } else {
                    this.x = previousPosition.getX();
                    this.y = previousPosition.getY() + movement.getValue();
                }
                this.distance = Math.abs(x) +  Math.abs(y);
                this.step = previousPosition.getStep() + 1;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                return ((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y && ((Position) obj).getDistance() == this.distance;
            } else {
                return super.equals(obj);
            }
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return "position : x=" + x + ", y=" + y + ", distance=" + distance + ", step=" + step;
        }
    }

    public static Map<Integer, List<Position>> generatePositions(Wire wire) {
        AtomicReference<Position> previousPosition = new AtomicReference<>();
        return wire.getMovements().stream()
                .map(movement -> generatePositionsFromMovement(previousPosition, movement))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Position::getDistance));
    }

    public static List<Position> generatePositionsFromMovement(AtomicReference<Position> previousPosition, Movement movement) {
        List<Position> positions = new ArrayList<>();
        int absoluteValue = Math.abs(movement.getValue());
        for (int i = 0; i < absoluteValue; i++) {
            previousPosition.set(new Position(previousPosition.get(), new Movement(movement.getDirection(), movement.getValue() > 0 ? 1 : -1)));
            positions.add(previousPosition.get());
        }
        return positions;
    }

}
