package DSAPractise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        int[] arr = new int[26];
        for(int i = 0;i<p.length();i++) {
            arr[p.charAt(i)-'a']++;
         }

        int left = 0;
        int right = 0;
        List<Integer> ans = new ArrayList<>();
        int[] dup = Arrays.copyOf(arr,arr.length);
        while(right<s.length()) {
            if(dup[s.charAt(right)-'a']!=0) {
                if((right-left) == p.length()-1){
                    ans.add(left);
                    dup[s.charAt(left)-'a']++;
                    left++;
                }
                dup[s.charAt(right)-'a']--;
            } else {
               if(arr[s.charAt(right)-'a']!=0) {
                   dup[s.charAt(left)-'a']++;
                   left++;
                   right--;
               } else {
                   left = right+1;
                   dup = Arrays.copyOf(arr,arr.length);
               }
            }

            right++;
        }
        return ans;
    }

//    public boolean checkIfAnagram(int[] arr) {
//        for (int j : arr) {
//            return j == 0;
//        }
//    }

}
