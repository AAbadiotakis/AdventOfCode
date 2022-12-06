package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.HashSet;
import java.util.Set;

public class day6 extends Runner {

    public day6(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day6("_2022/day6input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        String line = getLines().get(0);
        String[] characters = line.split("");
        for(int i = 3; i < characters.length; i++) {
            Set<String> previousChars = new HashSet<>();
            for(int j = 0; j < 4; j++) {
                if(!previousChars.add(characters[i-j])) {
                    break;
                }
            }
            if(previousChars.size() == 4) {
                System.out.println("Answer Part 1 = " + (i + 1));
                return;
            }
        }
    }

    @Override
    public void part2Code() {
        String line = getLines().get(0);
        String[] characters = line.split("");

        for(int i = 13; i < characters.length; i++) {
            Set<String> previousChars = new HashSet<>();
            for(int j = 0; j < 14 ; j++) {
                if(!previousChars.add(characters[i-j])) {
                    break;
                }
            }
            if(previousChars.size() == 14) {
                System.out.println("Answer Part 2 = " + (i + 1));
                return;
            }
        }
    }

}
