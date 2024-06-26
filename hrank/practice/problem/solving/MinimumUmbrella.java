package hrank.practice.problem.solving;

import java.util.Arrays;

/*
 * Problem Statement:
 * Given a number of people N and an array of integers, each one representing the amount of people a type of umbrella can handle,
 * output the minimum number of umbrellas needed to handle N people.
 * No umbrella could have left spaces. Which means if an umbrella can handle 2 people, there should be 2 people under it.
 * If there's no solution, return -1.
 *
 * Examples:
 * solve(3, [1, 2]) means that we have 3 people and two kinds of umbrellas, one that handled one person and one that handles 2.
 * We can give one two-sized umbrella to 2 of them and the other to the last person.
 * Therefore, the solution is 2 (umbrellas). You could give 3 one-sized umbrellas, but we want the minimum number.
 *
 * solve(10, [3, 1]). You can give 3 three-sized umbrellas and 1 one-sized. This means the solution is 4.
 * solve(3, [2]). There's no solution since one umbrella would have empty space.Return -1.
 */

/*
 * Solution Algorithm:
 * -------------------
 * Initialization:
 *   Start by checking if umbrellas array is empty (umbrellas.length == 0).
 *   If so, return -1 since no umbrellas are available.
 *
 * Dynamic Programming Setup:
 *   Create a dp array where dp[i] represents the minimum number of umbrellas needed to shelter exactly i people.
 *   Initialize dp[0] = 0 (0 umbrellas needed for 0 people) and all other entries to Integer.MAX_VALUE to indicate initially unreachable states.
 *
 * DP Transition:
 *   Iterate through each number of people from 1 to N.
 *   For each i, iterate through all available umbrella capacities u in umbrellas.
 *   Update dp[i] using the formula: dp[i] = Math.min(dp[i], dp[i - u] + 1),
 *   ensuring that we take the minimum number of umbrellas either by not adding a new umbrella or by adding one more umbrella of capacity u.
 *
 * Result:
 *   After filling up the dp array, dp[N] gives the minimum number of umbrellas required to shelter exactly N people.
 *   If dp[N] is still Integer.MAX_VALUE, it means it's impossible to exactly match N people with the given umbrellas, so return -1.
 *
 * Edge Cases:
 *   Handle cases where umbrellas array is empty right at the beginning to avoid unnecessary computation.
 */

public class MinimumUmbrella {

    public static int findMinUmbrella(int noOfPeople, int[] umbrellas) {

        //Check if umbrellas array is empty (umbrellas.length == 0). If so, return -1 since no umbrellas are available.
        if (umbrellas.length == 0) {
            return -1;
        }

        // Initialize the DP array with Integer.MAX_VALUE
        int[] dp = new int[noOfPeople + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 0 umbrellas needed for 0 people

        // Iterate through each number of people from 1 to N
        for (int i = 1; i <= noOfPeople; i++) {
            for (int u : umbrellas) {
                if (i >= u) {
                    dp[i] = Math.min(dp[i], dp[i - u] + 1);
                }
            }
        }

        // Check if dp[N] is still Integer.MAX_VALUE, meaning no valid configuration found
        if (dp[noOfPeople] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[noOfPeople];
        }
    }

    public static void main(String[] args) {

        int[] umbrellas1 = {1, 2};
        System.out.println(findMinUmbrella(3, umbrellas1)); // Output: 2

        int[] umbrellas2 = {3, 1};
        System.out.println(findMinUmbrella(10, umbrellas2)); // Output: 4

        int[] umbrellas3 = {2};
        System.out.println(findMinUmbrella(3, umbrellas3)); // Output: -1
    }
}
