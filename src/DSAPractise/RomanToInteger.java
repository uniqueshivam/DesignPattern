package DSAPractise;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int lastValue = 0;
        int ans = 0;
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            int val = map.get(c);
            if(val>lastValue) {
                ans = ans-lastValue;
                ans = ans+(val-lastValue);
            } else  {
                ans = ans +val;
            }
            lastValue = val;
        }
        return ans;
    }
}
