package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class MaxSumOfDistinctSubArray {
    public long maximumSubarraySum(int[] nums, int k) {
        int start = 0;
        int end = 0;
        long ans = 0;
        long sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        while(end<nums.length) {
            if(map.containsKey(nums[end]) && map.get(nums[end])!=-1)  {
                int index = map.get(nums[end]);
                while(start<=index) {
                    map.put(nums[start],-1);
                    sum = sum-nums[start];
                    start++;
                }
            }
            map.put(nums[end],end);
            sum = sum+nums[end];

            if((end-start)+1 == k) {
                ans = Math.max(sum,ans);
                sum = sum-nums[start];
                map.put(nums[start],-1);
                start++;
            }

            end++;
        }

        return ans;
    }
}
