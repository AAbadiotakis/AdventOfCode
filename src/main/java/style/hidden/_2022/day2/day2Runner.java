package style.hidden._2022.day2;

import style.hidden.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class day2Runner {

    private static String rock = "A";
    private static String paper = "B";
    private static String scissor = "C";

    private static String responseRock = "X";
    private static String responsePaper = "Y";
    private static String responseScissor = "Z";

    private static int rockPoints = 1;
    private static int paperPoints = 2;
    private static int scissorPoints = 3;

    private static int winPoints = 6;
    private static int drawPoints = 3;

    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2022/day2input.txt");
        part1Code(lines);
        part2Code(lines);
    }

    private static void part1Code(List<String> lines) {
        int totalScore = 0;
        for(String line : lines) {
            String oppChoice = line.split(" ")[0];
            String myChoice = line.split(" ")[1];
            if(myChoice.equals(responseRock)) {
                totalScore += rockPoints;
            } else if(myChoice.equals(responsePaper)) {
                totalScore += paperPoints;
            } else if(myChoice.equals(responseScissor)) {
                totalScore += scissorPoints;
            }

            if(oppChoice.equals(rock) && myChoice.equals(responsePaper)) {
                totalScore += winPoints;
            } else if(oppChoice.equals(paper) && myChoice.equals(responseScissor)) {
                totalScore += winPoints;
            } else if(oppChoice.equals(scissor) && myChoice.equals(responseRock)) {
                totalScore += winPoints;
            }

            if(oppChoice.equals(rock) && myChoice.equals(responseRock)) {
                totalScore += drawPoints;
            } else if(oppChoice.equals(paper) && myChoice.equals(responsePaper)) {
                totalScore += drawPoints;
            } else if(oppChoice.equals(scissor) && myChoice.equals(responseScissor)) {
                totalScore += drawPoints;
            }
        }
        System.out.println("Day 2 Part 1 = " + totalScore);
    }

    private static String needToLose = "X";
    private static String needToDraw = "Y";
    private static String needToWin = "Z";

    private static void part2Code(List<String> lines) {
        int totalScore = 0;
        for(String line : lines) {
            String oppChoice = line.split(" ")[0];
            String myChoice = line.split(" ")[1];

            if(myChoice.equals(needToDraw)) {
                totalScore += drawPoints;
            } else if(myChoice.equals(needToWin)) {
                totalScore += winPoints;
            }

            if(oppChoice.equals(rock)) {
                if(myChoice.equals(needToDraw)) {
                    totalScore += rockPoints;
                } else if(myChoice.equals(needToWin)) {
                    totalScore += paperPoints;
                } else if(myChoice.equals(needToLose)) {
                    totalScore += scissorPoints;
                }
            } else if(oppChoice.equals(paper)) {
                if(myChoice.equals(needToDraw)) {
                    totalScore += paperPoints;
                } else if(myChoice.equals(needToWin)) {
                    totalScore += scissorPoints;
                } else if(myChoice.equals(needToLose)) {
                    totalScore += rockPoints;
                }
            } else if(oppChoice.equals(scissor)) {
                if(myChoice.equals(needToDraw)) {
                    totalScore += scissorPoints;
                } else if(myChoice.equals(needToWin)) {
                    totalScore += rockPoints;
                } else if(myChoice.equals(needToLose)) {
                    totalScore += paperPoints;
                }
            }
        }
        System.out.println("Day 2 Part 2 = " + totalScore);

    }
}
