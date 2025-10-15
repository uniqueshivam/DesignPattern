package DSAPractise;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSum {
    public int getMaxLength(int[] arr) {
        return optimizedSolution(arr);
//        Set<Integer> set = new HashSet<>();
//        Set<Integer> arrSet = new HashSet<>();
//        int ans = 0;
//        for (int j : arr) {
//            arrSet.add(j);
//        }

//        for (int j : arr) {
//            if(set.contains(j)){
//                continue;
//            }
//            int initial = j;
//            int count = 1;
//            while (count < arr.length) {
//                initial += 1;
//                if (arrSet.contains(initial)) {
////                    set.add(initial);
//                    count++;
//                } else {
//                    break;
//                }
//            }
//
//            ans = Math.max(count, ans);
//
//        }
//        return ans;
    }
    public int optimizedSolution(int[] arr) {
       int ans = 0;
       Set<Integer> set = new HashSet<>();
       for(Integer i : arr) {
           set.add(i);
       }

       for(Integer i : arr) {
           if(!set.contains(i-1)) {
               int count = 0;
               count++;
               while(true) {
                   if(set.contains(i+1)) {
                       count++;
                       i = i+1;
                       continue;
                   }
                   ans = Math.max(count,ans);
                   break;
               }
           }
       }
       return ans;
    }
}
