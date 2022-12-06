package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class day5 extends Runner {

    public day5(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day5("_2022/day5input.txt");
//        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        List<Stack<Character>> characterStackList = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            characterStackList.add(new Stack<>());
        }
        boolean isStartingStacks = true;
        for(String line : getLines()) {
            if(line.startsWith(" 1")) {
                isStartingStacks = false;
                System.out.println("Reverse Sort stacks ...");
                reverseSortStacks(characterStackList);
                printStack(characterStackList);
            } else if(isStartingStacks) {
                for(int i = 1; i < line.split("").length; i=i+4) {
                    if(line.split("")[i].isEmpty() || line.split("")[i].isBlank()) {
                        continue;
                    }
                    Character ch = line.split("")[i].charAt(0);
                    if(i == 1) {
                        characterStackList.get(0).push(ch);
                    } else if(i == 5) {
                        characterStackList.get(1).push(ch);
                    } else if(i == 9) {
                        characterStackList.get(2).push(ch);
                    } else if(i == 13) {
                        characterStackList.get(3).push(ch);
                    } else if(i == 17) {
                        characterStackList.get(4).push(ch);
                    } else if(i == 21) {
                        characterStackList.get(5).push(ch);
                    } else if(i == 25) {
                        characterStackList.get(6).push(ch);
                    } else if(i == 29) {
                        characterStackList.get(7).push(ch);
                    } else if(i == 33) {
                        characterStackList.get(8).push(ch);
                    }
                }
            } else if(!line.isBlank() || !line.isEmpty()){
                int moveNum = Integer.parseInt(line.split(" ")[1]);
                int fromNum = Integer.parseInt(line.split(" ")[3]) - 1;
                int toNum = Integer.parseInt(line.split(" ")[5]) - 1;
                for(int i = 1; i<= moveNum; i++) {
                    characterStackList.get(toNum).push(characterStackList.get(fromNum).pop());
                }
            }
        }
        for(int i = 0; i < characterStackList.size() ; i++) {
            System.out.print(characterStackList.get(i).pop());
        }
    }

    @Override
    public void part2Code() {
        List<Stack<Character>> characterStackList = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            characterStackList.add(new Stack<>());
        }
        boolean isStartingStacks = true;
        for(String line : getLines()) {
            if(line.startsWith(" 1")) {
                isStartingStacks = false;
                System.out.println("Reverse Sort stacks ...");
                reverseSortStacks(characterStackList);
                printStack(characterStackList);
            } else if(isStartingStacks) {
                for(int i = 1; i < line.split("").length; i=i+4) {
                    if(line.split("")[i].isEmpty() || line.split("")[i].isBlank()) {
                        continue;
                    }
                    Character ch = line.split("")[i].charAt(0);
                    if(i == 1) {
                        characterStackList.get(0).push(ch);
                    } else if(i == 5) {
                        characterStackList.get(1).push(ch);
                    } else if(i == 9) {
                        characterStackList.get(2).push(ch);
                    } else if(i == 13) {
                        characterStackList.get(3).push(ch);
                    } else if(i == 17) {
                        characterStackList.get(4).push(ch);
                    } else if(i == 21) {
                        characterStackList.get(5).push(ch);
                    } else if(i == 25) {
                        characterStackList.get(6).push(ch);
                    } else if(i == 29) {
                        characterStackList.get(7).push(ch);
                    } else if(i == 33) {
                        characterStackList.get(8).push(ch);
                    }
                }
            } else if(!line.isBlank() || !line.isEmpty()){
                int moveNum = Integer.parseInt(line.split(" ")[1]);
                int fromNum = Integer.parseInt(line.split(" ")[3]) - 1;
                int toNum = Integer.parseInt(line.split(" ")[5]) - 1;

                if(moveNum == 1) {
                    characterStackList.get(toNum).push(characterStackList.get(fromNum).pop());
                } else {
                    List<Character> chars = new ArrayList<>();
                    for(int i = 1; i<= moveNum; i++) {
                        chars.add(characterStackList.get(fromNum).pop());
                    }
                    for(int i = chars.size()-1; i >= 0; i--) {
                        characterStackList.get(toNum).push(chars.get(i));
                    }
                }

            }
        }
        for(int i = 0; i < characterStackList.size() ; i++) {
            System.out.print(characterStackList.get(i).pop());
        }
    }

    void printStack(List<Stack<Character>> characterStackList) {
        for(int i = 0; i < characterStackList.size();i++) {
            System.out.format("Stack %d = %s%n", i, characterStackList.get(i));
        }
    }

    void reverseSortStacks(List<Stack<Character>> characterStackList) {
        for(int i = 0; i < characterStackList.size(); i++) {
            Stack stack = characterStackList.get(i);
            Stack REVERSE_STACK = new Stack<>();
            while(!stack.isEmpty()) {
                REVERSE_STACK.push(stack.pop());
            }
            characterStackList.set(i, REVERSE_STACK);
        }
    }
}
