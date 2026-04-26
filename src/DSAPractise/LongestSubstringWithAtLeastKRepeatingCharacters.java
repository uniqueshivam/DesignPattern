package DSAPractise;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        return fun(s,0,s.length()-1,k);



//        int ans = 0;
//        for(int i = 0;i<s.length();i++) {
//            int[] arr = new int[26];
//            for(int j = i;j<s.length();j++) {
//                arr[s.charAt(j)-'a']++;
//                if(isValid(arr,k)) {
//                    ans = Math.max(ans,j-i+1);
//                }
//            }
//        }
//        return ans;
    }

    private boolean isValid(int[] arr, int k) {
        for (int j : arr) {
            if (j != 0 && j < k) {
                return false;
            }
        }
        return true;
    }

    private int fun(String s, int left, int right, int k) {
        if((right-left)+1<k) {
            return 0;
        }
        int[] arr = new int[26];
        for(int i = left;i<=right;i++) {
            arr[s.charAt(i)-'a']++;
        }

        for(int i = left;i<=right;i++) {
            if(arr[s.charAt(i)-'a'] <k) {
                int leftValue = fun(s,left,i-1,k);
                int rightValue = fun(s,i+1,right,k);
                return Math.max(leftValue,rightValue);
            }
        }
        return right-left+1;
    }
}
