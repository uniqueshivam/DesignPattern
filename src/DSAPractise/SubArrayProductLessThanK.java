package DSAPractise;

public class SubArrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int product = 1;

        int ans = 0;
        while(right<nums.length) {
            product = product*nums[right];
            while(product>=k) {
                product = product/nums[left];
                left++;
            }

            ans = ans +(right-left+1);
            right++;
        }
        return ans;
    }
}
