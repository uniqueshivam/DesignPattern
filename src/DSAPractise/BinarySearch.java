package DSAPractise;

public class BinarySearch {
    public int search(int[] nums, int target) {
        return bs(nums,0,nums.length-1,target);
    }

    public int bs(int[] arr, int start, int end,int target) {
        if(start<=end) {
            int mid = (end-start)/2+start;
            if(arr[mid] == target) {
                return mid;
            }
            else if(arr[mid]<target) {
                return bs(arr,mid+1,end,target);
            } else  {
                return bs(arr,start,mid-1,target);
            }
        }
        return -1;
    }
}
