package DSAPractise;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> triplets(int[] arr) {
        return optimizedSolution(arr);
//        Set<Integer> set = new HashSet<>();
//        Set<List<Integer>> ansSet = new HashSet<>();
//        List<List<Integer>> ans = new ArrayList<>();
//        int n = arr.length;
//        for(int i = 0;i<n;i++){
//            set.clear();
//            for(int j = i+1;j<n;j++){
//                int toCheck = -1*(arr[i]+arr[j]);
//                if(set.contains(toCheck)) {
//                    List<Integer> tempList = new ArrayList<>();
//                    tempList.add(arr[i]);
//                    tempList.add(arr[j]);
//                    tempList.add(toCheck);
//                    Collections.sort(tempList);
//                    if(!ansSet.contains(tempList)) {
//                        ans.add(tempList);
//                        ansSet.add(tempList);
//                    }
//                }
//                set.add(arr[j]);
//            }
//        }
//        return ans;
    }

    //space will be optimized because extra space is not being used
    public List<List<Integer>> optimizedSolution(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length-1;
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0;i<n;i++){
            if(i>0 && arr[i] == arr[i-1]){
                continue;
            }
            int j = i+1;
            int k = n;
            while(j<k){
                int num1 = arr[i];
                int num2 = arr[j];
                int num3 = arr[k];
                int sum = num1+num2+num3;
                if(sum == 0){
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(arr[i]);
                    tempList.add(arr[j]);
                    tempList.add(arr[k]);
                    j++;
                    k--;
                    ans.add(tempList);
                    while(arr[j] == num2 && j<k){
                        j++;
                    }

                    while(arr[j] == num3 && k>j){
                        k--;
                    }

                } else if(sum<0){
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }
}
