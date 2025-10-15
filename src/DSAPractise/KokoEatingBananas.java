package DSAPractise;

import java.util.Arrays;

public class KokoEatingBananas {
    public int getBananaCount(int[] piles, int h) {
       int max = Integer.MIN_VALUE;
       for(Integer i : piles){
           if(i>max){
               max = i;
           }
       }
       return bs(piles,1,max,h);
    }

    public int bs(int[] arr, int start, int end, int target) {
        if(start == end){return start;}
        int hours = 0;
        int mid = start + (end - start) / 2;
        for (int i = 0; i < arr.length; i++) {
            int q = arr[i] / mid;
            int rem = arr[i] % mid;
            hours = hours + q;
            if (rem != 0) {
                hours = hours + 1;
            }
        }
        if (hours > target) {
            return bs(arr, mid + 1, end, target);
        } else {
            return bs(arr, start, mid, target);
        }
    }
}
