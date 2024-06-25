package hrank.practice.java.code;

//Algo used - Brute Force approach (Less efficient due to high range of possible looping for each element. May give timeout.)
    /*
        1. Define a function calculateArrayCost to calculate the total cost of an array.
        2. Define a minCost by using MAX_VALUE of long.
        3. Iterate through each position in the array in getMinimumCost method where a new element can be inserted.
        4. For each position, try inserting every possible new element (from 1 to 100,000 as per constraints).
        5. Calculate the cost of the new array formed after insertion. (Call calculateCost inside getMinimumCost)
        6. Update minCost value with the newly cost value obtained in Step 5.
    * */

    public class MinArrCostBruteForceApproach {
        // Function to calculate the cost of an array
        public static long calculateArrayCost(int[] array) {
            long cost = 0;
            for (int i = 1; i < array.length; i++) {
                cost += (long) Math.pow((array[i] - array[i - 1]) ,2);
            }
            /*List<Integer> aList = new ArrayList<>();
            for (int element : array) {
                aList.add(element);
            }
            System.out.println("Array is " + aList + " and cost of array is -> " + cost);*/
            return cost;
        }
        public static long getMinimumCost(int[] arr) {
            int n = arr.length;
            long minCost = Long.MAX_VALUE;
            // Try inserting element at every possible position
            for (int i = 0; i <= n; i++) {
                // looping till 1L for each position as it's a brute force approach.
                for (int newElement = 1; newElement <= 100000; newElement++) {
                    int[] newArray = new int[n + 1];
                    for (int j = 0, k = 0; j < newArray.length; j++) {
                        if (j == i) {
                            newArray[j] = newElement;
                        } else {
                            newArray[j] = arr[k++];
                        }
                    }
                    long currentCost = calculateArrayCost(newArray);
                    minCost = Math.min(minCost, currentCost);
                }
            }
            return minCost;
        }

        public static void main(String[] args) {
            int[] arr = {1, 3, 5, 2, 10};
            System.out.println(getMinimumCost(arr)); // Expected Output: 49
        }
    }