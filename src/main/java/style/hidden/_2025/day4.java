package style.hidden._2025;

import style.hidden.utils.Runner;

import java.util.List;
import java.util.Objects;

public class day4 extends Runner {
    public day4(String path) throws Exception {
        super(path);
    }

    static void main() throws Exception {
        Runner runner = new day4("_2025/day4input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        int answer = 0;
        int maxX = getLines().getFirst().length();
        int maxY = getLines().size();
        String[][] grid = new String[maxX][maxY];
        for(int x = 0; x < maxX; x++) {
            for(int y = 0; y < maxY; y++) {
                grid[y][x] = String.valueOf(getLines().get(y).charAt(x));
            }
        }
        for(int x = 0; x < maxX; x++) {
            for(int y = 0; y < maxY; y++) {
                if(Objects.equals(grid[y][x], "@")) {
                    Integer value = rollsAroundPosition(x,y,grid,maxX, maxY);
                    if(value < 4) {
                        answer++;
                    }
                }
            }
        }
        System.out.println("Answer Part1 = " + answer);
    }

    static List<Integer> positions = List.of(-1,0,1);

    public Integer rollsAroundPosition(int x, int y, String[][] grid, int maxX, int maxY) {
        int value = 0;
        for(Integer xPositionOffset : positions) {
            for(Integer yPositionOffset : positions) {
                if(!(xPositionOffset == 0 && yPositionOffset == 0)) {
                    if(!(x + xPositionOffset < 0 || x + xPositionOffset >= maxX)) {
                        if(!(y + yPositionOffset < 0 || y + yPositionOffset >= maxY)) {
                            if(Objects.equals(grid[y + yPositionOffset][x + xPositionOffset], "@")) {
                                value++;
                            }
                        }
                    }
                }
            }
        }
        return value;
    }

    @Override
    public void part2Code() {
        boolean canRemoveMoreRolls = true;
        int answer = 0;
        int maxX = getLines().getFirst().length();
        int maxY = getLines().size();
        String[][] grid = new String[maxX][maxY];
        for(int x = 0; x < maxX; x++) {
            for(int y = 0; y < maxY; y++) {
                grid[y][x] = String.valueOf(getLines().get(y).charAt(x));
            }
        }
        boolean hasRemovedRoll;
        do {
            hasRemovedRoll = false;
            for(int x = 0; x < maxX; x++) {
                for(int y = 0; y < maxY; y++) {
                    if(Objects.equals(grid[y][x], "@")) {
                        Integer value = rollsAroundPosition(x,y,grid,maxX, maxY);
                        if(value < 4) {
                            grid[y][x] = "X";
                            answer++;
                            hasRemovedRoll = true;
                        }
                    }
                }
            }
        } while(hasRemovedRoll);

        System.out.println("Answer Part2 = " + answer);

    }

}
