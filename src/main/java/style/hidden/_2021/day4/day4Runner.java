package style.hidden._2021.day4;

import style.hidden.utils.FileReaderUtil;

import java.util.*;
import java.util.stream.Collectors;

public class day4Runner {

    static final int boardWidth = 5;
    static final int boardHeight = 5;

    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day4input.txt");

        String firstLine = lines.get(0);
        List<Integer> bingoNumbers = Arrays.stream(firstLine.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println("bingo Numbers = " + bingoNumbers);

        List<Integer[][]> bingoBoardList = new ArrayList<>();
        int bingoBoardRow = 0;
        Integer[] bingoRowArray = new Integer[boardWidth];
        Integer[][] bingoBoard = new Integer[boardWidth][boardHeight];
        for(int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.isBlank() || line.isEmpty()) {
//                printBingoBoard(bingoBoard);
//                System.out.println(Arrays.deepToString(bingoBoard));
                bingoBoardList.add(Arrays.stream(bingoBoard).map(Integer[]::clone).toArray(Integer[][]::new));
                bingoBoardRow = 0;
                bingoBoard = new Integer[boardWidth][boardHeight];
            } else {
                List<Integer> bingoRow = Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty() || !s.isBlank()).map(Integer::parseInt).collect(Collectors.toList());
                bingoBoard[bingoBoardRow] = bingoRow.toArray(bingoRowArray).clone();
                bingoBoardRow++;
            }
        }
        bingoBoardList.add(bingoBoard);

        int j = 0;
        for(Integer[][] tempBingoBoard : bingoBoardList) {
            System.out.println("Board # " + j + " : " + Arrays.deepToString(tempBingoBoard));
            j++;
        }

        List<boolean[][]> calledBingoNumbersList = new ArrayList<>();
        for(int i = 0; i < bingoBoardList.size(); i++) {
            boolean[][] calledBingoNumbers = new boolean[boardWidth][boardHeight];
            calledBingoNumbersList.add(calledBingoNumbers);
        }

        int bingoNumberCount = 0;
        Set<Integer> bingoBoardsWon = new HashSet<>();
        while(bingoNumberCount < bingoNumbers.size()) {
            for (int i = 0; i < bingoBoardList.size(); i++) {
                Integer[][] playedBingoBoard = bingoBoardList.get(i);
                int bingoNum = bingoNumbers.get(bingoNumberCount);
                if(Arrays.stream(playedBingoBoard).anyMatch(val1 -> Arrays.stream(val1).anyMatch(val2 -> val2 == bingoNum))) {
                    Coordinate coordinate = findCoordinates(playedBingoBoard, bingoNum);
                    boolean[][] calledBingoNumbers = calledBingoNumbersList.get(i);
                    calledBingoNumbers[coordinate.x][coordinate.y] = true;

//                    System.out.println("Called Bingo Number = " + bingoNum + " Board # " + i);
//                    System.out.println(Arrays.deepToString(calledBingoNumbers));
                    if(checkIfWinningBoard(calledBingoNumbers, coordinate, playedBingoBoard)) {
                        System.out.println("Last called Bingo Number = " + bingoNum);
                        bingoBoardsWon.add(i);
                        System.out.println("Set = " + bingoBoardsWon);
                        if(bingoBoardsWon.size() == bingoBoardList.size()) {
                            return;
                        }
                    }
                }
            }
            bingoNumberCount++;
        }
    }

    static Coordinate findCoordinates(Integer[][] bingoBoard, int val) {
        for(int i =0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if(bingoBoard[i][j] == val) {
                    return new Coordinate(i,j);
                }
            }
        }
        return null;
    }

    static int sumAllUnmarkedNumbers(boolean[][] calledNumbersBoard, Integer[][] playedBingoBoard) {
        int sum = 0;
        for(int x = 0; x < calledNumbersBoard.length; x++) {
            for (int y = 0; y < calledNumbersBoard[x].length; y++) {
                if(!calledNumbersBoard[x][y]) {
                    sum += playedBingoBoard[x][y];
                }
            }
        }
        return sum;
    }

    static boolean checkIfWinningBoard(boolean[][] calledNumbersBoard, Coordinate coordinate, Integer[][] playedBingoBoard) {
        // Check the horizontal win condition
        boolean[] rowCalledNumbers = calledNumbersBoard[coordinate.x];
        for(int i = 0; i < calledNumbersBoard[coordinate.x].length; i++) {
            if(!calledNumbersBoard[coordinate.x][i]) {
                break;
            } else if(i == calledNumbersBoard[coordinate.x].length - 1) {
//                System.out.println(Arrays.deepToString(playedBingoBoard[coordinate.x]));
                System.out.println("Sum = " + sumAllUnmarkedNumbers(calledNumbersBoard, playedBingoBoard));
                return true;
            }
        }
        // Check Vertical Win Condition
        for(int i = 0; i < boardWidth; i++) {
            if(!calledNumbersBoard[i][coordinate.y]) {
                break;
            }
            if(i == boardWidth - 1) {
                System.out.println("Sum = " + sumAllUnmarkedNumbers(calledNumbersBoard, playedBingoBoard));
                return true;
            }
        }
/*        // Check diagonal win condition if applicable
        for(int i = 0; i < boardWidth; i++) {
            if(!calledNumbersBoard[i][i]) {
                break;
            } else if(i == boardWidth - 1) {
                return true;
            }
        }

        for(int i = boardWidth - 1; i >= 0; i--) {
            if(!calledNumbersBoard[i][boardWidth - 1 - i]) {
                break;
            } else if(i == 0) {
                return true;
            }
        } */
        return false;
    }


    static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class BingoPosition {
        final int value;
        boolean wasCalled;

        public BingoPosition(int value, boolean wasCalled) {
            this.value = value;
            this.wasCalled = wasCalled;
        }
    }

    static class BingoBoard {
        BingoPosition[][] bingoPositions;

        public BingoBoard(BingoPosition[][] bingoPositions) {
            this.bingoPositions = bingoPositions;
        }

        public boolean isBoardWinner() {
            for(int x = 0; x < bingoPositions.length; x++) {
                for(int y = 0; y < bingoPositions[x].length; y++) {

                }
            }
            return false;
        }

    }

}
