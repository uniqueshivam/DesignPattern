package DSAPractise;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConsecutiveOne {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ans = 0;

        Queue<Integer> zeros = new LinkedList<>();

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeros.offer(right);
            }

            // If more than k zeros, shrink
            if (zeros.size() > k) {
                left = zeros.poll() + 1;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;

        //below solution is without queue
        // int left = 0, zeros = 0, ans = 0;

        // for (int right = 0; right < nums.length; right++) {
        //     if (nums[right] == 0) zeros++;

        //     while (zeros > k) {
        //         if (nums[left] == 0) zeros--;
        //         left++;
        //     }

        //     ans = Math.max(ans, right - left + 1);
        // }

        // return ans;
    }
}
