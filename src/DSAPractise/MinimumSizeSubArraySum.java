package DSAPractise;

public class MinimumSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int pointer1 = 0;
        int pointer2 = 1;
        int sum = nums[pointer1];
        int ans = Integer.MAX_VALUE;

        while(pointer2<nums.length) {
            while (sum>=target) {
                ans = Math.min((pointer2-pointer1)+1,ans);
                sum = sum-nums[pointer1];
                pointer1++;
            }
            sum = sum +nums[pointer2];
            pointer2++;
        }

        while (sum>=target) {
            ans = Math.min((pointer2-pointer1),ans);
            sum = sum-nums[pointer1];
            pointer1++;
        }

        if(ans == Integer.MAX_VALUE) {
            return 0;
        }
        return ans;
    }
}
