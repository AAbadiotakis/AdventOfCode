package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.ArrayList;
import java.util.List;

public class day20 extends Runner {

    public day20(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day20("_2022/day20input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        System.out.println(getLines());
        List<String> mixedInputLines = mixInput(getLines());
        System.out.println(mixedInputLines);

        int thousandthNum = ((1000 % mixedInputLines.size()) + mixedInputLines.indexOf("0")) % mixedInputLines.size();
        int twoThousandthNum = ((2000 % mixedInputLines.size()) + mixedInputLines.indexOf("0")) % mixedInputLines.size();
        int threeThousandthNum = ((3000 % mixedInputLines.size()) + mixedInputLines.indexOf("0")) % mixedInputLines.size();
        System.out.println("1000th number = " + mixedInputLines.get(thousandthNum));
        System.out.println("2000th number = " + mixedInputLines.get(twoThousandthNum));
        System.out.println("3000th number = " + mixedInputLines.get(threeThousandthNum));
        int answer = Integer.parseInt(mixedInputLines.get(thousandthNum)) + Integer.parseInt(mixedInputLines.get(twoThousandthNum)) + Integer.parseInt(mixedInputLines.get(threeThousandthNum));
        System.out.println("Answer Part 1 = " + answer);
        // 512 is too low
        // 9505 is too low
    }

    List<String> mixInput(List<String> lines) {
        for(String line : new ArrayList<>(lines)) {
            int i = Integer.parseInt(line);
            int index = lines.indexOf(line);
            int set;
            if(i < 0) {
                set = i + index - 1;
                while(set < 0) {
                    set += lines.size();
                }
            } else {
                set = index + i;
                while(set >= lines.size()) {
                    set = set % lines.size();
                }
            }
            System.out.format("index = %d, i = %d, set = %d, lines.size() = %d%n", index, i, set, lines.size());
            try {
                lines.add(set, lines.remove(index));
            } catch (Exception e) {
                System.out.format("index = %d, i = %d, set = %d, lines.size() = %d%n", index, i, set, lines.size());
                throw e;
            }

            System.out.println(lines);
        }
        return lines;
    }

    @Override
    public void part2Code() {

    }

}
