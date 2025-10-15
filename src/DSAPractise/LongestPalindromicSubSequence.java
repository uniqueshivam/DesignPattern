package DSAPractise;

import java.util.Arrays;

public class LongestPalindromicSubSequence {
    public int getLongest(String s) {
        int[][] dp = new int[s.length()+1][s.length()+1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        for(int j = 0;j<=s.length();j++) {
            dp[s.length()][j] = 0;
        }

        for(int i = 0;i<=s.length();i++) {
            dp[i][0] = 0;
        }

        for(int i = s.length()-1;i>=0;i--) {
            for(int j = 1;j<=s.length();j++) {
                if(s.charAt(i) == s.charAt(j-1)) {
                    if(i ==(j-1)) {
                        dp[i][j] = 1+dp[i+1][j-1];
                    } else  {
                        dp[i][j] = 2+dp[i+1][j-1];
                    }

                } else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
//        return rec(s,0,s.length()-1,dp);
    }

    public int rec(String s, int start, int end,int[][]dp) {
        if(start>end) {
            return 0;
        }
        if(dp[start][end] != -1){
            return dp[start][end];
        }
        if(s.charAt(start) == s.charAt(end)) {
            if(start == end){
                return dp[start][end] =  1+rec(s,start+1,end-1,dp);
            }
            return dp[start][end] =  2+rec(s,start+1,end-1,dp);
        }
        return dp[start][end] = Math.max(rec(s,start,end-1,dp),rec(s,start+1,end,dp));
    }
}
