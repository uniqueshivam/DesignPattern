package DSAPractise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SimilarPairs {
    public int solve(int[] A, int B) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        int ans= 0;
        for(int i = 0;i<A.length;i++) {
            int key = A[i];
            if(map.containsKey(key)) {
                Queue<Integer> queueValue = map.get(key);
                int index = queueValue.poll();
                if(i-index<=B) {
                    ans++;
                }
                queueValue.add(i);
            } else {
                Queue<Integer> queueValue = new LinkedList<>();
                queueValue.add(i);
                map.put(key,queueValue);
            }
        }
        return (int) (ans%(Math.pow(10,9)+7));
    }
}
