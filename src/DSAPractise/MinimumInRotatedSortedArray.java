package DSAPractise;

public class MinimumInRotatedSortedArray {
    public int getMinimum(int[] arr ) {
        int min = Integer.MAX_VALUE;
        return search(arr,0,arr.length-1,min);

    }

    public int search(int[] arr, int start, int end, int minValue) {
        if(start<=end && end<arr.length && start>=0){
            int mid = start+(end-start)/2;
            if(arr[start]<=arr[mid]) {
                minValue = Math.min(minValue,arr[start]);
                return search(arr,mid+1,end,minValue);
            } else{
                minValue = Math.min(minValue,arr[mid]);
                return search(arr,start,mid-1,minValue);
            }


        }
        return minValue;
    }
}
