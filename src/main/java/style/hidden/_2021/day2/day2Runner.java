package style.hidden._2021.day2;

import style.hidden.utils.FileReaderUtil;

import java.util.List;

public class day2Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day2input.txt");
        long horPos = 0;
        long depth = 0;
        long aim = 0;
        for (String line : lines) {
            String direction = line.split(" ")[0];
            long distance = Long.valueOf(line.split(" ")[1]);
            if(direction.contains("forward")) {
                horPos += distance;
                depth += (aim * distance);
            } else if(direction.contains("down")) {
//                depth += distance;
                aim += distance;
            } else if(direction.contains("up")) {
//                depth -= distance;
                aim -= distance;
            }
        }
        System.out.println("horPos = " + horPos);
        System.out.println("depth = " + depth);
        System.out.println("aim = " + aim);
        System.out.println("Part 2 Answer = " + (horPos * depth));
    }
}
