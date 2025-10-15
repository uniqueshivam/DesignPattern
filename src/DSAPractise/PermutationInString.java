package DSAPractise;

import java.util.*;

public class PermutationInString {
    public boolean permutationExist(String s1, String s2) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

       for(int i = 0;i<s1.length();i++) {
           arr1[s1.charAt(i)-'a']++;
       }
       int pointer1  = 0;
       int pointer2 = 0;

       while(pointer2<s2.length()) {
           if(arr1[s2.charAt(pointer2)-'a'] == 0) {
               pointer1 = pointer2+1;
               pointer2++;
               Arrays.fill(arr2, 0);
           } else {
               int count = arr2[s2.charAt(pointer2)-'a'];
               if(count+1>arr1[s2.charAt(pointer2)-'a']) {
                   arr2[s2.charAt(pointer1)-'a']--;
                   pointer1++;
                   continue;
               }
               arr2[s2.charAt(pointer2)-'a']++;
               if((pointer2-pointer1) == s1.length()-1){
                   return true;
               }
               pointer2++;
           }
       }
       return false;
    }
}
