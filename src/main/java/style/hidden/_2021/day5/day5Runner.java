package style.hidden._2021.day5;

import style.hidden.utils.FileReaderUtil;

import java.util.*;

public class day5Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day5input.txt");
        Set<Coordinate> coordinates = new HashSet<>();
        int counter = 0;
        Set<Coordinate> overlappedCoordinates = new HashSet<>();
        for(String line : lines) {
            int x1 = Integer.parseInt(line.split(" -> ")[0].split(",")[0]);
            int y1 = Integer.parseInt(line.split(" -> ")[0].split(",")[1]);
            int x2 = Integer.parseInt(line.split(" -> ")[1].split(",")[0]);
            int y2 = Integer.parseInt(line.split(" -> ")[1].split(",")[1]);
//            System.out.println("x1 = " + line.split(" -> ")[0]);
//            System.out.println("y1 = " + line.split(" -> ")[1]);
            if(x1 == x2) {
                int x = x1;
                if(y2 > y1) {
                    for(int y = y1; y <= y2; y++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                    }
                } else {
                    for(int y = y2; y <= y1; y++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                    }
                }
            }else if(y1 == y2) {
                int y = y1;
                if(x2 > x1) {
                    for(int x = x1; x <= x2; x++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                    }
                } else {
                    for(int x = x2; x <= x1; x++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                    }
                }
            } else {
                if(x2 > x1 && y2 > y1) {
                    int y = y1;
                    for(int x=x1;x<=x2;x++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                        y++;
                    }
                } else if(x2 > x1 && y2 < y1) {
                    int y = y1;
                    for(int x=x1;x<=x2;x++) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                        y--;
                    }
                } else if(x2 < x1 && y2 > y1) {
                    int y = y1;
                    for(int x=x1;x>=x2;x--) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                        y++;
                    }
                } else {
                    int y = y1;
                    for(int x=x1;x>=x2;x--) {
                        if(!coordinates.add(new Coordinate(x,y))) {
                            if(overlappedCoordinates.add(new Coordinate(x,y))) {
                                System.out.println("Coordinate overlap!");
                                counter++;
                            }
                        }
                        y--;
                    }
                }
            }
        }
        System.out.println(coordinates);
        System.out.println("Counter = " + counter);
        // NOT 7712
    }

    static class Coordinate {
        final int x;
        final int y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }

        @Override
        public boolean equals(Object obj){
            return (obj instanceof Coordinate && ((Coordinate) obj).x == this.x && ((Coordinate) obj).y == this.y);
        }

        @Override
        public int hashCode() {
            return x+y;
        }
    }
}
