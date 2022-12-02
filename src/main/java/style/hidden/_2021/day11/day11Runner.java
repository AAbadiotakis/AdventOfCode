package style.hidden._2021.day11;

import style.hidden.utils.FileReaderUtil;

import java.util.Arrays;
import java.util.List;

public class day11Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day11input.txt");

        Integer[][] grid = new Integer[10][10];

        int y = 0;
        for(String line : lines) {
            for(int x = 0; x < line.length(); x++) {
                grid[x][y] = Integer.valueOf(line.charAt(x));
            }
            y++;
        }

        System.out.println(Arrays.deepToString(grid));
    }
}
