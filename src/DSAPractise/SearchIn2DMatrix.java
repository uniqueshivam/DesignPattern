package DSAPractise;

public class SearchIn2DMatrix {
    public boolean search(int[][] arr, int target) {
        for (int[] ints : arr) {
            if (target >= ints[0] && target <= ints[ints.length - 1]) {
                return bs(ints, 0, ints.length, target);
            }
        }
        return false;
    }

    public boolean bs(int[] arr, int start, int end, int target) {
        if(start<=end) {
            int mid = start+(end-start)/2;
            if(arr[mid] == target) {
                return true;
            }
            else if(arr[mid]<target) {
                return bs(arr,mid+1,end,target);
            } else  {
                return bs(arr,start,mid-1,target);
            }
        }
        return false;
    }
}
