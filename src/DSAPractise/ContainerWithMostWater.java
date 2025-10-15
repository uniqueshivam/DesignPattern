package DSAPractise;

public class ContainerWithMostWater {
    public int getMaxWater(int[] arr) {
        int start = 0;
        int end = arr.length-1;
        int max = Integer.MIN_VALUE;
        while(start<end) {
            int val = Math.min(arr[end],arr[start])*(end-start);
            max = Math.max(val,max);
            if(arr[start]<arr[end]){
                start++;
            } else if(arr[start]>arr[end]) {
                end--;
            } else {
                start++;
                end--;
            }
        }
        return max;
    }

}
