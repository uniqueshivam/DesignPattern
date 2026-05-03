package DSAPractise;

import java.util.Stack;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        stack.add(temperatures.length-1);
        for(int i = temperatures.length-2;i>=0;i--) {
            while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()]) {
                stack.pop();
            }

            ans[i] = stack.isEmpty()? 0 :stack.peek()-i;
            stack.add(i);
        }

        return ans;
    }

}
