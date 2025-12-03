package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.*;
import java.util.stream.Collectors;

public class day15 extends Runner {

    public day15(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day15("_2022/day15input.txt");
//        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        Set<Coordinate> sensors = new HashSet<>();
        Set<Coordinate> beacons = new HashSet<>();
        Set<Coordinate> cannotBe = new HashSet<>();
        int y = 2000000;

        for(String line : getLines()) {
//            System.out.println("line = " + line);
            int sensorX = Integer.parseInt(line.split("x=")[1].split(",")[0]);
            int sensorY = Integer.parseInt(line.split("y=")[1].split(":")[0]);
            Coordinate sensor = new Coordinate(sensorX, sensorY);
            int beaconX = Integer.parseInt(line.split("x=")[2].split(",")[0]);
            int beaconY = Integer.parseInt(line.split("y=")[2]);
            Coordinate beacon = new Coordinate(beaconX, beaconY);
//            System.out.format("Sensor Coordinate = %s, Beacon Coordinate = %s%n", sensor, beacon);
            sensors.add(sensor);
            beacons.add(beacon);
            cannotBe.addAll(findCoordinatesCannotBe(sensor, beacon, y));
//            cannotBe.stream().sorted(new Comparator<Coordinate>() {
//                @Override
//                public int compare(Coordinate o1, Coordinate o2) {
//                    return Integer.compare(o1.x, o2.x);
//                }
//            });
        }
        cannotBe = cannotBe.stream()
                .filter(c -> !sensors.contains(c) && !beacons.contains(c))
                .collect(Collectors.toSet());
//        System.out.println("Places where Beacon cannot be = " + cannotBe);
        System.out.println("cannotBe.size() = " + cannotBe.size());
/*        System.out.println(cannotBe.stream().sorted(new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return Integer.compare(o1.x, o2.x);
            }
        }).collect(Collectors.toList())); */
    }

    Set<Coordinate> findCoordinatesCannotBe(Coordinate sensor, Coordinate beacon, int y) {
        int diffX = Math.abs(sensor.x - beacon.x);
        int diffY = Math.abs(sensor.y - beacon.y);
        int manhattanDistance = diffX + diffY;
        Set<Coordinate> cannotBe = new HashSet<>();
        int maxX = 0;
        boolean foundMaxX = false;
        while(!foundMaxX) {
            maxX++;
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, sensor.add(new Coordinate(maxX, 0)))) {
                cannotBe.add(sensor.add(new Coordinate(maxX, 0)));
            } else {
                foundMaxX = true;
            }
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, sensor.add(new Coordinate(maxX * -1, 0)))) {
                cannotBe.add(sensor.add(new Coordinate(maxX * -1, 0)));
            } else {
                foundMaxX = true;
            }
        }
        int maxY = 0;
        boolean foundMaxY = false;
        while(!foundMaxY) {
            maxY++;
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, sensor.add(new Coordinate(0, maxY)))) {
                cannotBe.add(sensor.add(new Coordinate(0, maxY)));
            } else {
                foundMaxY = true;
            }
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, sensor.add(new Coordinate(0, maxY * -1)))) {
                cannotBe.add(sensor.add(new Coordinate(0, maxY * -1)));
            } else {
                foundMaxY = true;
            }
        }
