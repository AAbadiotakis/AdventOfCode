package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.*;
import java.util.stream.Collectors;

public class day12 extends Runner {

    public day12(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day12("_2022/day12input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    Coordinate startCoordinate = null;
    Coordinate endCoordinate = null;

    @Override
    public void part1Code() {
        Character[][] characters = new Character[getLines().size()][getLines().get(0).length()];
        List<Coordinate> startingCoordinates = new ArrayList<>();
        for(int y = 0; y < getLines().size(); y++) {
            for(int x = 0; x < getLines().get(y).length(); x++) {
                characters[y][x] = getLines().get(y).charAt(x);
                if(characters[y][x].equals('S')) {
                    startCoordinate = new Coordinate(x,y);
                    startingCoordinates.add(startCoordinate);
                } else if(characters[y][x].equals('E')) {
                    endCoordinate = new Coordinate(x,y);
                } else if(characters[y][x].equals('a')) {
                    startingCoordinates.add(new Coordinate(x,y));
                }
            }
        }

        Set<Coordinate> previouslyVisited = new HashSet<>();
        //Set<Coordinate> currentLevel = new HashSet<>(); currentLevel.add(startCoordinate); // Part 1
        Set<Coordinate> currentLevel = new HashSet<>(startingCoordinates);
        Set<Coordinate> nextLevel = new HashSet<>();

        int count = 0;
        while(true) {
            for(Coordinate c : currentLevel) {
                if(c.equals(endCoordinate)) {
                    System.out.println("FINISHED IN " + count);
                    return;
                }
                nextLevel.addAll(c.validMoves(characters).stream().filter(coord -> !previouslyVisited.contains(coord)).collect(Collectors.toList()));
                previouslyVisited.add(c);
            }
            count++;
            currentLevel = nextLevel;
            System.out.println("new currentLevel = " + currentLevel);
            nextLevel = new HashSet<>();
            if(currentLevel.size() == 0) {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void part2Code() {

    }

    static List<Coordinate> directions;

    {
        directions = new ArrayList<>();
        directions.add(new Coordinate(-1, 0));
        directions.add(new Coordinate(0, -1));
        directions.add(new Coordinate(0, 1));
        directions.add(new Coordinate(1, 0));
    }

    class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        List<Coordinate> validMoves(Character[][] characters) {
            return directions.stream()
                    .map(dir -> dir.add(this))
                    .filter(newCoord -> newCoord.isInBounds(characters[0].length, characters.length))
                    .filter(newCoord -> newCoord.isValidHeight(characters, this))
                    .collect(Collectors.toList());
        }

        boolean isInBounds(int maxX, int maxY) {
//            System.out.format("characters.length=%d, characters[y].length=%d, newCoordinate=%s", characters.length, characters[previousCoordinate.y].length, this);
            return y >= 0 && y < maxY && x >= 0 && x < maxX;
        }

        boolean isValidHeight(Character[][] characters, Coordinate previousCoordinate) {
            return rewriteCharacter(characters[y][x]) - rewriteCharacter(characters[previousCoordinate.y][previousCoordinate.x]) <= 1;
        }

        Character rewriteCharacter(Character c) {
            return switch (c) {
                case 'S' -> 'a';
                case 'E' -> 'z';
                default -> c;
            };
        }

        Coordinate add(Coordinate c) {
            return new Coordinate(x + c.x, y + c.y);
        }

        @Override
        public String toString() {
            return String.format("Coordinate { x=%d, y=%d }%n", x, y);
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Coordinate)) {
                return false;
            }
            Coordinate coordinate = (Coordinate) o;
            return this.x == coordinate.x && this.y == coordinate.y;
        }

        @Override
        public int hashCode() {
            return x * y;
        }
    }

}
