package hrank.practice.java.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MinimizeArrayCost {

    //Algo used.
    /*
    Find the maximum difference:
       1. Find the pair of adjacent elements (first and second) with the maximum absolute difference.
       2. If the absolute difference between any two adjacent elements is greater than the current max_cost,
          then update max_cost to this new value, and firstValueIndex and secValueIndex are updated to the indices of these elements.
       3. Calculate middle point as the integer of the two elements with the maximum difference.
          In this case its done by averaging arr[firstValueIndex] and arr[secValueIndex].
       4. Calculate initial cost using,
            The cost is the sum of the squares of the differences between the midpoint and each of these elements:
       5. Calculate remaining cost, by doing the same repeat iteration for each remaining adjacent pars.
            Note: Skipping the pair where the new midpoint was already inserted.
            For each pair, it calculates the square of the difference between the elements
            cost = cost + {square of (first - second)}
    * */
    public static long getMinimumCostOfArray(ArrayList<Integer> inputArr) {
        long cost = 0, maxCost = 0;
        int firstValueIndex = 0, secValueIndex = 0;

        // Find max diff between neighbors
        for (int i = 0; i < inputArr.size() - 1; i++) {
            long first = inputArr.get(i);
            long second = inputArr.get(i + 1);
            if (Math.abs(second - first) > maxCost) {
                maxCost = Math.abs(second - first);
                firstValueIndex = i;
                secValueIndex = i + 1;
            }
        }

        // Find new number between the max diff
        int mid = (inputArr.get(firstValueIndex) + inputArr.get(secValueIndex)) / 2;
        cost = (long) (cost + Math.pow((inputArr.get(firstValueIndex) - mid),2));
        cost = (long) (cost + Math.pow((inputArr.get(secValueIndex) - mid),2));
        for (int j = 0; j < inputArr.size() - j; j++) {
            if (j == secValueIndex) {
                continue;
            }
            long first = inputArr.get(j);
            long second = inputArr.get(j + 1);
            cost = (long) (cost + Math.pow((first - second),2 ));
        }
        return cost;
    }

    public static void main(String[] args) {
        int[] numArr= {1, 3, 5, 2, 10};
        ArrayList<Integer> listOfLong = Arrays.stream(numArr)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        long minimumCost = getMinimumCostOfArray(listOfLong);
        System.out.println("Minimum Array Cost: " + minimumCost); // Expected Output: 49
    }
}
