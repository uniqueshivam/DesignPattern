package DSAPractise;

public class LongestPalindromicSubstring {
    public String longestPalindromic(String s) {
        int maxLength = Integer.MIN_VALUE;
        String maxString = "";

        //first we will find the odd length palindrome
        for(int i = 0;i<s.length();i++) {
            int l = i;
            int r = i;
            while(l>=0 && r<s.length()) {
                if(s.charAt(l) == s.charAt(r)) {
                    if(r-l+1>maxLength) {
                        maxLength = r-l+1;
                        maxString = s.substring(l,r+1);
                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }

        //we will compute thge odd length palindrome
        for(int i = 0;i<s.length();i++) {
            int l = i;
            int r = i+1;

            while(l>=0 && r<s.length()) {
                if(s.charAt(l) == s.charAt(r)) {
                    if(r-l+1>maxLength) {
                        maxLength = r-l+1;
                        maxString = s.substring(l,r+1);
                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }

        }
        return maxString;
    }
}
