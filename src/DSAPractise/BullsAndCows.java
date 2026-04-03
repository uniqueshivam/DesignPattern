package DSAPractise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BullsAndCows {
    public String getHint(String secret, String guess) {
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<secret.length();i++) {
           if(map.containsKey(secret.charAt(i))) {
               map.put(secret.charAt(i),map.get(secret.charAt(i))+1);
           } else {
               map.put(secret.charAt(i),1);
           }
        }

        int cows = 0;
        int bulls = 0;

        for(int i = 0;i<guess.length();i++) {
            if(map.containsKey(guess.charAt(i)) && map.get(guess.charAt(i))>0) {
                cows++;
                map.put(guess.charAt(i),map.get(guess.charAt(i))-1);
            }

            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
        }

        cows = cows-bulls;
        return bulls+"A"+cows+"B";

    }
}
