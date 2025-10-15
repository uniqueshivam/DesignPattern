package DSAPractise;

public class LongestRepeatingCharacterReplacement {
    public int getMaxLength(String s, int k) {
        int[] arr = new int[26];
        int ans = 0;
        int start = 0;
        int end =0;

        while (start<=end && end < s.length()) {
            arr[s.charAt(end)-'A']++;
            int max = 0;
            char c='\0';
            for (int j = 0; j < 26; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    c = (char) ('A'+j);
                }
            }

            int length = end - start + 1;
            if (length - max <= k) {
                ans = Math.max(ans, length);
                end++;
            } else {
                arr[s.charAt(start)-'A']--;
                arr[s.charAt(end)-'A']--;
                start++;
            }
        }
        return ans;
    }

    public int optimized(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0;
        int start = 0;
        int ans = 0;

        for (int end = 0; end < s.length(); end++) {
            count[s.charAt(end) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }

            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }
}
