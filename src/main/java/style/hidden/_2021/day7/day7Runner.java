package style.hidden._2021.day7;

import style.hidden.utils.FileReaderUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class day7Runner {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        List<String> lines = FileReaderUtil.parseFile("day7input.txt");
        List<Integer> horPos = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int maxInt = horPos.stream().max(Integer::compare).get();
        int minInt = horPos.stream().min(Integer::compare).get();
        int leastFuelUsed = Integer.MAX_VALUE;
        int leastPosition = 0;
        for(int i = minInt; i < maxInt; i++) {
            int fuelUsed = 0;
            for(int crab : horPos) {
                int distance = Math.abs(crab - i);
                for(int j = 1; j <= distance; j ++) {
                    fuelUsed += j;
                }
//                System.out.println("crab = " + crab + " | i = " + i);
            }
            System.out.println("i = " + i + " | fuelUsed = " + fuelUsed);
            if(fuelUsed < leastFuelUsed) {
                leastPosition = i;
                leastFuelUsed= fuelUsed;
            }
        }
        System.out.println("Least Fuel Consumed Possible = " + leastFuelUsed);
        System.out.println("Position = " + leastPosition);
        System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime));
        System.out.println("Mean = " + horPos.stream().mapToDouble(a -> a).average());
    }
}
