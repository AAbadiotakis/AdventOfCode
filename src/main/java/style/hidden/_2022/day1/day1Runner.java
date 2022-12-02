package style.hidden._2022.day1;

import style.hidden.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class day1Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2022/day1input.txt");
        int i = 0;
        List<Long> calories = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty() || line.isBlank()) {
                i++;
            } else {
                if(calories.size() == i) {
                    calories.add(i, Long.valueOf(line));
                } else {
                    calories.set(i, calories.get(i) + Long.parseLong(line));
                }
            }
        }
        calories = calories.stream()
                        .sorted((l1, l2) -> Long.compare(l2, l1))
                                .collect(Collectors.toList());
        System.out.println(calories);
        System.out.println("Day 1 Part 1 = " + calories.get(0));
        System.out.println("Day 1 Part 2 = " + (calories.get(0) + calories.get(1) + calories.get(2)));





    }
}
