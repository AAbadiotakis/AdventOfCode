package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.*;
import java.util.stream.Collectors;

public class day11 extends Runner {

    public day11(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day11("_2022/day11input.txt");
//        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        Integer monkeyNum = null;
        String operation = null;
        String testCase = null;
        Integer ifTrue = null;
        Integer ifFalse;
        Map<Integer, Monkey> monkeyMap = new HashMap<>();
        Map<Integer, List<Long>> monkeyItems = new HashMap<>();
        Map<Integer, Integer> monkeyInspections = new HashMap<>();
        for(String line : getLines()) {
            if(line.startsWith("Monkey"))
                monkeyNum = Integer.parseInt(line.split(" ")[1].replace(":", "").trim());
            if(line.trim().startsWith("Starting items:"))
                monkeyItems.put(monkeyNum, Arrays.stream(line.split(":")[1].split(",")).map(String::trim).map(Long::parseLong).collect(Collectors.toList()));
            if(line.trim().startsWith("Operation:"))
                operation = line.split("=")[1].trim();
            if(line.trim().startsWith("Test:"))
                testCase = line.split(":")[1].trim();
            if(line.trim().startsWith("If true:"))
                ifTrue = Integer.parseInt(line.split("monkey")[1].trim());
            if(line.trim().startsWith("If false:")) {
                ifFalse = Integer.parseInt(line.split("monkey")[1].trim());
                monkeyMap.put(monkeyNum, new Monkey(monkeyNum, operation, testCase, ifTrue, ifFalse));
                monkeyInspections.put(monkeyNum, 0);
            }
        }

        for(int round = 1; round <= 20; round++) {
            for(Integer key : monkeyMap.keySet()) {
                Monkey monkey = monkeyMap.get(key);
                List<Long> items = monkeyItems.get(key);
                monkeyItems.put(key, new ArrayList<>());

                for(Long item : items) {
                    item = operation(item, monkey.operation) / 3;
                    Integer throwTo;
                    if(isTestCaseTrue(item, monkey.testCase))
                        throwTo = monkey.ifTrue;
                    else
                        throwTo = monkey.ifFalse;
                    List<Long> tempList = monkeyItems.get(throwTo);
                    if(tempList == null) {
                        tempList = new ArrayList<>();
                    }
                    tempList.add(item);
//                    System.out.format("Throwing item=%d to MonkeyNum=%d%n", item, throwTo);
                    monkeyItems.put(throwTo, tempList);
                    monkeyInspections.put(key, monkeyInspections.get(key)+1);
                }
            }
            System.out.format("MonkeyItems after round %d = %s%n", round, monkeyItems);
        }
        System.out.println("Monkey Inspections = " + monkeyInspections);
        int largestMonkeyInspection = monkeyInspections.values().stream().sorted(Comparator.reverseOrder()).findFirst().get();
        int secondLargestMonkeyInspection = monkeyInspections.values().stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.format("Monkey Business = %d * %d = %d", largestMonkeyInspection, secondLargestMonkeyInspection, largestMonkeyInspection * secondLargestMonkeyInspection);
    }

    Long operation(Long old, String operation) {
        String firstInput = operation.split(" ")[0].trim();
        Long firstValue = ("old".equals(firstInput)) ? old : Long.parseLong(firstInput);
        String operator = operation.split(" ")[1].trim();
        String secondInput = operation.split(" ")[2].trim();
        Long secondValue = ("old".equals(secondInput)) ? old : Long.parseLong(secondInput);

        return switch (operator) {
            case "+" -> firstValue + secondValue;
            case "*" -> firstValue * secondValue;
            case "-" -> firstValue - secondValue;
            case "/" -> firstValue / secondValue;
            default -> throw new RuntimeException("ERROR WHILE TRYING TO OPERATE");
        };
    }

    boolean isTestCaseTrue(long value, String testCase) {
        if(testCase.startsWith("divisible by")) {
            return value % Integer.parseInt(testCase.split("by ")[1]) == 0;
        }
        System.out.println("testCase = " + testCase);
        throw new RuntimeException("testCase not accounted for");
    }

