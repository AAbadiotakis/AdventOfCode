package style.hidden._2021.day9;

import style.hidden.utils.FileReaderUtil;

import java.util.List;

public class day9Runner {

    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day9input.txt");
        int width = lines.get(0).length();
        int height = lines.size();
        Integer[][] box = new Integer[height][width];
        int i = 0;
        for (String line : lines) {
            for(int j = 0; j < line.length(); j++) {
                box[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
//            System.out.println(Arrays.deepToString(box));
            i++;
        }
        int counter = 0;
        int riskLevel = 0;
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                // For an x,y coord, check if the left/right/up/down coords are ever smaller than the xy coord
                //Check left coord. If x,y is less than x-1,y, break and check new coord
                if(x != 0 && box[y][x] >= box[y][x-1]) {
                    continue;
                }
                // Check right coord
                if(x != width - 1 && box[y][x] >= box[y][x+1]) {
                        continue;
                }
                //check top coord
                if(y != 0 && box[y][x] >= box[y-1][x]) {
                    continue;
                }

                if(y != height - 1 && box[y][x] >= box[y+1][x]) {
                    continue;
                }
                System.out.println("Found low point at x=" + x + ",y=" + y + " | box value = " + box[y][x]);
                counter++;
                riskLevel += 1 + box[y][x];
            }
        }
        System.out.println("Counter = " + counter);
        System.out.println("riskLevel = " + riskLevel);
    }
}
