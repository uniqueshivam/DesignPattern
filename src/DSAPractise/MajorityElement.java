package DSAPractise;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        //this is moores votinh algorithm
        int candidate = nums[0];
        int count = 1;

        for(int i = 1;i<nums.length;i++) {
            if(count == 0) {
                candidate = nums[i];
                count= 1;
            } else  {
                if(nums[i] == candidate) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        if(count>nums.length/2) {
            return candidate;
        }
        return -1;
    }
}
