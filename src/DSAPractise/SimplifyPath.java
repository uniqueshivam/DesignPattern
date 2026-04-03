package DSAPractise;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();

        for(String s : arr) {
            if(!Objects.equals(s,".") && !Objects.equals(s,"..") && !s.isEmpty()) {
               stack.push(s);
            }

            if(Objects.equals(s,"..") && !stack.isEmpty()) {
                stack.pop();
            }

        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }

        return result.toString();

    }
}
