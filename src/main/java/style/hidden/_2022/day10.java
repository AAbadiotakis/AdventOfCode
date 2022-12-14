package style.hidden._2022;

import style.hidden.utils.Runner;

public class day10 extends Runner {

    public day10(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day10("_2022/day10input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        int x = 1;
        int lineNum = 0;
        int sumSignalStrength = 0;
        boolean isWait = false;
        Character[] crtScreen = new Character[40];
        for(int cycleCount = 1; cycleCount <= 240; cycleCount++) {
            int crtScreenVal = (cycleCount - 1) % 40;
            if((x - 1) <= crtScreenVal && (x + 1) >= crtScreenVal) {
                crtScreen[(cycleCount - 1) % 40] = '#';
            } else {
                crtScreen[(cycleCount - 1) % 40] = '.';
            }

            if(cycleCount == 20) {
                sumSignalStrength += (cycleCount * x);
            } else if((cycleCount - 20) % 40 == 0) {
                sumSignalStrength += (cycleCount * x);
            }

            if(cycleCount % 40 == 0) {
                printCrtScreen(crtScreen);
            }

            if(getLines().get(lineNum).equals("noop")) {
                lineNum++;
            } else {
                if(isWait) {
                    isWait = false;
                    x += Integer.parseInt(getLines().get(lineNum).split(" ")[1]);
                    lineNum++;
                } else {
                    isWait = true;
                }
            }
        }
        System.out.println("SumSignalStrength = " + sumSignalStrength);
    }

    void printCrtScreen(Character[] chars) {
        for(Character c : chars) {
            if(c != null) {
                System.out.print(c);
            }
        }
        System.out.println();
    }



    @Override
    public void part2Code() {
    }

}
