package DSAPractise;

import java.util.Stack;

public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int currentNumber = 0;

        StringBuilder currentStringBuilder = new StringBuilder();

        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                currentNumber = currentNumber*10+c-'0';
            }

            else if(c =='[') {
                stack.add(currentNumber);
                stringStack.add(currentStringBuilder);
                currentStringBuilder = new StringBuilder();
                currentNumber = 0;
            } else if(c ==']') {
                int number = stack.pop();
                StringBuilder prev = stringStack.pop();
                prev.append(currentStringBuilder.toString().repeat(Math.max(0, number)));

                currentStringBuilder = prev;
            } else {
                currentStringBuilder.append(c);
            }
        }

        return currentStringBuilder.toString();
    }
}
