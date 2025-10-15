package DSAPractise;

import java.util.Arrays;

public class CoinChangeProblem {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int i = 0;i<dp.length;i++) {
            Arrays.fill(dp[i],-1);
        }
        int ans = (int) rec(coins.length-1,amount,coins,dp);
        if(ans>=1e9){
            return -1;
        }
        return ans;
    }

    public double rec(int index, int amount,int[] coins,int[][]dp) {
        if(index == 0) {
            if(amount%coins[index] == 0) {
                return (double) amount /coins[index];
            }
            return 1e9;
        }
        if(dp[index][amount]!=-1){
            return dp[index][amount];
        }
        double noTake = 0+rec(index-1,amount,coins,dp);
        double take = Integer.MAX_VALUE;
        if(coins[index]<=amount) {
            take = 1+rec(index,amount-coins[index],coins,dp);
        }
        return dp[index][amount] = (int) Math.min(noTake,take);
    }

}
