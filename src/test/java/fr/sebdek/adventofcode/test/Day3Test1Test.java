package fr.sebdek.adventofcode.test;

import fr.sebdek.adventofcode.Day3Test1;
import fr.sebdek.adventofcode.Day3Test1.Position;
import fr.sebdek.adventofcode.Day3Test1.Wire;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class Day3Test1Test {

    @Test
    public void simpleDataTest() {
        Wire wire1 = new Wire( "R8,U5,L5,D3");
        Wire wire2 = new Wire("U7,R6,D4,L4");
        Position closestIntersection = getPosition(wire1, wire2);
        assert closestIntersection.getDistance() == 6;
    }

    @Test
    public void dataTest1() {
        Wire wire1 = new Wire( "R75,D30,R83,U83,L12,D49,R71,U7,L72");
        Wire wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");
        Position closestIntersection = getPosition(wire1, wire2);
        assert closestIntersection.getDistance() == 159;
    }

    @Test
    public void dataTest2() {
        Wire wire1 = new Wire( "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        Wire wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Position closestIntersection = getPosition(wire1, wire2);
        assert closestIntersection.getDistance() == 135;
    }

    private Position getPosition(Wire wire1, Wire wire2) {
        Map<Integer, List<Position>> positions1 = Day3Test1.generatePositions(wire1);
        Map<Integer, List<Position>> positions2 = Day3Test1.generatePositions(wire2);
        return Day3Test1.findClosestPosition(positions1, positions2);
    }

}