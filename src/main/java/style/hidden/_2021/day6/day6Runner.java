package style.hidden._2021.day6;

import style.hidden.utils.FileReaderUtil;

import java.util.*;
import java.util.stream.Collectors;

public class day6Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day6input.txt");
        List<Integer> ages = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println("Initial State: " + ages.toString());
        Map<Integer, Long> fishMap = new HashMap<>();
        for(int age : ages) {
            if(fishMap.containsKey(age)) {
                fishMap.put(age, fishMap.get(age)+1);
            } else {
                fishMap.put(age, 1L);
            }
        }
        System.out.println("Initial FishMap = " + fishMap);

        for(int day = 1; day <= 256; day++) {
            Map<Integer, Long> tempFishMap = new HashMap<>();
            for(int key : fishMap.keySet()) {
                if(key != 0) {
                    tempFishMap.put(key-1, fishMap.get(key));
                }
            }
            if(fishMap.containsKey(0)) {
                if(tempFishMap.containsKey(6)) {
                    tempFishMap.put(6, fishMap.get(0) + tempFishMap.get(6));
                } else {
                    tempFishMap.put(6, fishMap.get(0));
                }
                if(tempFishMap.containsKey(8)) {
                    tempFishMap.put(8, fishMap.get(0) + tempFishMap.get(8));
                } else {
                    tempFishMap.put(8, fishMap.get(0));
                }
            }



            fishMap = tempFishMap;
            System.out.println("Day " + day + " : FishMap = " + fishMap);
//            System.out.println("Day " + i + ": " + ages);
            System.out.println("Day " + day + " : Total Fish = " + fishMap.values().stream().reduce(0L, Long::sum));
        }
    }
}
