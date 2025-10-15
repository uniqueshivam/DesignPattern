package DSAPractise;

import java.util.Arrays;
import java.util.Stack;

public class ValidParantheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c =='(' || c=='{' || c =='[') {
                stack.push(c);
            } else  {
                switch (c) {
                    case ')': {
                        if(!stack.isEmpty() && stack.peek() == '('){
                            stack.pop();
                        }  else {
                           return false;
                        }
                        break;
                    }
                    case '}':{
                        if(!stack.isEmpty() && stack.peek() == '{'){
                            stack.pop();
                        } else {
                            return false;
                        }
                        break;
                    }

                    case ']' : {
                        if(!stack.isEmpty() && stack.peek() == '['){
                            stack.pop();
                        } else {
                           return false;
                        }
                        break;
                    }
                }
            }

        }
        return stack.isEmpty();
    }
}
