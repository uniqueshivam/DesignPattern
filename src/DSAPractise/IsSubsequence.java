package DSAPractise;

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int pointer1 = 0;
        int pointer2 = 0;

        while(pointer1<t.length() && pointer2<s.length()) {
            if(t.charAt(pointer1) == s.charAt(pointer2)) {
                pointer2++;
            }

            pointer1++;
        }

        return pointer2 == s.length();
    }
}
