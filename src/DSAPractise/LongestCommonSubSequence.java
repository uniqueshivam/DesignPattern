package DSAPractise;

import java.util.Arrays;

public class LongestCommonSubSequence {
    public int getLongestSubSequenceLength(String s1, String s2) {

        //This solution is for dp tabulation way
        //From recursion to dp to tabulation only 3 thing is important
//        1) Fill the tabulation for the base case (Copy the base case)
        //2) Write down the changing parameters in opposite fashion (i & j here)
        //3) Copy the recursion
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        for(int j = 0;j<=s2.length();j++) {
            dp[0][j] = 0;
        }

        for(int i = 0;i<=s1.length();i++) {
            dp[i][0] = 0;
        }

        for(int i =1;i<=s1.length();i++) {
            for(int j = 1;j<=s2.length();j++) {
               if(s1.charAt(i-1) == s2.charAt(j-1))  {
                   dp[i][j] = 1+ dp[i-1][j-1];
               } else {
                   dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
               }
            }
        }
        return dp[s1.length()][s2.length()];

//        int[][] dp = new int[s1.length()][s2.length()];
//        for (int[] ints : dp) {
//            Arrays.fill(ints, -1);
//        }
//        return recursion(s1.length()-1,s1,s2.length()-1,s2);
//        return dpSolution(s1.length()-1,s1,s2.length()-1,s2,dp);
    }

//    public int recursion(int index1, String s1, int index2, String s2) {
//        if(index1<0 || index2<0) {
//            return 0;
//        }
//
//        if(s1.charAt(index1) == s2.charAt(index2)) {
//            return 1+ recursion(index1-1,s1,index2-1,s2);
//        }
//
//        return Math.max(recursion(index1-1,s1,index2,s2),recursion(index1,s1,index2-1,s2));
//    }

//    public int dpSolution(int index1, String s1, int index2, String s2, int[][] dp) {
//        if(index1<0 || index2<0) {
//            return 0;
//        }
//
//        if(dp[index1][index2]!=-1) {
//            return dp[index1][index2];
//        }
//
//        if(s1.charAt(index1) == s2.charAt(index2))  {
//            return dp[index1][index2] = 1+dpSolution(index1-1,s1,index2-1,s2,dp);
//        }
//        return dp[index1][index2] = Math.max(dpSolution(index1-1,s1,index2,s2,dp),dpSolution(index1,s1,index2-1,s2,dp));
//    }
}