//        System.out.println("maxX = " + maxX);
//        System.out.println("maxY = " + maxY);
        cannotBe = new HashSet<>();
        Coordinate yCoord = new Coordinate(sensor.x, y);
        if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, yCoord)) {
            cannotBe.add(yCoord);
        }
        for(int i = 0; i < maxX ; i++) {
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, yCoord.add(new Coordinate(i, 0)))) {
                cannotBe.add(yCoord.add(new Coordinate(i, 0)));
            }
            if(manhattanDistance >= manhattanDistanceBetween2Points(sensor, yCoord.add(new Coordinate(i * -1, 0)))) {
                cannotBe.add(yCoord.add(new Coordinate(i * -1, 0)));
            }
        }
        return cannotBe;
    }

    int manhattanDistanceBetween2Points(Coordinate c1, Coordinate c2) {
        return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
    }

    private long scan(Coordinate c, Map<Coordinate, Coordinate> sensorBeaconMap) {
        boolean inRange = false;
        for(final Map.Entry<Coordinate, Coordinate> entry : sensorBeaconMap.entrySet()) {
            Coordinate sensor = entry.getKey();
            Coordinate beacon = entry.getValue();
            int manhattanDistance = manhattanDistanceBetween2Points(sensor, beacon);
            if(manhattanDistanceBetween2Points(sensor, c) <= manhattanDistance) {
                inRange = true;
                break;
            }
        }
        if(!inRange) {
            return (long) c.x * 4000000 + c.y;
        }
        return 0;
    }

    @Override
    public void part2Code() {
        Map<Coordinate, Coordinate> sensorBeaconMap = new HashMap<>();
        for(String line : getLines()) {
            int sensorX = Integer.parseInt(line.split("x=")[1].split(",")[0]);
            int sensorY = Integer.parseInt(line.split("y=")[1].split(":")[0]);
            Coordinate sensor = new Coordinate(sensorX, sensorY);
            int beaconX = Integer.parseInt(line.split("x=")[2].split(",")[0]);
            int beaconY = Integer.parseInt(line.split("y=")[2]);
            Coordinate beacon = new Coordinate(beaconX, beaconY);
            sensorBeaconMap.put(sensor, beacon);
        }

        for(Map.Entry<Coordinate, Coordinate> entry : sensorBeaconMap.entrySet()) {
            Coordinate sensor = entry.getKey();
            Coordinate beacon = entry.getValue();
            int manhattanDistance = manhattanDistanceBetween2Points(sensor, beacon) + 1;
            long val = findDistressBeacon(sensor, sensorBeaconMap, manhattanDistance, 4000000);
            if(val > 0) {
                System.out.println("Answer Part 2 = " + val);
                return;
            }
        }

    }

    boolean inRange(int x, int y, int max) {
        return x >= 0 && y>= 0 && x <= max && y <= max;
    }

    long scan1(Coordinate sensor, Map<Coordinate, Coordinate> map, int manhattanDistance, int max) {
        for(int y = manhattanDistance; y>= 0; y--) {
            if(inRange(sensor.x + (manhattanDistance - y), sensor.y + y, max)) {
                Coordinate c = sensor.add(manhattanDistance - y, y);
                long val = scan(c, map);
                if(val > 0)
                    return val;
            }
        }
        return 0;
    }

    long scan2(Coordinate sensor, Map<Coordinate, Coordinate> map, int manhattanDistance, int max) {
        for(int y = -manhattanDistance; y<= 0; y++) {
            if(inRange(sensor.x + (manhattanDistance + y), sensor.y + y, max)) {
                Coordinate c = sensor.add(manhattanDistance + y, y);
                long val = scan(c, map);
                if(val > 0)
                    return val;
            }
        }
        return 0;
    }

    long scan3(Coordinate sensor, Map<Coordinate, Coordinate> map, int manhattanDistance, int max) {
        for(int x = manhattanDistance; x>= 0; x--) {
            if(inRange(sensor.x + x, sensor.y + (manhattanDistance - x), max)) {
                Coordinate c = sensor.add(x, manhattanDistance - x);
                long val = scan(c, map);
                if(val > 0)
                    return val;
            }
        }
        return 0;
    }

    long scan4(Coordinate sensor, Map<Coordinate, Coordinate> map, int manhattanDistance, int max) {
        for(int x = -manhattanDistance; x<= 0; x++) {
            if(inRange(sensor.x + x, sensor.y + manhattanDistance + x, max)) {
                Coordinate c = sensor.add(x, manhattanDistance + x);
                long val = scan(c, map);
                if(val > 0)
                    return val;
            }
        }
        return 0;
    }

    long findDistressBeacon(Coordinate sensor, Map<Coordinate, Coordinate> map, int manhattanDistance, int max) {
        long val = scan1(sensor, map, manhattanDistance, max);
        if(val > 0)
            return val;
        val = scan2(sensor, map, manhattanDistance, max);
        if(val > 0)
            return val;
        val = scan3(sensor, map, manhattanDistance, max);
        if(val > 0)
            return val;
        val = scan3(sensor, map, manhattanDistance, max);
        if(val > 0)
            return val;
        return scan4(sensor, map, manhattanDistance, max);
    }

    class Coordinate {
        private int x;
        private int y;
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

        Coordinate add(Coordinate c) {
            return new Coordinate(x + c.x, y + c.y);
        }

        Coordinate add(int x, int y) {
            return new Coordinate(this.x + x, this.y + y);
        }

    }

}
