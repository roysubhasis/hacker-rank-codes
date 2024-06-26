package hrank.practice.problem.solving;

import java.io.*;

public class CountingValleys {
    public static void main(String[] args) throws IOException {
        System.out.println(countingValleys(12, "DDUUDDUDUUUD"));
        System.out.println(countingValleys(1, "UUDDDDUUDUDUDDUU"));
        System.out.println(countingValleys(8, "UDDDUDUU"));
    }
    public static int countingValleys(int steps, String path) {
        int noOfValley = 0;
        int baseLevel = 0;

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'U') {
                baseLevel++;
            } else {
                baseLevel--;
            }
            if (baseLevel == 0 && path.charAt(i) == 'U') {
                //System.out.println("its a valley");
                noOfValley++;
            }
        }
        return noOfValley;
    }
}
