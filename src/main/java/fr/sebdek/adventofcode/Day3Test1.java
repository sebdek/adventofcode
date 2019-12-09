package fr.sebdek.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    //TODO
    public Position findClosestPosition(Map<Integer, List<Position>> positions1, Map<Integer, List<Position>> positions2) {
        positions1.keySet().stream();
        return null;
    }

    public static class Wire {

        Set<Movement> movements;

        public Wire(String path) {
            this.movements = Arrays.stream(path.split(","))
                    .map(s -> {
                        String[] movementDesc = s.split("");
                        return new Movement(movementDesc[0], Integer.parseInt(movementDesc[1]));
                    })
                    .collect(Collectors.toSet());
        }

        public Set<Movement> getMovements() {
            return movements;
        }

        public void setMovements(Set<Movement> movements) {
            this.movements = movements;
        }
    }

    public static class Movement {

        String direction;
        int value;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
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
                    this.value = - value;
                    break;
                case "D":
                    this.direction = "U";
                    this.value = - value;
                    break;
                default:
                    break;
            }

        }

    }

    public class Position {

        int x;
        int y;
        int distance;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
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
                this.distance = x + y;
            } else {
                if ("R".equals(movement.getDirection())) {
                    this.x = previousPosition.getX() + movement.getValue();
                    this.y = previousPosition.getY();
                } else {
                    this.x = previousPosition.getX();
                    this.y = previousPosition.getY() + movement.getValue();
                }
                this.distance = previousPosition.getDistance() + x + y;
            }
        }
    }

    public Map<Integer, List<Position>> generatePositions(Wire wire) {
        AtomicReference<Position> previousPosition = new AtomicReference<>();
        return wire.getMovements().stream()
                .map(movement -> {
                    Position newPosition = new Position(previousPosition.get(), movement);
                    previousPosition.set(newPosition);
                    return newPosition;
                })
                .collect(Collectors.groupingBy(Position::getDistance));
    }

}
