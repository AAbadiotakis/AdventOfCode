package style.hidden._2025;

import kotlin.Pair;
import style.hidden.utils.Runner;

import java.util.*;

public class day5 extends Runner {
    public day5(String path) throws Exception {
        super(path);
    }

    static void main() throws Exception {
        Runner runner = new day5("_2025/day5input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        List<Pair<Long, Long>> freshIngredients = new ArrayList<>();
        List<Long> availableIngredients = new ArrayList<>();
        boolean isFreshIngredientList = true;
        for(String line : getLines()) {
            if(line.isEmpty()) {
                isFreshIngredientList = false;
            } else if(isFreshIngredientList){
                Long firstInt = Long.valueOf(line.split("-")[0]);
                Long secondInt = Long.valueOf(line.split("-")[1]);
                freshIngredients.add(new Pair<>(firstInt, secondInt));
            } else if(!isFreshIngredientList) {
                availableIngredients.add(Long.valueOf(line));
            }
        }

        int answer = 0;
        for(Long ingredient : availableIngredients) {
            for(Pair<Long, Long> freshIngredientPair : freshIngredients) {
                if(ingredient >= freshIngredientPair.component1() && ingredient <= freshIngredientPair.component2()) {
                    answer++;
                    break;
                }
            }

        }
        System.out.println("Answer Part 1 = " + answer);
    }

    @Override
    public void part2Code() {
        List<Pair<Long, Long>> freshIngredients = new ArrayList<>();
        boolean isFreshIngredientList = true;
        for(String line : getLines()) {
            if(line.isEmpty()) {
                isFreshIngredientList = false;
            } else if(isFreshIngredientList){
                Long firstInt = Long.valueOf(line.split("-")[0]);
                Long secondInt = Long.valueOf(line.split("-")[1]);
                freshIngredients.add(new Pair<>(firstInt, secondInt));
            }
        }

        freshIngredients.sort(Comparator.comparing(Pair::component1));
        long currentEnd = freshIngredients.getFirst().component2();

        long answer = currentEnd - freshIngredients.getFirst().component1() + 1;
        for(Pair<Long, Long> pair : freshIngredients) {
            if(pair.component1() > currentEnd) {
                answer += pair.component2() - pair.component1() + 1;
                currentEnd = pair.component2();
            } else if(pair.component1() <= currentEnd && pair.component2() > currentEnd){
                answer += pair.component2() - currentEnd;
                currentEnd = pair.component2();
            } else if(pair.component1() <= currentEnd && pair.component2() <= currentEnd) {
            } else {
                System.out.println("ERROR?");
            }

        }

        System.out.println("Answer Part 2 = " + answer);
        // Answer Part 2 = 355555479253787
    }

}
