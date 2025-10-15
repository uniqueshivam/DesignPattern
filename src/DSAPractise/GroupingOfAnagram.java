package DSAPractise;

import java.sql.Array;
import java.util.*;

public class GroupingOfAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            String hash = getHash(s);

            if (map.containsKey(hash)) {
                List<String> list = map.get(hash);
                list.add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(hash, list);
            }
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }


    private String getHash(String s) {
        StringBuilder sb = new StringBuilder();
        int[] freq = new int[26];
        for(int i = 0;i<s.length();i++) {
            freq[s.charAt(i)-'a']++;
        }

        for(int i = 0;i<26;i++) {
            sb.append(freq[i]);
            sb.append("&");
        }
        return sb.toString();
    }
}
