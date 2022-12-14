package style.hidden._2022;

import com.google.gson.Gson;
import style.hidden.utils.Runner;

import java.util.*;

public class day13 extends Runner {

    public day13(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day13("_2022/day13input.txt");
        runner.part1Code();
        runner.part2Code();
    }

    static volatile int count = 0;
    static volatile int pairNum = 1;

    @Override
    public void part1Code() {
        ArrayList<?> left = null;
        ArrayList<?> right = null;
        int pairNum = 1;
        List<ArrayList<?>> allRowsSortedList = new ArrayList<>();
        for(String line : getLines()) {
            if(line.isEmpty() || line.isBlank()) {
                left = null; right = null;
                continue;
            }
            if(left == null) {
                left = new Gson().fromJson(line, ArrayList.class);
                allRowsSortedList.add(left);
            } else {
                right = new Gson().fromJson(line, ArrayList.class);
                allRowsSortedList.add(right);
                System.out.format("NEWLINE! left = %s, right = %s%n", left, right);
                Boolean comparison = compareTwoArrays(left, right);
                if(comparison == null) {
                    throw new RuntimeException();
                }
                if(comparison) {
                    count += pairNum;
                }
                pairNum++;
                System.out.format("Final Comparison of left and right = %b%n", comparison);
                System.out.println("Count = " + count);
            }
        }

        System.out.println("Final Count = " + count);
        ArrayList dividerPacket1 = new ArrayList();
        ArrayList subDividerPacket1 = new ArrayList();
        subDividerPacket1.add(2.0);
        dividerPacket1.add(subDividerPacket1);
        ArrayList dividerPacket2 = new ArrayList();
        ArrayList subDividerPacket2 = new ArrayList();
        subDividerPacket2.add(6.0);
        dividerPacket2.add(subDividerPacket2);

        allRowsSortedList.add(dividerPacket1);
        allRowsSortedList.add(dividerPacket2);


        allRowsSortedList.sort(new Comparator<ArrayList<?>>() {
            public int compare(ArrayList<?> o1, ArrayList<?> o2) {
                if (Boolean.TRUE.equals(compareTwoArrays(o1, o2))) {
                    return -1;
                } else if (Boolean.FALSE.equals(compareTwoArrays(o1, o2))) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for(List l : allRowsSortedList) {
            System.out.println(l);
        }
        System.out.format("dividerPacket1=%d, dividerPacket2=%d%n", allRowsSortedList.indexOf(dividerPacket1)+1, allRowsSortedList.indexOf(dividerPacket2)+1);
        System.out.println("Decoder Key = " + (allRowsSortedList.indexOf(dividerPacket1)+1) * (allRowsSortedList.indexOf(dividerPacket2)+1));
    }

    Boolean compareTwoArrays(ArrayList<?> left, ArrayList<?> right) {
        System.out.format("Compare two arrays: left=%s, right=%s%n", left, right);
        for(int i = 0; i < Math.min(left.size(), right.size()); i++) {
            System.out.format("left.get(%s) = %s, right.get(%s) = %s%n", i, left.get(i), i, right.get(i));
            if(isBothInteger(left.get(i), right.get(i))) {
                if(numberConverter(left.get(i)) < numberConverter(right.get(i))) {
//                    count++;
//                    System.out.println("Count incremented (Both Integers Comparison). Count = " + count);
                    System.out.format("Successful Pair. left.get(i)=%s, right.get(i)=%s%n", left.get(i), right.get(i));
                    return true;
                } else if(numberConverter(left.get(i)) > numberConverter(right.get(i))) {
                    System.out.format("PAIR OUT OF ORDER. left.get(i)=%s, right.get(i)=%s%n", left.get(i), right.get(i));
                    return false;
                }
            } else if(isBothArray(left.get(i), right.get(i))) {
                System.out.println("IS BOTH ARRAY");
                Boolean b = compareTwoArrays((ArrayList) left.get(i), (ArrayList) right.get(i));
                if(Boolean.TRUE.equals(b)) {
                    return true;
                } else if(Boolean.FALSE.equals(b)) {
                    return false;
                }
            } else {
                System.out.println(" 1 ARRAY 1 NUM ");
                Boolean b;
                if(left.get(i) instanceof ArrayList) {
                    b = compareTwoArrays((ArrayList) left.get(i), new ArrayList<>(Collections.singleton((Double) right.get(i))));
                } else {
                    b = compareTwoArrays(new ArrayList<>(Collections.singleton((Double) left.get(i))), (ArrayList) right.get(i));
                }
                if(Boolean.TRUE.equals(b)) {
                    return true;
                } else if(Boolean.FALSE.equals(b)) {
                    return false;
                }
            }
        }

        if(left.size() == right.size()) {
            System.out.println("Does this happen?");
            return null;
        } else if(left.size() < right.size()){
            return true;
        } else {
            return false;
        }
    }

    boolean isBothInteger(Object left, Object right) {
        return ((left instanceof Integer || left instanceof Double) && (right instanceof Integer || right instanceof Double));
    }

    boolean isBothArray(Object left, Object right) {
        return (left instanceof ArrayList && right instanceof ArrayList);
    }


    Integer numberConverter(Object o) {
        return (o instanceof Integer) ? (Integer) o : ((Double) o).intValue();
    }

    @Override
    public void part2Code() {

    }

}
