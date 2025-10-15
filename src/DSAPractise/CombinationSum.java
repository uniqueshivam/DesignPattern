package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> getCombination(int[] arr, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        fun(arr,target,0,ans,combination);
        return ans;
    }

    private void fun(int[] arr,int sum, int counter, List<List<Integer>> ans, List<Integer> combinations) {
        if(sum == 0){
            ans.add(new ArrayList<>(combinations));
            return;
        }
        if(counter== arr.length){
            return;
        }

        if(arr[counter]<=sum) {
            combinations.add(arr[counter]);
            fun(arr,sum-arr[counter],counter,ans,combinations);
            combinations.remove(combinations.size()-1);
        }

        fun(arr,sum,counter+1,ans,combinations);
    }
}
