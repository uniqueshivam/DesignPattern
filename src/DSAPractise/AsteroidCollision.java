package DSAPractise;

import java.util.Stack;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(Integer i : asteroids) {
            boolean currentDestroyed = false;
            while(!stack.isEmpty() && stack.peek()>0 && i<0) {
                if(Math.abs(i)>stack.peek()) {
                    stack.pop();
                } else if(Math.abs(i) == stack.peek()) {
                    currentDestroyed = true;
                    stack.pop();
                    break;
                } else if(Math.abs(i)<stack.peek()) {
                    currentDestroyed = true;
                    break;
                }
            }
            if(!currentDestroyed) {
                stack.add(i);
            }

        }

        int[] ans = new int[stack.size()];
        for(int i = ans.length-1;i>=0;i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }
}
