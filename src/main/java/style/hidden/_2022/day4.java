package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class day4 extends Runner {

    public day4(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day4("_2022/day4input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        int count = 0;
        for(String line : getLines()) {
            String firstElf = line.split(",")[0];
            int firstElfStart = Integer.parseInt(firstElf.split("-")[0]);
            int firstElfEnd = Integer.parseInt(firstElf.split("-")[1]);

            String secondElf = line.split(",")[1];
            int secondElfStart = Integer.parseInt(secondElf.split("-")[0]);
            int secondElfEnd = Integer.parseInt(secondElf.split("-")[1]);

            if((firstElfStart <= secondElfStart && firstElfEnd >= secondElfEnd) ||
                    (secondElfStart <= firstElfStart && secondElfEnd >= firstElfEnd)) {
                count++;
            }
        }

        System.out.println("Answer Part 1 = " + count);

    }

    @Override
    public void part2Code() {
        int count = 0;
        for (String line : getLines()) {
            String firstElf = line.split(",")[0];
            int firstElfStart = Integer.parseInt(firstElf.split("-")[0]);
            int firstElfEnd = Integer.parseInt(firstElf.split("-")[1]);
            Set<Integer> elfSet = new HashSet<>();
            for(int i = firstElfStart; i <= firstElfEnd; i++) {
                elfSet.add(i);
            }

            String secondElf = line.split(",")[1];
            int secondElfStart = Integer.parseInt(secondElf.split("-")[0]);
            int secondElfEnd = Integer.parseInt(secondElf.split("-")[1]);

            boolean hasOverlap = false;
            for(int i = secondElfStart; i<= secondElfEnd; i++) {
                if(!elfSet.add(i)) {
                    hasOverlap = true;
                    break;
                }
            }
            if(hasOverlap) {
                count++;
            }
        }
        System.out.println("Answer Part 2 = " + count);
    }
}
