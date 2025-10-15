package DSAPractise;

public class FindPeakElement {
    public int getPeakElement(int[] arr) {
        int start = -1;
        int end = 0;

        for(int i = 0;i<arr.length;i++) {
            end  = i+1;
            if(start == -1 && end == arr.length) {
               return i;
            }

            if(start == -1){
                if(arr[i]>arr[end]){
                    return i;
                }
            }

            if(end == arr.length) {
                if(arr[i]>arr[start]){
                    return i;
                }
            }

            if(start>=0 && end<arr.length) {
                if(arr[i]>arr[start] && arr[i]>arr[end]){
                    return i;
                }
            }
            start = i;
        }
        return -1;
    }

    public int getPeakElementOptimised(int[] arr) {
        // the intuition is this if we get the mid of the array either it will be the peak or
        // if it is less than the previous element it means that it's on decreasing graph, hence peak can
        // be on the left side.
        // if the mid is on the increasing side and if it is less than mid+1 then the peak will be on the right hand side
        // we just want any peak
        // if the mid is even smaller that the previous element and even smaller than the next element then the peak can be on any side

        int n = arr.length;
        if(arr.length == 1){
            return 0;
        }

        if(arr[0]>arr[1]){
            return 0;
        }

        if(arr[n-1]>arr[n-2]){
            return n-1;
        }

        int l = 1;
        int r = n-2;
        return getFromBs(arr,l,r);

    }

    private int getFromBs(int[] arr ,int l, int r) {
        if(l>r){
            return -1;
        }
        int mid  = l+(r-l)/2;
        if(arr[mid]>arr[mid-1] && arr[mid]>arr[mid+1]) {
            return mid;
        }

        if(arr[mid]<arr[mid-1]) {
            return getFromBs(arr,l,mid-1);
        } else {
            return getFromBs(arr,mid+1,r);
        }
    }
}
