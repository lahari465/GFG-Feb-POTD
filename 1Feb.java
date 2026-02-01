Q) K Sized Subarray Maximum

Given an array arr[] of positive integers and an integer k. You have to find the maximum value for each contiguous subarray of size k. Return an array of maximum values corresponding to each contiguous subarray.

Examples:

Input: arr[] = [1, 2, 3, 1, 4, 5, 2, 3, 6], k = 3
Output: [3, 3, 4, 5, 5, 5, 6]
Explanation: 
1st contiguous subarray [1, 2, 3], max = 3
2nd contiguous subarray [2, 3, 1], max = 3
3rd contiguous subarray [3, 1, 4], max = 4
4th contiguous subarray [1, 4, 5], max = 5
5th contiguous subarray [4, 5, 2], max = 5
6th contiguous subarray [5, 2, 3], max = 5
7th contiguous subarray [2, 3, 6], max = 6

Input: arr[] = [5, 1, 3, 4, 2], k = 1
Output: [5, 1, 3, 4, 2]
Explanation: When k = 1, each element in the array is its own subarray, so the output is simply the same array

Constraints:
1 ≤ arr.size() ≤ 106
1 ≤ k ≤ arr.size()
0 ≤ arr[i] ≤ 109

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

SOLUTION: 

class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
        ArrayList<Integer> result = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {

            // Remove indices out of current window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove elements smaller than current element
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.pollLast();
            }

            // Add current index
            dq.offerLast(i);

            // Record max for completed window
            if (i >= k - 1) {
                result.add(arr[dq.peekFirst()]);
            }
        }

        return result;
    }
}


-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
                int n = arr.length;

        // to store the results
        ArrayList<Integer> res = new ArrayList<Integer>();

        // to store the max value
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return b.first - a.first;
            }
        });

        // Initialize the heap with the first k elements
        for (int i = 0; i < k; i++)
            heap.add(new Pair(arr[i], i));

        // The maximum element in the first window
        res.add(heap.peek().first);

        // Process the remaining elements
        for (int i = k; i < arr.length; i++) {

            // Add the current element to the heap
            heap.add(new Pair(arr[i], i));

            // Remove elements that are outside the current
            // window
            while (heap.peek().second <= i - k)
                heap.poll();

            // The maximum element in the current window
            res.add(heap.peek().first);
        }

        return res;
    }

    static class Pair {
        int first;
        int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
