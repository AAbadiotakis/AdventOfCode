package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.HashSet;
import java.util.Set;

public class day9 extends Runner {

    public day9(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day9("_2022/day9input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        Set<Coordinate> coords = new HashSet<>();
        coords.add(new Coordinate(0,0));
        Set<Coordinate> lastTailPositions = new HashSet<>();
        lastTailPositions.add(new Coordinate(0,0));
        Coordinate[] tails = new Coordinate[9];
        for(int i = 0; i < tails.length; i++) {
            tails[i] = new Coordinate(0,0);
        }
        int currentHeadX = 0;
        int currentHeadY = 0;
        for(String line : getLines()) {
            String direction = line.split(" ")[0];
            int steps = Integer.parseInt(line.split(" ")[1]);
            for(int i = 1; i <= steps; i++) {
                switch(direction) {
                    case "R": currentHeadX++; break;
                    case "L": currentHeadX--; break;
                    case "U": currentHeadY++; break;
                    case "D": currentHeadY--; break;
                }
                Coordinate previousTail = null;
                for(int j = 0; j < tails.length ; j++) {
                    Coordinate t = tails[j];
                    if(previousTail == null) {
                        tails[j] = findTail(currentHeadX, currentHeadY, t.x, t.y);
                    } else {
                        tails[j] = findTail(previousTail.x, previousTail.y, t.x, t.y);
                    }
                    if(j == 0) {
                        coords.add(tails[j]);
                    } else if(j == tails.length - 1) {
                        lastTailPositions.add(tails[j]);

                    }
                    previousTail = tails[j];
                }
            }

        }
        System.out.println("Coords size = " + coords.size());
        System.out.println("lastTailPositionSize = " + lastTailPositions.size());
    }

    boolean isHeadTouchingTail(int headX, int headY, int tailX, int tailY) {
        if (tailX >= headX - 1 && tailX <= headX + 1 && tailY >= headY - 1 && tailY <= headY + 1) {
            return true;
        }
        return false;
    }

    Coordinate findTail(int headX, int headY, int tailX, int tailY) {
        if(!isHeadTouchingTail(headX, headY, tailX, tailY)) {
            if (headY == tailY) {
                if (isHeadTouchingTail(headX, headY, tailX + 1, tailY)) {
                    tailX++;
                } else if (isHeadTouchingTail(headX, headY, tailX - 1, tailY)) {
                    tailX--;
                }
            } else if (headX == tailX) {
                if (isHeadTouchingTail(headX, headY, tailX, tailY + 1)) {
                    tailY++;
                } else if (isHeadTouchingTail(headX, headY, tailX, tailY - 1)) {
                    tailY--;
                }
            } else {
                if (isHeadTouchingTail(headX, headY, tailX + 1, tailY + 1)) {
                    tailX++;
                    tailY++;
                } else if (isHeadTouchingTail(headX, headY, tailX + 1, tailY - 1)) {
                    tailX++;
                    tailY--;
                } else if (isHeadTouchingTail(headX, headY, tailX - 1, tailY + 1)) {
                    tailX--;
                    tailY++;
                } else if (isHeadTouchingTail(headX, headY, tailX - 1, tailY - 1)) {
                    tailX--;
                    tailY--;
                } else {
                    System.out.println("ERROR");
                    System.out.format("currentHeadX=%d,currentHeadY=%d | currentTailX=%d,currentTailY=%d%n", headX, headY, tailX, tailY);
                    throw new RuntimeException();
                }
            }
        }
        return new Coordinate(tailX, tailY);
    }


    @Override
    public void part2Code() {
    }

    class Coordinate {
        private int x;
        private int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d]", x, y);
        }

        @Override
        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Coordinate))
                return false;
            Coordinate obj = (Coordinate) o;
            return this.x == obj.x && this.y == obj.y;
        }

        @Override
        public int hashCode() {
            return x * y;
        }
    }

}
