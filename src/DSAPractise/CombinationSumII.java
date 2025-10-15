package DSAPractise;

import java.util.*;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> combinations  = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates,0,target,combinations,ans);
        return ans;
    }

    private void backtrack(int[] nums, int start, int remain, List<Integer> path, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            if (nums[i] > remain) break; // no need to continue
            path.add(nums[i]);
            backtrack(nums, i + 1, remain - nums[i], path, result); // move to next index
            path.remove(path.size() - 1); // backtrack
        }
    }
}
