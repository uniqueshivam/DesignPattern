package DSAPractise;

public class JumpGameII {
    public int jump(int[] nums) {
        int target = nums.length-1;
        int ans = 0;
        while(target!=0) {
            for(int i = 0;i<target;i++) {
                if(nums[i]+i>=target) {
                    target = i;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
