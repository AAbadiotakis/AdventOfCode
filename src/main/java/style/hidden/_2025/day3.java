package style.hidden._2025;

import style.hidden.utils.Runner;

public class day3 extends Runner {
    public day3(String path) throws Exception {
        super(path);
    }

    static void main() throws Exception {
        Runner runner = new day3("_2025/day3input.txt");
        runner.part1Code();
//        runner.part2Code();
    }

    @Override
    public void part1Code() {
        int sum = 0;
        for(String str : getLines()) {
            int largestJoltage = 0;
            for(int i = 0; i < str.length(); i++) {
                int firstNumber = Character.getNumericValue(str.charAt(i));
                for(int j = i +1; j < str.length(); j++) {
                    int secondNumber = Character.getNumericValue(str.charAt(j));
                    int joltage = (firstNumber * 10) + secondNumber;
                    if(largestJoltage < joltage) {
                        largestJoltage = joltage;
                    }
                }
            }
            System.out.println("Largest Joltage=" + largestJoltage);
            sum += largestJoltage;
        }
        System.out.println("Answer Part1: " + sum);
    }

    @Override
    public void part2Code() {
        long sum = 0;
        for(String str: getLines()) {
            StringBuilder largestJoltage = new StringBuilder();
            int numbersLeft = 12;
            int index = 0;
            while(numbersLeft > 0) {
                String strLeft = str.substring(index, str.length() - numbersLeft + 1);

                int currentIndex = largestNumberPositionInString(strLeft);
                largestJoltage.append(strLeft.charAt(currentIndex));

                index = index + currentIndex + 1;
                numbersLeft--;
            }
            System.out.println("Largest Joltage = " + largestJoltage);
            sum += Long.parseLong(largestJoltage.toString());
        }
        System.out.println("Answer Part 2 = " + sum);
    }

    int largestNumberPositionInString(String str) {
        int largestNumber = 0;
        int position = 0;
        String[] strList = str.split("");
        for(int i = 0; i < strList.length; i++) {
            if(largestNumber < Integer.parseInt(strList[i])) {
                largestNumber = Integer.parseInt(strList[i]);
                position = i;
            }
        }
        return position;
    }

}
