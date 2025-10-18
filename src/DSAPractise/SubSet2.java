package DSAPractise;

import java.util.*;

public class SubSet2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> set = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        Arrays.sort(nums);
        fun(nums,0,set,ans);
        return ans;
    }

    public void fun(int[] arr, int counter, List<Integer> list,List<List<Integer>> ans) {
        for(int i = counter;i<arr.length;i++) {
            if (i > counter && arr[i] == arr[i - 1]) continue;
            list.add(arr[i]);
            ans.add(new ArrayList<>(list));
            fun(arr,i+1,list,ans);
            list.removeLast();
        }
    }
}
