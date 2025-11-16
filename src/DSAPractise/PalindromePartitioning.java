package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<String> comb = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        fun(s,0,0,comb,ans);
        return ans;
    }



    public void fun(String s, int start, int end, List<String> comb, List<List<String>> ans) {
        if(end == s.length()) {
            return;
        }

        String x = s.substring(start,end);
        if(isPalindrome(x)) {
            if(end == s.length()-1) {
                comb.add(x);
            }
            fun(s,start,end+1,comb,ans);
        }

        comb.add(s.substring(start,end));
        fun(s,end,end+1,comb,ans);
        ans.add(new ArrayList<>(comb));

    }
    private boolean isPalindrome(String s) {
        if(s.length() == 1) {
            return true;
        }

        int start = 0;
        int end = s.length()-1;
        while(start<=end) {
            if(s.charAt(start)!=s.charAt(end)) {
                return false;
            }

            start++;
            end--;

        }
        return true;
    }
}
