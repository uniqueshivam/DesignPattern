package DSAPractise;

public class TwoSumSortedArray {
    public int[] getTwoSumIndex(int[] arr, int target) {
        int pointer1 = 0;
        int pointer2 = arr.length-1;
        int[] ans = new int[2];

        while(pointer1<pointer2) {
            int sum = arr[pointer1]+arr[pointer2];
            if(sum>target) {
                pointer2--;
            } else if(sum<target){
                pointer1++;
            } else {
                ans[0] = pointer1+1;
                ans[1] = pointer2+1;
                break;
            }
        }
        return ans;
    }
}
