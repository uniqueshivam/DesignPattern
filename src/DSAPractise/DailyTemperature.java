package DSAPractise;


import java.util.ArrayDeque;
import java.util.Stack;

public class DailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for(int i = temperatures.length-1;i>=0;i--) {
            while(!stack.isEmpty() && temperatures[stack.peek()]<=temperatures[i]){
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek()-i;

            stack.push(i);
        }
        return ans;
    }
}
//
//class Data {
//    int val;
//    int index;
//
//    public Data (int val, int index){
//        this.val = val;
//        this.index = index;
//    }
//
//    public int getVal(){
//        return this.val;
//    }
//
//    public int getIndex(){
//        return this.index;
//    }
//}
