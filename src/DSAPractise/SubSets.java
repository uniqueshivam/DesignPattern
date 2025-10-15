package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class SubSets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        ans.add(list);
        fun(nums,0,list,ans);
        return ans;
    }

    public void fun(int[] arr, int counter, List<Integer> list, List<List<Integer>> ans) {
        for(int i = counter;i<arr.length;i++) {
            list.add(arr[i]);
            ans.add(new ArrayList<>(list));
            fun(arr,i+1,list,ans);
            list.removeLast();
        }
    }
}
