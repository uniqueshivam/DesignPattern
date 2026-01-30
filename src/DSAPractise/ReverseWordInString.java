package DSAPractise;

import java.util.Objects;

public class ReverseWordInString {
    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length-1;i>=0;i--) {
            if(!Objects.equals(arr[i], "")) {
                sb.append(arr[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
