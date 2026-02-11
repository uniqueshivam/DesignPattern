package DSAPractise;

import java.util.*;

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        int counter = 0;
        int pointer = 0;
        Map<Integer, List<Character>> map = new HashMap<>();
        boolean increment = true;
        while(pointer<s.length()) {
            if(increment) {
                counter++;
            } else  {
                counter--;
            }

            char c = s.charAt(pointer);
            if(map.containsKey(counter)) {
                List<Character> characterList =map.get(counter);
                characterList.add(c);
                map.put(counter,characterList);
            } else {
                List<Character> characterList = new ArrayList<>();
                characterList.add(c);
                map.put(counter,characterList);
            }

            if(counter == numRows) {
                increment = false;

            } else if(counter == 1) {
                increment = true;
            }
            pointer++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<=numRows;i++) {
            List<Character> list = map.get(i);
            if(!list.isEmpty()) {
                for(Character c : list) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

}
