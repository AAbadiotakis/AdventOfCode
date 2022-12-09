package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.Arrays;

public class day8 extends Runner {

    public day8(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day8("_2022/day8input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        Integer[][] integers = new Integer[getLines().size()][getLines().get(0).length()];
        for(int y = 0; y < getLines().size(); y++) {
            for(int x = 0; x < getLines().get(0).length(); x++) {
                integers[y][x] = Integer.parseInt(String.valueOf(getLines().get(y).charAt(x)));
            }
         }

        int count = 0;
        int bestTreeScore = 0;
        for(int y = 0; y < getLines().size(); y++) {
            for(int x = 0; x < getLines().get(0).length(); x++) {
                if(isVisible(x, y, integers)) {
                    count++;
                }
                int treeScore = treesVisible(x, y, integers);
                if(bestTreeScore < treeScore) {
                    bestTreeScore = treeScore;
                }
                System.out.format("Tree Score for [%d][%d] = %d%n", y, x, treeScore);

            }
        }

        System.out.println("Count = " + count);
        System.out.println("Best Tree Score = " + bestTreeScore);
        System.out.println("integers = " + Arrays.deepToString(integers));
    }



    @Override
    public void part2Code() {
    }

    int treesVisible(int x, int y, Integer[][] integers) {
        int value = integers[y][x];

        //leftX
        int leftX = 0;
        for(int i = x - 1; i >= 0; i--) {
            leftX++;
            if(value <= integers[y][i]) {
                break;
            }
        }
        //rightX
        int rightX = 0;
        for(int i = x + 1; i < integers.length; i++) {
            rightX++;
            if(value <= integers[y][i]) {
                break;
            }

        }
        //upY
        int upY = 0;
        for(int i = y - 1; i >= 0 ; i--) {
            upY++;
            if(value <= integers[i][x]) {
                break;
            }

        }
        //downY
        int downY = 0;
        for(int i = y + 1; i < integers.length; i++) {
            downY++;
            if(value <= integers[i][x]) {
                break;
            }
        }
        System.out.format("leftX = %d, rightX = %d, upY = %d, downY = %d%n", leftX, rightX, upY, downY);
        return leftX * rightX * upY * downY;
    }

    boolean isVisible(int x, int y, Integer[][] integers) {
        System.out.format("Checking [%d][%d] = %d%n",y, x, integers[y][x]);
        // leftX Check
        if(x == 0) {
            return true;
        }
        int value = integers[y][x];
        for(int i = x - 1; i >= 0; i--) {
            if(value <= integers[y][i]) {
                break;
            }
            if(i == 0) {
                return true;
            }
        }

        //rightX Check
        if(x == integers.length - 1) {
            return true;
        }
        for(int i = x + 1; i < integers.length; i++) {
            if(value <= integers[y][i]) {
                break;
            }
            if(i == integers.length - 1) {
                return true;
            }
        }

        //upY Check
        if(y == 0) {
            return true;
        }
        for(int i = y - 1; i >= 0 ; i--) {
            if(value <= integers[i][x]) {
                break;
            }
            if(i == 0) {
                return true;
            }
        }

        // downY Check
        if(y == integers.length - 1) {
            return true;
        }
        for(int i = y + 1; i < integers.length; i++) {
            if(value <= integers[i][x]) {
                break;
            }
            if(i == integers.length - 1) {
                return true;
            }
        }
        return false;
    }
}
