package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class day14 extends Runner {

    public day14(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day14("_2022/day14input.txt");
        runner.part1Code();
//        runner.part2Code();
    }

    static int maxY = 0;
    @Override
    public void part1Code() {
        Set<Coordinate> rockCoordinates = new HashSet<>();
        Set<Coordinate> sandCoordinates = new HashSet<>();
        for(String line : getLines()) {
            for(int i = 1; i < line.split("->").length; i++) {
                rockCoordinates.addAll(coordinatesBetweenTwoPoints(findCoordinate(line.split("->")[i-1]), findCoordinate(line.split("->")[i])));
            }
        }
        maxY = rockCoordinates.stream()
                .map(c -> c.y)
                .mapToInt(i -> i)
                .max()
                .getAsInt();

        int count = 0;
        while(fallenSandCoordinate(rockCoordinates, sandCoordinates) != null) {
            count++;
        }

        System.out.println("Answer Part 1 = " + sandCoordinates.size());

        Set<Coordinate> sandCoordinatesPart2 = new HashSet<>();
        int part2Count = 0;
        while(fallenSandCoordinatePart2(rockCoordinates, sandCoordinatesPart2, maxY + 2) != null) {
            part2Count++;
        }

        System.out.println("Answer Part 2 = " + part2Count);
    }

    Coordinate findCoordinate(String str) {
        return new Coordinate(Integer.parseInt(str.split(",")[0].trim()), Integer.parseInt(str.split(",")[1].trim()));
    }

    Set<Coordinate> coordinatesBetweenTwoPoints(Coordinate first, Coordinate second) {
        Set<Coordinate> coordinates = new HashSet<>();
        if(first.x == second.x) {
            if(first.y > second.y) {
                for(int y = second.y; y <= first.y; y++) {
                    coordinates.add(new Coordinate(first.x, y));
                }
            } else if(first.y < second.y) {
                for(int y = first.y; y <= second.y; y++) {
                    coordinates.add(new Coordinate(first.x, y));
                }
            }
        } else if(first.y == second.y) {
            if(first.x > second.x) {
                for(int x = second.x; x <= first.x; x++) {
                    coordinates.add(new Coordinate(x, first.y));
                }
            } else if(first.x < second.x) {
                for(int x = first.x; x <= second.x; x++) {
                    coordinates.add(new Coordinate(x, first.y));
                }
            }
        }
        return coordinates;
    }

    Coordinate fallenSandCoordinate(Set<Coordinate> rockCoordinates, Set<Coordinate> sandCoordinates) {
        Coordinate sandFallingPath = new Coordinate(500, 0);
        while(true) {
//            Boolean blockedDirectlyDown;
//            Boolean blockedDownLeft;
//            Boolean blockedDownRight;
            System.out.println("Checking Sand Coordinate " + sandFallingPath);
            // check direct down path
            if(rockCoordinates.contains(new Coordinate(sandFallingPath.x, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x, sandFallingPath.y + 1))) {
                //blockedDirectlyDown = true;

                //check down-left path
                if(rockCoordinates.contains(new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1))) {
                    //blockedDownLeft = true;

                    // check down-right path
                    if(rockCoordinates.contains(new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1))) {
                        //blockedDownRight = true;
                        System.out.println("Fallen Sand found its home at " + sandFallingPath);
                        sandCoordinates.add(sandFallingPath);
                        return sandFallingPath;
                    } else {
                        sandFallingPath = new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1);
                    }
                } else {
                    // blockedDownLeft = false;
                    sandFallingPath = new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1);
                }
            } else {
                // blockedDirectlyDown = false;
                if(sandFallingPath.y + 1 > maxY) {
                    return null;
                } else {
                    sandFallingPath = new Coordinate(sandFallingPath.x, sandFallingPath.y + 1);
                }
            }
        }
    }

    Coordinate fallenSandCoordinatePart2(Set<Coordinate> rockCoordinates, Set<Coordinate> sandCoordinates, int lineY) {
        Coordinate sandFallingPath = new Coordinate(500, 0);
        while(true) {
            if(sandCoordinates.contains(new Coordinate(500, 0))) {
                return null;
            }
            // check direct down path
            if(rockCoordinates.contains(new Coordinate(sandFallingPath.x, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x, sandFallingPath.y + 1)) || sandFallingPath.y + 1 == lineY) {
                //blockedDirectlyDown = true;

                //check down-left path
                if(rockCoordinates.contains(new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1)) || sandFallingPath.y + 1 == lineY) {
                    //blockedDownLeft = true;

                    // check down-right path
                    if(rockCoordinates.contains(new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1)) || sandCoordinates.contains(new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1)) || sandFallingPath.y + 1 == lineY) {
                        //blockedDownRight = true;
                        sandCoordinates.add(sandFallingPath);
                        return sandFallingPath;
                    } else {
                        sandFallingPath = new Coordinate(sandFallingPath.x + 1, sandFallingPath.y + 1);
                    }
                } else {
                    // blockedDownLeft = false;
                    sandFallingPath = new Coordinate(sandFallingPath.x - 1, sandFallingPath.y + 1);
                }
            } else {
                // blockedDirectlyDown = false;
                if(sandFallingPath.y + 1 > lineY) {
                    throw new RuntimeException("Should never get here");
                } else {
                    sandFallingPath = new Coordinate(sandFallingPath.x, sandFallingPath.y + 1);
                }
            }
        }
    }


    @Override
    public void part2Code() {

    }


    class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if(o == null)
                return false;
            if(!(o instanceof Coordinate))
                return false;
            return (x == ((Coordinate) o).x && y == ((Coordinate) o).y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }

        @Override
        public String toString() {
            return String.format("{ x=%d, y=%d }", x, y);
        }
    }

}
