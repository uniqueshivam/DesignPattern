package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingCharacter {
    public int getMaxLength(String s) {
        int n = s.length();
        Map<Character,Integer> map = new HashMap<>();
        int start = 0;
        int ans = !s.isEmpty() ? 1: 0;
        int counter = 0;

        while(counter<n) {
            if(!map.containsKey(s.charAt(counter))) {
                map.put(s.charAt(counter),counter);
                if(counter == n-1) {
                    ans  = Math.max((counter-start+1),ans);
                }
                counter++;
            } else {
                int index = map.get(s.charAt(counter));
                if(index<start) {
                    map.put(s.charAt(counter),counter);
                    if(counter == n-1) {
                        ans = Math.max((counter-start+1),ans);
                    }
                    counter++;
                    continue;
                }
                ans  = Math.max(counter-start,ans);
                start = index+1;
//                counter = index+1;
//                map.clear();
            }
        }
        return ans;
    }

}
