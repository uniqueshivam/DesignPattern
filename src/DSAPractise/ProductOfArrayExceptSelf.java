package DSAPractise;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int prev = 1;
        int[] suffix = new int[n];
        int after = 1;
        for(int i = n-1;i>=0;i--) {
            suffix[i] = after*nums[i];
            after = suffix[i];
        }

        for(int i = 0;i<n-1;i++) {
            suffix[i] = prev*suffix[i+1];
            prev = prev*nums[i];
        }
        suffix[n-1] = prev;

        for(int i = 0;i<n;i++) {;
            System.out.print(suffix[i]+" ");
        }
        return nums;
    }
}
