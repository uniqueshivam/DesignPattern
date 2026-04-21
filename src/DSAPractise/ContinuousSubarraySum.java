package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);

        int prefixSum = 0;
        for(int i = 0;i<nums.length;i++) {
            prefixSum = prefixSum+nums[i];
            int mod = (k==0)? prefixSum : prefixSum%k;

            if(map.containsKey(mod)) {
                if(i-map.get(mod)>=2) {
                    return true;
                }
            } else {
                map.put(mod,i);
            }
        }
        return false;

    }
}
