package style.hidden._2021.day10;

import style.hidden.utils.FileReaderUtil;

import java.util.List;

public class day10Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("_2021/day10input.txt");
        for(String line : lines) {
            int counterOpenParen = 0;
            boolean openParen = false;
            int counterCloseParen = 0;
            int counteropenBracket = 0;
            boolean openBracket = false;
            int counterCloseBracket = 0;
            int counterLessThan = 0;
            boolean lessThan = false;
            int counterGreaterThan = 0;

            for(int i = 0; i < line.length(); i++) {
                switch(String.valueOf(line.charAt(i))) {
                    case "(":
                        counterOpenParen++;
                        openParen = true;
                        break;
                    case ")":
                        if(!openParen) {
                            System.out.println("ERROR on CHAR " + line.charAt(i));
                        }
                        counterCloseParen++;
                        break;
                    case "[":
                        openBracket = true;
                        counteropenBracket++;
                        break;
                    case "]":
                        if(!openBracket) {
                            System.out.println("ERROR on CHAR " + line.charAt(i));
                        }
                        counterCloseBracket++;
                        break;
                    case "<":
                        lessThan = true;
                        counterLessThan++;
                        break;
                    case ">":
                        if(!lessThan) {
                            System.out.println("ERROR on CHAR " + line.charAt(i));
                        }
                        counterGreaterThan++;
                        break;
                }
            }
            System.out.println("openParen = " + counterOpenParen);
            System.out.println("closeParen = " + counterCloseParen);
            System.out.println("openBracket = " + counteropenBracket);
            System.out.println("closeBracket = " + counterCloseBracket);
            System.out.println("LessThan = " + counterLessThan);
            System.out.println("Greater Than = " + counterGreaterThan);
        }

    }

    boolean isChunkValid(String line) {
        System.out.println("line = " + line);
        return false;
    }
}
