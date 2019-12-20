package fr.sebdek.adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6Test1 {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("day6_test1.txt"))) {
            reader.lines()
                    .map(s -> s.split("\\)"))
                    .collect(Collectors.groupingBy(strings -> strings[0]));
        }
    }

    class GravitationnalObject {

        private String name;
        private Set<GravitationnalObject> satellites;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<GravitationnalObject> getSatellites() {
            return satellites;
        }

        public void setSatellites(Set<GravitationnalObject> satellites) {
            this.satellites = satellites;
        }

        public GravitationnalObject(String name, Set<GravitationnalObject> satellites) {
            this.name = name;
            this.satellites = satellites;
        }
    }

}
