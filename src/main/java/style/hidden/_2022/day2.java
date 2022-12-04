package style.hidden._2022;

import style.hidden.utils.Runner;

public class day2 extends Runner {

    private static int rockPoints = 1;
    private static int paperPoints = 2;
    private static int scissorPoints = 3;

    private static int winPoints = 6;
    private static int drawPoints = 3;

    private static String ROCK = "A";
    private static String PAPER = "B";
    private static String SCISSOR = "C";

    public day2(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
            Runner runner = new day2("_2022/day2input.txt");
            runner.part1Code();
            runner.part2Code();
    }

    @Override
    public void part1Code() {
        long points = 0;
        final String responseRock = "X";
        final String responsePaper = "Y";
        final String responseScissor = "Z";
        for(String line : getLines()) {
            String oppChoice = line.split(" ")[0];
            String myChoice = line.split(" ")[1];
            System.out.printf("oppChoice = %s , myChoice = %s%n", oppChoice, myChoice);
            switch(myChoice) {
                case responseRock: points += rockPoints; break;
                case responsePaper: points += paperPoints; break;
                case responseScissor: points += scissorPoints; break;
            }
            System.out.println("points = " + points);
            if(ROCK.equals(oppChoice)) {
                if(responsePaper.equals(myChoice)) {
                    points += winPoints;
                } else if(responseRock.equals(myChoice)) {
                    points += drawPoints;
                }
            } else if(PAPER.equals(oppChoice)) {
                if(responseScissor.equals(myChoice)) {
                    points += winPoints;
                } else if(responsePaper.equals(myChoice)) {
                    points += drawPoints;
                }
            } else if(SCISSOR.equals(oppChoice)) {
                if(responseRock.equals(myChoice)) {
                    points += winPoints;
                } else if(responseScissor.equals(myChoice)) {
                    points += drawPoints;
                }
            }
            System.out.println("points = " + points);
        }
        System.out.println("Part 1 Answer = " + points);
    }

    @Override
    public void part2Code() {

    }

    enum RockPaperScissor {
        ROCK,
        PAPER,
        SCISSOR;
    }
}
