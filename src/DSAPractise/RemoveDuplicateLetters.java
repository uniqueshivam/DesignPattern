package DSAPractise;

import java.util.*;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character,Integer> map = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();

        for(int i = 0;i<s.length();i++) {
            if(map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            } else {
                map.put(s.charAt(i),1);
            }
        }

        for(int i = 0;i<s.length();i++) {
            map.put(s.charAt(i),map.get(s.charAt(i))-1);
            if(stack.isEmpty()) {
                stack.add(s.charAt(i));
                set.add(s.charAt(i));
            } else {
                if(set.contains(s.charAt(i))) {
                    continue;
                }
                while(!stack.isEmpty() && (s.charAt(i)<stack.peek() && map.get(stack.peek())>=1)) {
                    char pop = stack.pop();
                    set.remove(pop);
                }

                stack.add(s.charAt(i));
                set.add(s.charAt(i));
            }

        }

        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()){
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}
