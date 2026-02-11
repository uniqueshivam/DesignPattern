package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class FirstIndexOfFirstOccurrence {
    public int strStr(String haystack, String needle) {
        for(int i = 0;i<haystack.length()-needle.length()+1;i++) {
            if(haystack.charAt(i) == needle.charAt(0)) {
                if(haystack.startsWith(needle, i)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
