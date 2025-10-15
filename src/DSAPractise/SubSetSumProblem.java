package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class SubSetSumProblem {
    public boolean doSumExist(int[] input, int sum) {
        int n = input.length;
        boolean[][] dp = new boolean[n][sum+1];

        for(int i = 0;i<n;i++){
            dp[i][0] = true;
        }

        if (input[0] <= sum) {
            dp[0][input[0]] = true;
        }


        for(int i = 1;i<n;i++){
            for(int j = 1;j<=sum;j++) {
                boolean take = false;
                boolean notTake = false;
                if(j>=input[i]) {
                    take = dp[i-1][j-input[i]];
                }
                notTake = dp[i-1][j];
                dp[i][j] = take | notTake;
            }
        }
        return dp[n-1][sum];
    }
//    public boolean findSum(int endIndex, int target, int[] arr){
//        if(target == 0){
//            return true;
//        }
//
//        if(endIndex == 0 && target == arr[endIndex]){
//            return true;
//        } else if(endIndex == 0){
//            return false;
//        }
//
//        boolean take = false;
//        boolean notTake = false;
//        if(target>= arr[endIndex]){
//             take = findSum(endIndex-1, target-arr[endIndex],arr);
//        }
//
//        notTake  = findSum(endIndex-1, target,arr);
//        return take | notTake;
//    }
}
