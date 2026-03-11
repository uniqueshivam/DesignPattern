package DSAPractise;

import java.util.*;

public class FindKPairsWithSmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> pairPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int value : nums1) {
            for (int i : nums2) {
                Pair pair = new Pair(value, i);
                pairPriorityQueue.offer(pair);
                if (pairPriorityQueue.size() > k) {
                    pairPriorityQueue.poll();
                }
            }
        }

        Stack<Pair> stack = new Stack<>();
        List<List<Integer>> ans = new ArrayList<>();
        while (!pairPriorityQueue.isEmpty()) {
            Pair pair = pairPriorityQueue.poll();
            stack.add(pair);
        }

        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            List<Integer> list = Arrays.asList(p.a,p.b);
            ans.add(list);
        }
        return  ans;
    }

    static class Pair implements Comparable<Pair> {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            return (this.a+ this.b)-(o.a+o.b);
        }
    }
}
