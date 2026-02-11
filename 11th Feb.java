import java.util.Arrays;

class Solution {

    // Function to calculate total cost for making all heights equal to target
    private long costOfOperation(int[] heights, int[] cost, int target) {
        long totalCost = 0;
        for (int i = 0; i < heights.length; i++) {
            totalCost += (long) Math.abs(heights[i] - target) * cost[i];
        }
        return totalCost;
    }

    public long minCost(int[] heights, int[] cost) {

        int low = Arrays.stream(heights).min().getAsInt();
        int high = Arrays.stream(heights).max().getAsInt();

        long minimumCost = Long.MAX_VALUE;

        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;

            long cost1 = costOfOperation(heights, cost, mid1);
            long cost2 = costOfOperation(heights, cost, mid2);

            minimumCost = Math.min(minimumCost, Math.min(cost1, cost2));

            if (cost1 < cost2) {
                high = mid2 - 1;
            } else {
                low = mid1 + 1;
            }
        }

        return minimumCost;
    }
}
