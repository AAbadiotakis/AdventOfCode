package style.hidden._2021.day8;

import style.hidden.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class day8Runner {
    static final int NUM_ZERO = 6;
    static final int NUM_ONE = 2; // EASY
    static final int NUM_TWO = 5;
    static final int NUM_THREE = 5;
    static final int NUM_FOUR = 4; // EASY
    static final int NUM_FIVE = 5;
    static final int NUM_SIX = 6;
    static final int NUM_SEVEN = 3; // EASY
    static final int NUM_EIGHT = 7; // EASY
    static final int NUM_NINE = 6;

    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day8input.txt");
        int counter = 0;
        long part2Answer = 0;
        for(String line : lines) {

//            System.out.println(line.split("\\|")[0].trim());
//            System.out.println(line.split("\\|")[1].trim());
            String[] signalPatterns = line.split("\\|")[0].trim().split(" ");
            String[] outputValues = line.split("\\|")[1].trim().split(" ");

            Map<Integer, String> map = new HashMap<>();

            List<String> sixDigits = new ArrayList<>();
            List<String> fiveDigits = new ArrayList<>();

            for(String signal : signalPatterns) {
                if(signal.length() == NUM_ONE) { // 2
                    map.put(1, signal);
                } else if(signal.length() == NUM_FOUR) { // 4
                    map.put(4, signal);
                } else if(signal.length() == NUM_SEVEN) { // 3
                    map.put(7, signal);
                } else if(signal.length() == NUM_EIGHT) { // 7
                    map.put(8, signal);
                } else if(signal.length() == 5) {
                    fiveDigits.add(signal);
                } else if(signal.length() == 6) {
                    sixDigits.add(signal);
                }
            }

            // Find 9 using 7 and 4
            map.put(9,sixDigits.stream()
                    .filter(s -> allCharsExist(map.get(7), s))
                    .filter(s -> allCharsExist(map.get(4), s))
                    .findFirst()
                    .get());
            char a = differentChar(map.get(7), map.get(1));
            char e = differentChar(map.get(8), map.get(9));
            String bd = removeChars(map.get(1),map.get(4));
            char g = differentChar(map.get(8), String.valueOf(a) + String.valueOf(e) + bd + map.get(1));

            map.put(6,sixDigits.stream()
                    .filter(s -> s.contains(String.valueOf(a)))
                    .filter(s -> allCharsExist(bd, s))
                    .filter(s -> s.contains(String.valueOf(e)))
                    .filter(s -> s.contains(String.valueOf(g)))
                    .findFirst()
                    .get());

            char c = differentChar(map.get(8), map.get(6));
            char f = differentChar(map.get(1), String.valueOf(c));

            System.out.println(map);
            map.put(5,fiveDigits.stream()
                    .filter(s -> s.contains(String.valueOf(a)))
                    .filter(s -> allCharsExist(bd, s))
                    .filter(s -> s.contains(String.valueOf(f)))
                    .filter(s -> s.contains(String.valueOf(g)))
                    .findFirst()
                    .get());

            map.put(3,fiveDigits.stream()
                    .filter(s -> allCharsExist(map.get(7), s))
                    .filter(s -> s.contains(String.valueOf(g)))
                    .findFirst()
                    .get());
            char d = differentChar(map.get(3), map.get(7)+g);
            char b = differentChar(bd, String.valueOf(d));

            map.put(2,fiveDigits.stream()
                    .filter(s -> s.contains(String.valueOf(a)))
                    .filter(s -> s.contains(String.valueOf(c)))
                    .filter(s -> s.contains(String.valueOf(d)))
                    .filter(s -> s.contains(String.valueOf(e)))
                    .filter(s -> s.contains(String.valueOf(g)))
                    .findFirst()
                    .get());
            map.put(0,sixDigits.stream()
                    .filter(s -> allCharsExist(map.get(7), s))
                    .filter(s -> s.contains(String.valueOf(b)))
                    .filter(s -> s.contains(String.valueOf(e)))
                    .filter(s -> s.contains(String.valueOf(g)))
                    .findFirst()
                    .get());

            System.out.println(map.toString());

            String outputValue = "";
            for(String output : outputValues) {
                for(int i : map.keySet()) {
                    if(output.length() == map.get(i).length()) {
                        if(allCharsExist(output, map.get(i))) {
                            outputValue += String.valueOf(i);
                            System.out.println("i="+ i);
                        }
                    }
                }
                if(output.length() == NUM_ONE || output.length() == NUM_FOUR || output.length() == NUM_SEVEN || output.length() == NUM_EIGHT) {
                    counter++;
                }
            }
            part2Answer += Long.valueOf(outputValue);
            System.out.println("part2Answer = " + part2Answer);
        }
        System.out.println("Answer Part 1 = " + counter);
        System.out.println("Answer Part 2 = " + part2Answer);
    }

    // Check if all s1 chars exist in s2
    static boolean allCharsExist(String s1, String s2) {
        for(int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if(s2.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    //s1 should be the longer string
    static char differentChar(String s1, String s2) {
        for(int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if(s2.indexOf(c) == -1) {
                return c;
            }
        }
        throw new RuntimeException("Character not Found");
    }

    static String removeChars(String s1, String s2) {
        StringBuilder response = new StringBuilder(s2);
        for(int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            for(int j = 0; j < response.length(); j++) {
                if(response.charAt(j) == c) {
                    response.deleteCharAt(j);
                }
            }
        }
        return response.toString();
    }
}
