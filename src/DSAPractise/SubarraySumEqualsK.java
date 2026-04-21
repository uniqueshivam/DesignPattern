package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        return subarraySumOptimised(nums,k);
//       int[] preFixsum = new int[nums.length];
//       preFixsum[0] = nums[0];
//       for(int i = 01;i<nums.length;i++) {
//           preFixsum[i] = preFixsum[i-1]+nums[i];
//       }
//
//
//       int ans = 0;
//       for(int i = 0;i<nums.length;i++) {
//           if(preFixsum[i] == k) {
//               ans++;
//           }
//           for(int j = i+1;j<nums.length;j++) {
//               if(preFixsum[j]-preFixsum[i] == k) {
//                   ans++;
//               }
//           }
//       }
//       return ans;
    }


    public int subarraySumOptimised(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int ans = 0;
        int sum = 0;

        for (int num : nums) {
            sum = sum + num;
            if (map.containsKey(sum - k)) {
                ans = ans + map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return ans;
    }
}
