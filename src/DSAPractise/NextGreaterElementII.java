package DSAPractise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[nums.length];
        Arrays.fill(arr,-1);
        for(int i = 0;i<2*nums.length;i++) {
            while(!stack.isEmpty() && nums[stack.peek()]<nums[i%nums.length]) {
                arr[stack.pop()] = nums[i];
            }

            if(i<nums.length) {
                stack.add(i)
;            }
        }
        return arr;
    }
}
