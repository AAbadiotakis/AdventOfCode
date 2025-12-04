package style.hidden._2025;

import style.hidden.utils.Runner;

import java.util.ArrayList;
import java.util.List;

public class day2 extends Runner {

    public day2(String path) throws Exception {
        super(path);
    }

    static void main() throws Exception {
        Runner runner = new day2("_2025/day2input.txt");
//        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        long answer = 0;
        for(String line : getLines()) {
            List<String> productIdRanges = List.of(line.split(","));
            for(String productId : productIdRanges) {
                long firstProductId = Long.parseLong(productId.split("-")[0]);
                long secondProductId = Long.parseLong(productId.split("-")[1]);
                for(long i = firstProductId; i <= secondProductId; i++) {
                    if(isInvalidNumber(i)) {
                        System.out.println("Adding: " +i);
                        answer+=i;
                    }
                }
            }

        }
        System.out.println("Answer Part1: " + answer);
    }

    boolean isInvalidNumber(long number) {
        int numberLength = String.valueOf(number).length();
        int numberLengthHalved = numberLength / 2;
        String firstHalfNumber = String.valueOf(number).substring(0,numberLengthHalved);
        String secondHalfNumber = String.valueOf(number).substring(numberLengthHalved);
        return firstHalfNumber.equals(secondHalfNumber);
    }

    @Override
    public void part2Code() {
        long answer = 0;
        for(String line : getLines()) {
            List<String> productIdRanges = List.of(line.split(","));
            for(String productId : productIdRanges) {
                long firstProductId = Long.parseLong(productId.split("-")[0]);
                long secondProductId = Long.parseLong(productId.split("-")[1]);
                for(long i = firstProductId; i <= secondProductId; i++) {
                    if(isInvalidNumberPart2(i)) {
                        System.out.println("Adding: " +i);
                        answer+=i;
                    }
                }
            }

        }
        System.out.println("Answer Part1: " + answer);
    }

    boolean isInvalidNumberPart2(long number) {
        String numberString = String.valueOf(number);
        int numberLength = numberString.length();
        for(int i = 1; i <= numberLength/2; i++) {
            List<String> numberSplit = new ArrayList<>();
            for(int j = 0; j < numberLength; j+=i) {
                if(j+i > numberLength){
                    numberSplit.add(numberString.substring(j, numberLength));
                    break;
                }
                numberSplit.add(numberString.substring(j, j+i));
            }
            for(int k = 1; k < numberSplit.size(); k++) {
                if(!numberSplit.getFirst().equals(numberSplit.get(k))) {
                    break;
                }
                if(k == numberSplit.size() - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
