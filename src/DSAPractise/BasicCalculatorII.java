package DSAPractise;

import java.util.Stack;

public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+';
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                currentNumber = currentNumber*10 +(c-'0');
            }

            if((!Character.isDigit(c) &&  !Character.isSpaceChar(c)) || i == s.length()-1){
                if(operation == '+') {
                    stack.add(currentNumber);
                } else if(operation =='-') {
                    stack.add(-1*currentNumber);
                } else if(operation == '*') {
                    stack.add(stack.pop() * currentNumber);
                } else if(operation =='/') {
                    stack.add(stack.pop()/currentNumber);
                }

                operation = c;
                currentNumber = 0;
            }
        }

        int ans = 0;

        while(!stack.isEmpty()) {
            ans = ans+stack.pop();
        }
        return ans;
    }
}
