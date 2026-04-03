package DSAPractise;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinConsecutiveProduct {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }
        return !queue.isEmpty() ? queue.peek() : -1;
    }


}
