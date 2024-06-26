package hrank.practice.java.code;

/*
There is a string input_str consisting of characters 0 and 1 only and an integer k.
Find a substring of string input_str such that the number of '1's is equal to k, it is the smallest length, it in lexicographically smallest
*/

public class KthSmallestSubstring {

	public static String smallestSubstringWithKOnes(String input_str, int k) {
        int left = 0, count = 0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;

        for (int right = 0; right < input_str.length(); right++) {
            if (input_str.charAt(right) == '1') {
                count++;
            }
            
            while (count == k) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }
                
                if (input_str.charAt(left) == '1') {
                    count--;
                }
                left++;
            }
        }

        if (startIndex == -1) {
            return "No such substring exists.";
        } else {
            return input_str.substring(startIndex, startIndex + minLength);
        }
    }

    public static void main(String[] args) {
        String input_str = "100101110";
        int k = 3;
        String result = smallestSubstringWithKOnes(input_str, k);
        System.out.println("Smallest substring with " + k + " 1's: " + result);
    }

}
