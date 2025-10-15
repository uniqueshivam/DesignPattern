package DSAPractise;

public class SumOfAllSubArray {
    public int getSum(int[] arr) {
        int sum = 0;
        for(int i = 0;i<arr.length;i++) {
            sum += arr[i]+(arr[i]*i)+(arr[i]*(arr.length-i-1)) +(arr.length-i-1)*(arr[i]*i);
            sum += (arr[i] * (i + 1) * (arr.length-i));
        }
        return sum;
    }
}