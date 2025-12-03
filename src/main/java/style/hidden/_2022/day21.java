package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.HashMap;
import java.util.Map;

public class day21 extends Runner {

    public day21(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day21("_2022/day21input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    @Override
    public void part1Code() {
        Map<String, String> monkeyMap = new HashMap<>();
        for(String line : getLines()) {
            monkeyMap.put(line.split(":")[0].trim(), line.split(":")[1].trim());
        }

        while(true) {
            for(String key : monkeyMap.keySet()) {
                if(key.equals("root")) {
                    System.out.println("On Root key");
                    if(isNumeric(monkeyMap.get(key))) {
                        System.out.println("Root key is numeric");
                        return;
                    }

                }
                if(!isNumeric(monkeyMap.get(key))) {
                    System.out.println(monkeyMap.get(key));
                    String value = monkeyMap.get(key);
                    String first = value.split(" ")[0].trim();
                    String operator = value.split(" ")[1].trim();
                    String second = value.split(" ")[2].trim();
                    
                    if(monkeyMap.containsKey(first) && isNumeric(monkeyMap.get(first)) && monkeyMap.containsKey(second) && isNumeric(monkeyMap.get(second))) {
                        Double d = null;
                        switch(operator) {
                            case "+" -> d = Double.parseDouble(monkeyMap.get(first)) + Double.parseDouble(monkeyMap.get(second));
                            case "-" -> d = Double.parseDouble(monkeyMap.get(first)) - Double.parseDouble(monkeyMap.get(second));
                            case "*" -> d = Double.parseDouble(monkeyMap.get(first)) * Double.parseDouble(monkeyMap.get(second));
                            case "/" -> d = Double.parseDouble(monkeyMap.get(first)) / Double.parseDouble(monkeyMap.get(second));
                        };
                        System.out.format("for Monkey = %s, Value = %f%n", key, d);
                        monkeyMap.put(key, String.valueOf(d));
                    }
                }
            }
        }
    }

    boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }


    @Override
    public void part2Code() {
        Map<String, String> monkeyMap = new HashMap();
        String firstEquals = null;
        String secondEquals = null;
        for(String line : getLines()) {
            if(line.startsWith("root")) {
                String str = line.split(":")[1].trim();
                firstEquals = str.split(" ")[0].trim();
                secondEquals = str.split(" ")[2].trim();
            } else if(line.startsWith("humn")) {

            } else {
                monkeyMap.put(line.split(":")[0].trim(), line.split(":")[1].trim());
            }
        }
        while(true) {
            int i = 0;
            for(String key : monkeyMap.keySet()) {
                String value = monkeyMap.get(key);
                if(!isNumeric(value)) {
                    String first = value.split(" ")[0].trim();
                    String operator = value.split(" ")[1].trim();
                    String second = value.split(" ")[2].trim();
                    if(((monkeyMap.containsKey(first) && isNumeric(monkeyMap.get(first))) || isNumeric(first)) && ((monkeyMap.containsKey(second) && isNumeric(monkeyMap.get(second))) || isNumeric(second))) {
                        Double d = null;
                        Double firstD = isNumeric(first) ? Double.parseDouble(first) : Double.parseDouble(monkeyMap.get(first));
                        Double secondD = isNumeric(second) ? Double.parseDouble(second) : Double.parseDouble(monkeyMap.get(second));
                        switch(operator) {
                            case "+" -> d = firstD + secondD;
                            case "-" -> d = firstD - secondD;
                            case "*" -> d = firstD * secondD;
                            case "/" -> d = firstD / secondD;
                        };
                        System.out.format("for Monkey = %s, Value = %f%n", key, d);
                        monkeyMap.put(key, String.valueOf(d));
                    } else if(monkeyMap.containsKey(first) && isNumeric(monkeyMap.get(first))) {
                        value = value.replaceAll(first, monkeyMap.get(first));
                        monkeyMap.put(key, value);
                    } else if(monkeyMap.containsKey(second) && isNumeric(monkeyMap.get(second))) {
                        value = value.replaceAll(second, monkeyMap.get(second));
                        monkeyMap.put(key, value);
                    } else {
                        i++;
                    }
                } else {
                    i++;
                }

            }
            if(i == monkeyMap.size()) {
                break;
            }
        }
        int humn = 24000;
        while(true) {
            Map<String, String> tempMap = new HashMap<>(monkeyMap);
            tempMap.put("humn", String.valueOf(humn));
            while(true) {
                int i = 0;
                for(String key : tempMap.keySet()) {
                    String value = tempMap.get(key);
                    if(!isNumeric(value)) {
                        String first = value.split(" ")[0].trim();
                        String operator = value.split(" ")[1].trim();
                        String second = value.split(" ")[2].trim();
                        if(((tempMap.containsKey(first) && isNumeric(tempMap.get(first))) || isNumeric(first)) && ((tempMap.containsKey(second) && isNumeric(tempMap.get(second))) || isNumeric(second))) {
                            Double d = null;
                            Double firstD = isNumeric(first) ? Double.parseDouble(first) : Double.parseDouble(tempMap.get(first));
                            Double secondD = isNumeric(second) ? Double.parseDouble(second) : Double.parseDouble(tempMap.get(second));
                            switch(operator) {
                                case "+" -> d = firstD + secondD;
                                case "-" -> d = firstD - secondD;
                                case "*" -> d = firstD * secondD;
                                case "/" -> d = firstD / secondD;
                            };
//                            System.out.format("for Monkey = %s, Value = %f%n", key, d);
                            tempMap.put(key, String.valueOf(d));
                        } else if(tempMap.containsKey(first) && isNumeric(tempMap.get(first))) {
                            value = value.replaceAll(first, tempMap.get(first));
                            tempMap.put(key, value);
                        } else if(tempMap.containsKey(second) && isNumeric(tempMap.get(second))) {
                            value = value.replaceAll(second, tempMap.get(second));
                            tempMap.put(key, value);
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                    }

                }
                if(i == tempMap.size()) {
                    break;
                }
            }
//            System.out.println("Finished updated tempMap");
//            System.out.println("FirstEquals = " + tempMap.get(firstEquals));
//            System.out.println("SecondEquals = " + tempMap.get(secondEquals));
//            System.out.println("humn = " + humn);
            if(Double.parseDouble(tempMap.get(firstEquals)) == Double.parseDouble(tempMap.get(secondEquals))) {
                System.out.println("Found Answer humn = " + humn);
                return;
            } else {
                humn++;
                if(humn % 100 == 0) {
                    System.out.println("humn = " + humn);
                }
            }
        }
/*        String firstEqualsString = monkeyMap.get(firstEquals);
        System.out.println(firstEqualsString);
        while(!isNumeric(firstEqualsString)) {
            int i = 0;
            for(String str : firstEqualsString.split(" ")) {
                if(monkeyMap.containsKey(str)) {
                    firstEqualsString = firstEqualsString.replaceAll(str, "( " + monkeyMap.get(str) + " ) ");
                    System.out.println(firstEqualsString);
                } else {
                    i++;
                }
            }
            System.out.format("i = %d, size() = %d%n", i, firstEqualsString.split(" ").length);
            if(i == firstEqualsString.split(" ").length) {
                break;
            }
        }
        firstEqualsString = firstEqualsString.replaceAll(" ", "");
        String secondEqualsString = monkeyMap.get(secondEquals);
        System.out.println(secondEqualsString);
        while(!isNumeric(secondEqualsString)) {
            int i = 0;
            for(String str : secondEqualsString.split(" ")) {
                if(monkeyMap.containsKey(str)) {
                    secondEqualsString = secondEqualsString.replaceAll(str, monkeyMap.get(str));
                    System.out.println(secondEqualsString);
                } else {
                    i++;
                }
            }
            System.out.format("i = %d, size() = %d%n", i, secondEqualsString.split(" ").length);
            if(i == secondEqualsString.split(" ").length) {
                break;
            }
        }
        secondEqualsString = secondEqualsString.replaceAll(" ", "");

        System.out.println("firstEqualsString = " + firstEqualsString);
        System.out.println("secondEqualsString = " + secondEqualsString); */
    }

}
