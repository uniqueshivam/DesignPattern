package DSAPractise;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReached = 0;
        for(int i = 0;i<nums.length;i++) {
            int val = nums[i];
            if(i>maxReached) {
                return false;
            }
            if(i+val>maxReached) {
                maxReached = val+i;
            }
        }

        return maxReached >= nums.length - 1;
    }

//    public boolean fun(int index, int[] arr,boolean[] dp) {
//        if(index == arr.length-1) {
//            return true;
//        }
//
////        [2,3,1,1,4]
//        int val = arr[index];
//        int start  = 1;
//
//        while(start<=val) {
//            int temp = index+start;
//            if(dp[temp]) {
//                return true;
//            }
//            boolean returnValue = fun(temp,arr,dp);
//            if(returnValue) {
//                dp[temp] = true;
//                return true;
//            }
//            start++;
//        }
//        return false;
//    }
}
