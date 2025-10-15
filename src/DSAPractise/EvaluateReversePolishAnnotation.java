package DSAPractise;

import java.util.Objects;
import java.util.Stack;

public class EvaluateReversePolishAnnotation {
    public int evaluate(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String s : tokens) {
            switch (s) {
                case "/":
                    int first = stack.pop();
                    int second = stack.pop();
                    stack.add(second/first);
                    break;
                case "*":
                    stack.add(stack.pop()*stack.pop());
                    break;
                case "+":
                    stack.add(stack.pop()+stack.pop());
                    break;
                case "-":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.add(b-a);
                    break;
                default:
                    stack.add(Integer.valueOf(s));
            }
        }
        return !stack.isEmpty() ? stack.pop() : 0;
    }
}
