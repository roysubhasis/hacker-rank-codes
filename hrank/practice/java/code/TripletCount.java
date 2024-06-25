package hrank.practice.java.code;

import java.util.HashMap;
import java.util.Map;

public class TripletCount {

    static int getTripletCount(int[] arr, int d) {
        int n = arr.length;
        int count = 0;

        // Store remainders and their frequencies
        Map<Integer, Integer> remainderFreq = new HashMap<>();

        // Iterate through array to find remainders
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = arr[i] + arr[j] + arr[k];
                    if (sum % d == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] a = {3, 3, 4, 7, 8};
        int d = 5;
        System.out.println(getTripletCount(a, d)); // Output: 3
    }
}