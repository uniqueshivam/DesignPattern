package DSAPractise;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int leftSum = 0;
        int totalSum = 0;

        for(Integer i :nums) {
            totalSum+=i;
        }

        for(int i = 0;i<nums.length;i++) {
            int rightSum = totalSum-(leftSum+nums[i]);
            if(rightSum == leftSum) {
                return i;
            }

            leftSum = leftSum+nums[i];
        }
        return -1;
    }
}
