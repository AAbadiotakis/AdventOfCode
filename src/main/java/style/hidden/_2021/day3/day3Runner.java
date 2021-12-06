package style.hidden._2021.day3;

import style.hidden.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class day3Runner {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileReaderUtil.parseFile("day3input.txt");
        int lineLength = lines.get(0).length();
        int lineSize = lines.size();
        String gammaRate = "";
        String epsilonRate = "";
        for(int i = 0; i < lineLength; i++) {
            int count0 = 0;
            int count1 = 0;
            for(int j = 0; j < lineSize; j++) {
                if(lines.get(j).charAt(i) == '0') {
                    count0++;
                } else {
                    count1++;
                }
            }
            if(count0 > count1) {
                gammaRate += "0";
                epsilonRate += "1";
            } else {
                gammaRate += "1";
                epsilonRate += "0";
            }
        }
        System.out.println("Part 1 Answer = " + (Integer.parseInt(gammaRate,2) * Integer.parseInt(epsilonRate, 2)));

        // ----- //
        String oxyGenPrefixStr = "";

        List<String> oxyGenRating = new ArrayList<>(lines);
        while(oxyGenRating.size() > 1) {
            for(int i = 0; i < lineLength; i++) {
                lineSize = oxyGenRating.size();
                if(lineSize == 1) {
                    break;
                }
                int count0 = 0;
                int count1 = 0;
                for (int j = 0; j < lineSize; j++) {
                    if (oxyGenRating.get(j).charAt(i) == '0') {
                        count0++;
                    } else {
                        count1++;
                    }
                }
                if (count0 > count1) {
                    oxyGenPrefixStr += "0";
                } else {
                    oxyGenPrefixStr += "1";
                }
                String oxyGenStartsWith = oxyGenPrefixStr;
                oxyGenRating = oxyGenRating.stream().filter(l -> l.startsWith(oxyGenStartsWith)).collect(Collectors.toList());
//                System.out.println("oxyGenRating = " + oxyGenRating);
            }
        }

        String co2PrefixStr = "";
        List<String> co2Rating = new ArrayList<>(lines);
        while(co2Rating.size() > 1) {
            for(int i = 0; i < lineLength; i++) {
                lineSize = co2Rating.size();
                if(lineSize == 1) {
                    break;
                }
                int count0 = 0;
                int count1 = 0;
                for (int j = 0; j < lineSize; j++) {
                    if (co2Rating.get(j).charAt(i) == '0') {
                        count0++;
                    } else {
                        count1++;
                    }
                }
                if (count0 > count1) {
                    co2PrefixStr += "1";
                } else {
                    co2PrefixStr += "0";
                }
                String co2StartsWith = co2PrefixStr;
                co2Rating = co2Rating.stream().filter(l -> l.startsWith(co2StartsWith)).collect(Collectors.toList());
//                System.out.println("co2Rating = " + co2Rating);
            }
        }
        System.out.println("Part 2 Answer = " + (Integer.parseInt(oxyGenRating.get(0),2) * Integer.parseInt(co2Rating.get(0), 2)));
    }
}
