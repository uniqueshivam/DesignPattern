package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBasket {
    public int totalFruit(int[] fruits) {
        int left = 0;
        int right = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        while(right<fruits.length) {
            map.put(fruits[right],map.getOrDefault(fruits[right],0)+1);

            while(map.size()>2) {
                map.put(fruits[left],map.get(fruits[left])-1);
                if(map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }

            ans = Math.max(ans,(right-left)+1);
            right++;
        }
        return ans;
    }
}
