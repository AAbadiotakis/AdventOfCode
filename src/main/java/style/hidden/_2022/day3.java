package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.*;
import java.util.stream.Collectors;

public class day3 extends Runner {

    public day3(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day3("_2022/day3input.txt");
//        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        int answer = 0;
        for(String line : getLines()) {
            System.out.println(line);
            Set<Character> firstSack = new HashSet<>();
            Set<Character> secondSack = new HashSet<>();
            for(int i = 0; i < line.length()/2; i++) {
                firstSack.add(line.charAt(i));
            }
            for (int i = line.length()/2; i < line.length(); i++) {
                secondSack.add(line.charAt(i));
            }

            List<Character> charsMatching = firstSack.stream().filter(c -> secondSack.contains(c)).collect(Collectors.toList());

            Character ch = charsMatching.get(0);
            if(Character.isUpperCase(ch)) {
//                System.out.println("Value of matching Char = " + ((int) charsMatching.get(0) - (64 - 26)));
                answer += ((int) charsMatching.get(0) - (64 - 26));
            } else {
//                System.out.println("Value of matching Char = " + ((int) charsMatching.get(0) - 96));
                answer += ((int) charsMatching.get(0) - 96);
            }
        }
        System.out.println("Answer Part 1 = " + answer);
    }

    @Override
    public void part2Code() {
        List<String> lines = getLines();
        int answer = 0;
        for(int i = 2; i < lines.size(); i=i+3) {
            List<Character> firstElf = convertCharArrayToList(lines.get(i-2).toCharArray());
            List<Character> secondElf = convertCharArrayToList(lines.get(i-1).toCharArray());
            List<Character> thirdElf = convertCharArrayToList(lines.get(i).toCharArray());

            Character charMatching = firstElf.stream()
                    .filter(ch -> secondElf.contains(ch))
                    .filter(ch -> thirdElf.contains(ch))
                    .findFirst()
                    .orElse(null);
            System.out.println("Found char Matching = " + charMatching);
            if(Character.isUpperCase(charMatching)) {
//                System.out.println("Value of matching Char = " + ((int) charsMatching.get(0) - (64 - 26)));
                answer += ((int) charMatching - (64 - 26));
            } else {
//                System.out.println("Value of matching Char = " + ((int) charsMatching.get(0) - 96));
                answer += ((int) charMatching - 96);
            }
        }
        System.out.println("Answer Part 2 = " + answer);
    }

    public List<Character> convertCharArrayToList(char[] chars) {
        List<Character> characters = new ArrayList<>();
        for(char ch : chars) {
            characters.add(ch);
        }
        return characters;
    }
}
