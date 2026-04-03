package DSAPractise;

public class MinimumOperationToReduceToReduceInteger {
    public int minOperations(int n) {
        int logValue = (int) (Math.log(n)/Math.log(2));
        if(Math.pow(2,logValue) == n){
            return 1;
        }

        int low = n-(int) Math.pow(2,logValue);
        int high = (int) Math.pow(2,logValue+1)-n;

        return 1+Math.min(minOperations(low),minOperations(high));
    }

}
