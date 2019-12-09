package fr.sebdek.adventofcode.test;

import fr.sebdek.adventofcode.Day3Test1;
import fr.sebdek.adventofcode.Day3Test1.Position;
import fr.sebdek.adventofcode.Day3Test1.Wire;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day3Test1Test {

    private Day3Test1 testInstance = new Day3Test1();

    @Before
    public void init() {

    }

    @Test
    public void simpleDataTest() {
        Wire wire1 = new Wire( "R8,U5,L5,D3");
        Wire wire2 = new Wire("U7,R6,D4,L4");
        Map<Integer, List<Position>> positions1 = testInstance.generatePositions(wire1);
        Map<Integer, List<Position>> positions2 = testInstance.generatePositions(wire2);
        Position closestIntersection = testInstance.findClosestPosition(positions1, positions2);
    }

}