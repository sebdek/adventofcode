package fr.sebdek.adventofcode.test;

import fr.sebdek.adventofcode.Day3Test1.Position;
import fr.sebdek.adventofcode.Day3Test1.Wire;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static fr.sebdek.adventofcode.Day3Test1.generatePositions;
import static fr.sebdek.adventofcode.Day3Test2.findLeastStepPosition;

public class Day3Test2Test {

    @Test
    public void simpleDataTest() {
        Wire wire1 = new Wire( "R8,U5,L5,D3");
        Wire wire2 = new Wire("U7,R6,D4,L4");
        Position leastStepIntersection = getPosition(wire1, wire2);
        assert leastStepIntersection.getStep() == 30;
    }

    @Test
    public void dataTest1() {
        Wire wire1 = new Wire( "R75,D30,R83,U83,L12,D49,R71,U7,L72");
        Wire wire2 = new Wire("U62,R66,U55,R34,D71,R55,D58,R83");
        Position leastStepIntersection = getPosition(wire1, wire2);
        assert leastStepIntersection.getStep() == 610;
    }

    @Test
    public void dataTest2() {
        Wire wire1 = new Wire( "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        Wire wire2 = new Wire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Position leastStepIntersection = getPosition(wire1, wire2);
        assert leastStepIntersection.getStep() == 410;
    }

    private Position getPosition(Wire wire1, Wire wire2) {
        Map<Integer, List<Position>> positions1 = generatePositions(wire1);
        Map<Integer, List<Position>> positions2 = generatePositions(wire2);
        return findLeastStepPosition(positions1, positions2);
    }

}