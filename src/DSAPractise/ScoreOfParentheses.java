package DSAPractise;

import java.util.Stack;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<s.length();i++){
            if(!stack.isEmpty() && s.charAt(i) == ')') {
                int num = 0;
                while(stack.peek()>=0) {
                    num = num +stack.pop();
                }
                stack.pop();
                if(num == 0) {
                    stack.add(1);
                } else {
                    num = num*2;
                    stack.add(num);
                }
                continue;
            }
            stack.add(-1);
        }
        int ans = 0;
        while(!stack.isEmpty()) {
            ans = ans+ stack.pop();
        }
       return ans;
    }
}
