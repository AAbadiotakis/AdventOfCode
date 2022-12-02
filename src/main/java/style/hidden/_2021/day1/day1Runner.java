package style.hidden._2021.day1;

import style.hidden.utils.FileReaderUtil;

import java.util.List;

public class day1Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day1input.txt");
        Long previousDepth = null;
        Long secondPreviousDepth = null;
        Long thirdPreviousDepth = null;
        long firstAnswerCounter = 0;
        long secondAnswerCounter = 0;
        for (String line : lines) {
            if(previousDepth != null) {
                if(Long.valueOf(line) > previousDepth) {
                    firstAnswerCounter++;
                }
                if(secondPreviousDepth != null) {
                    if(thirdPreviousDepth != null) {
                        if((thirdPreviousDepth + secondPreviousDepth + previousDepth) < (secondPreviousDepth + previousDepth + Long.valueOf(line))) {
                            secondAnswerCounter++;
                        }
                    }
                }
            }
            thirdPreviousDepth = secondPreviousDepth;
            secondPreviousDepth = previousDepth;
            previousDepth = Long.valueOf(line);
        }
        // Answer Part 1
        System.out.println("First Part Answer: " + firstAnswerCounter);
        System.out.println("Second Part Answer: " + secondAnswerCounter);

    }
}
