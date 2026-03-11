package DSAPractise;

import java.util.*;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0;i<nums.length;i++) {
            if(map.containsKey(nums[i])) {
                Integer recent = map.get(nums[i]);
                if(Math.abs(i-recent)<=k) {
                    return true;
                }
                map.put(nums[i],i);
            } else {
                map.put(nums[i],i);
            }

        }
        return false;

    }
}
