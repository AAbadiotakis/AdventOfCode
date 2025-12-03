package style.hidden._2025;

import style.hidden.utils.Runner;

class day1 extends Runner {
    public day1(String path) throws Exception {
        super(path);
    }

    public static void main() throws Exception {
        Runner runner = new day1("_2025/day1input.txt");
        runner.part1Code();
//        runner.part2Code();
    }

    @Override
    public void part1Code() {
        int i = 50;
        int count = 0;
        for (String line : getLines()) {
            System.out.println("Line=" + line);
            String rotation = line.split("")[0];
            Integer amount = Integer.parseInt(line.replace("R","").replace("L", ""));
            if("L".equalsIgnoreCase(rotation)) {
                i -= amount;
                while(i < 0) {
                    i = 100 + i;
                }
            } else {
                i += amount;
                while(i >= 100) {
                    i = i - 100;
                }
            }
            if(i == 0) {
                count++;
            }
            System.out.println("i="+i);
            System.out.println("count="+count);
        }
        System.out.println("Answer Part 1: " + count);
    }

    @Override
    public void part2Code() {
        int dialPosition = 50;
        int zeroCount = 0;
        for (String line : getLines()) {
            System.out.println("Line=" + line);
            String rotation = line.split("")[0];
            Integer amount = Integer.parseInt(line.replace("R","").replace("L", ""));
            if("L".equalsIgnoreCase(rotation)) {
                for(int i = 0; i < amount; i++) {
                    dialPosition--;
                    // if dialPosition = -1, GOTO 99
                    if(dialPosition == -1) {
                        dialPosition = 99;
                    }
                    if(dialPosition == 0) {
                        zeroCount++;
                    }
                }
            } else {
                for(int i = 0; i < amount; i++) {
                    dialPosition++;
                    if(dialPosition == 100) {
                        zeroCount++;
                        dialPosition = 0;
                    }
                }
            }
        }
        System.out.println("zeroCount=" + zeroCount);
    }
}
