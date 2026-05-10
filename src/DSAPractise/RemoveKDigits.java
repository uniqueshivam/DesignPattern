package DSAPractise;

import java.util.Stack;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack  = new Stack<>();
        StringBuilder sb = new StringBuilder();
        stack.add(num.charAt(0));
        for(int i = 1;i<num.length();i++) {
            int last = stack.peek()-'0';
            int current = num.charAt(i)-'0';

            if(current<last && k>0) {
                stack.pop();
                k--;
            }

            if(last == 0 && stack.size() == 1) {
                stack.pop();
            }
            stack.add(num.charAt(i));
        }

        while(!stack.isEmpty() && k>0) {
            stack.pop();
            k--;
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return !sb.isEmpty() ? sb.reverse().toString():"0";
    }
}
