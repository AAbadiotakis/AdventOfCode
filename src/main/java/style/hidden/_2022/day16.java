package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.*;
import java.util.stream.Collectors;

public class day16 extends Runner {

    public day16(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day16("_2022/day16input.txt");
        runner.part1Code();
//        runner.part2Code();
    }

    @Override
    public void part1Code() {
        Map<String, Valve> valveMap = new HashMap<>();
        for (String line : getLines()) {
            String valveName = line.split(" ")[1];
            int flowRate = Integer.parseInt(line.split("=")[1].split(";")[0]);
            List<String> linkedValves = new ArrayList<>();
            if(line.contains("valves")) {
                linkedValves = Arrays.stream(line.split("valves")[1].split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
            } else {
                String valve = line.split("valve")[1].trim();
                linkedValves.add(valve);
            }

            Valve valve;
            if(valveMap.containsKey(valveName)) {
                valve = valveMap.get(valveName);
                valve.flowRate = flowRate;
                valve.linkedValves = linkedValves;
            } else {
                valve = new Valve(valveName, flowRate, linkedValves);
            }
            valveMap.put(valveName, valve);
        }

        Valve currentValve = valveMap.get("AA"); // starting point
        Set<Valve> visitedValves = new HashSet<>();
        visitedValves.add(currentValve);
        int totalFlow = 0;
        for(int minutes = 0; minutes < 30; minutes++) {
            System.out.println("i = " + minutes);
            if(minutes == 0) {
                currentValve = valveMap.get("AA");
            }
            int maxFlowRate = Integer.MIN_VALUE;
            Valve nextValve = null;
            if(currentValve.linkedValves == null) {
                System.out.println(currentValve);
            }
            for (String linkedValve : currentValve.linkedValves) {
                if (visitedValves.stream()
                        .noneMatch(v -> Objects.equals(v.valveName, linkedValve))) {
                    if(maxFlowRate < valveMap.get(linkedValve).flowRate) {
                        nextValve = valveMap.get(linkedValve);
                        maxFlowRate = nextValve.flowRate;
                    }
                }
            }
            if(currentValve.isOpen) {
                currentValve = nextValve;
                System.out.println("New Valve = " + currentValve);
            } else if(currentValve.flowRate == 0) {
                currentValve = nextValve;
                System.out.println("New Valve = " + currentValve);
            } else {
                if(minutes + 2 < 30) {
                    currentValve.isOpen = true;
                    System.out.println("Opened Valve " + currentValve);
                    totalFlow += currentValve.flowRate;
                    minutes += 2;
                }
            }

        }
        System.out.println("Total Flow Rate = " + totalFlow);
    }

    Valve findMaxFlow(Map<String, Valve> valveMap, String startingValve, int minutesLeft) {
        Integer maxFlow = Integer.MIN_VALUE;
        Valve maxFlowValve;
        for(String key : valveMap.keySet()) {
            if(Objects.equals(key, startingValve) && !valveMap.get(key).isOpen) {
                if(valveMap.get(key).flowRate > maxFlow) {
                    maxFlowValve = valveMap.get(key);
                    maxFlow = maxFlowValve.flowRate;
                }
            }
            List<String> pathToKey = new ArrayList<>();
            Integer leastMovesToKey = Integer.MAX_VALUE;
            for(String linkedValve : valveMap.get(key).linkedValves) {

            }
        }
        return null;
    }

    int findLeastMovesToValve(Map<String, Valve> valveMap, String start, String end){
        Valve startingValve = valveMap.get(start);
        int leastMoves = Integer.MAX_VALUE;
        for(String linkedValve : startingValve.linkedValves) {
            if(Objects.equals(linkedValve, end)) {
                return 1;
            } else {
                int tmpLeastMoves = findLeastMovesToValve(valveMap, linkedValve, end);
                if(tmpLeastMoves < leastMoves)
                    leastMoves = tmpLeastMoves;
            }
        }
        return leastMoves;
    }

    @Override
    public void part2Code() {

    }

    class Valve {
        String valveName;
        int flowRate;
        List<String> linkedValves;
        boolean isOpen;

        Valve(String valveName, int flowRate, List<String> linkedValves) {
            this.valveName = valveName;
            this.flowRate = flowRate;
            this.linkedValves = linkedValves;
            isOpen = false;
        }

        Valve(String valveName) {
            this.valveName = valveName;
            isOpen = false;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Valve && Objects.equals(this.valveName, ((Valve) o).valveName);
        }

        @Override public String toString() {
            return String.format("Valve [name=%s, flowRate=%d, linkedValves=%s", valveName, flowRate, linkedValves);
        }
    }

}
