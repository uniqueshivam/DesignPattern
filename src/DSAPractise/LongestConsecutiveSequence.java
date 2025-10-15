package DSAPractise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxCount = 0;
        for(Integer i : set) {
            if(!set.contains(i-1)) {
                int currentNum = i;
                int count = 1;
                while(set.contains(currentNum+1)) {
                    currentNum++;
                    count++;
                }
                maxCount  = Math.max(maxCount,count);
            }
        }
        return maxCount;
    }
}
