package DSAPractise;

public class SearchInRotatedSortedArray {
    public int getResult(int[] arr, int target) {
        int deflection =  getPoint(arr,0,arr.length-1);
        if(deflection == 0){
            return binarySearch(arr,0,arr.length-1,target);
        } else {
            if(target<=arr[deflection-1] && target>=arr[0]){
                return binarySearch(arr,0,deflection-1,target);
            } else {
                return binarySearch(arr,deflection,arr.length-1,target);
            }
        }
    }

    public int getPoint(int[] arr, int start, int end) {
        if (start <=end && end < arr.length && start >= 0) {
            int mid = start + (end - start) / 2;
            if(mid == end && mid>0 && arr[mid] < arr[mid - 1]) {
                return mid;
            }
            if(mid>0 && mid<end && arr[mid]<arr[mid-1] && arr[mid]<arr[mid+1]){
                return mid;
            }
            if(arr[mid]>=arr[start]){
                if(start>0 && arr[start]<arr[start-1]){
                    return start;
                }
                return getPoint(arr,mid+1,end);
            } else return getPoint(arr,start,mid-1);
        }
        return 0;
    }

    public int binarySearch(int[] arr, int start, int end, int target) {
        if(start<=end) {
            int mid = start+(end-start)/2;
            if(arr[mid] == target){
                return mid;
            }

            if(target<arr[mid]){
                return binarySearch(arr,start,mid-1,target);
            } else {
                return binarySearch(arr,mid+1,end,target);
            }
        }
        return -1;
    }
}