    @Override
    public void part2Code() {
        Integer monkeyNum = null;
        String operation = null;
        String testCase = null;
        Integer ifTrue = null;
        Integer ifFalse;
        Map<Integer, Monkey> monkeyMap = new HashMap<>();
        Map<Integer, List<Long>> monkeyItems = new HashMap<>();
        Map<Integer, Integer> monkeyInspections = new HashMap<>();
        for(String line : getLines()) {
            if(line.startsWith("Monkey"))
                monkeyNum = Integer.parseInt(line.split(" ")[1].replace(":", "").trim());
            if(line.trim().startsWith("Starting items:"))
                monkeyItems.put(monkeyNum, Arrays.stream(line.split(":")[1].split(",")).map(String::trim).map(Long::parseLong).collect(Collectors.toList()));
            if(line.trim().startsWith("Operation:"))
                operation = line.split("=")[1].trim();
            if(line.trim().startsWith("Test:"))
                testCase = line.split(":")[1].trim();
            if(line.trim().startsWith("If true:"))
                ifTrue = Integer.parseInt(line.split("monkey")[1].trim());
            if(line.trim().startsWith("If false:")) {
                ifFalse = Integer.parseInt(line.split("monkey")[1].trim());
                monkeyMap.put(monkeyNum, new Monkey(monkeyNum, operation, testCase, ifTrue, ifFalse));
                monkeyInspections.put(monkeyNum, 0);
            }
        }
        List<Integer> divisibleBy = new ArrayList<>();
        Long LCM = 1L;
        for(Monkey m : monkeyMap.values()) {
            LCM *= Integer.parseInt(m.testCase.split("by ")[1]);
            divisibleBy.add(Integer.parseInt(m.testCase.split("by ")[1]));
        }

        System.out.println("Divisible By = " + divisibleBy);
        System.out.println("LCM = " + LCM);

        for(int round = 1; round <= 10000; round++) {
            for(Integer key : monkeyMap.keySet()) {
                Monkey monkey = monkeyMap.get(key);
                List<Long> items = monkeyItems.get(key);
                monkeyItems.put(key, new ArrayList<>());

                for(Long item : items) {
                    item = operation(item, monkey.operation) % LCM;
                    Integer throwTo;

                    if(isTestCaseTrue(item, monkey.testCase))
                        throwTo = monkey.ifTrue;
                    else
                        throwTo = monkey.ifFalse;
                    List<Long> tempList = monkeyItems.get(throwTo);
                    if(tempList == null) {
                        tempList = new ArrayList<>();
                    }
                    tempList.add(item);
//                    System.out.format("Throwing item=%d to MonkeyNum=%d%n", item, throwTo);
                    monkeyItems.put(throwTo, tempList);
                    monkeyInspections.put(key, monkeyInspections.get(key)+1);
                }
            }
            System.out.format("MonkeyItems after round %d = %s%n", round, monkeyItems);
        }
        System.out.println("Monkey Inspections = " + monkeyInspections);
        long largestMonkeyInspection = monkeyInspections.values().stream().sorted(Comparator.reverseOrder()).findFirst().get();
        long secondLargestMonkeyInspection = monkeyInspections.values().stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.format("Monkey Business = %d * %d = %d", largestMonkeyInspection, secondLargestMonkeyInspection, largestMonkeyInspection * secondLargestMonkeyInspection);
    }

    Integer reduceValue(Integer value, List<Integer> divisibleBy) {
        Integer LCM = 1;
        for(Integer i : divisibleBy) {
            LCM = LCM * i;
        }
        return value % LCM;
    }

    class Monkey {
        Integer monkeyNum;
        String operation;
        String testCase;
        Integer ifTrue;
        Integer ifFalse;

        Monkey(Integer monkeyNum, String operation, String testCase, Integer ifTrue, Integer ifFalse) {
            this.monkeyNum = monkeyNum;
            this.operation = operation;
            this.testCase = testCase;
            this.ifTrue = ifTrue;
            this.ifFalse = ifFalse;
        }
    }

}
