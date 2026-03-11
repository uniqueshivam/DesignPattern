package DSAPractise;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(true) {
            int sum = 0;
            while(true) {
                int rem = n%10;
                sum = sum+rem*rem;
                if(n/10 == 0) {
                    break;
                }
                n = n/10;
            }
            if(sum == 1) {
                return true;
            }
            n = sum;

            if(set.contains(sum)) {
                return false;
            }
            set.add(sum);
        }
    }
}

//time complexit - o(1)
//space complexity - 0(1)
