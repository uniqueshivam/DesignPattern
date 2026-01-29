package DSAPractise;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
       String ans = "";
        if(strs.length == 1) {
            return strs[0];
        }

        ans = getCount(strs[0],strs[1]);
        for(int i = 2;i<strs.length;i++) {
            String temp = getCount(strs[i-1],strs[i]);
            if(temp.length()<ans.length()) {
                ans = temp;
            }
        }
        return ans;

    }

    private static String getCount(String s1, String s2) {
        int pointer1 = 0;
        int pointer2 = 0;
        int count = 0;
        StringBuilder sb  = new StringBuilder();
        while(pointer1<s1.length() && pointer2<s2.length()) {
            if(s1.charAt(pointer1) == s2.charAt(pointer2)) {
                count++;
                sb.append(s1.charAt(pointer1));
            } else  {
                break;
            }
            pointer2++;
            pointer1++;
        }
        return  sb.toString();
    }
}
