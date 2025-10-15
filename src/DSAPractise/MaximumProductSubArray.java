package DSAPractise;

public class MaximumProductSubArray {
    public int getMaxProduct(int[] arr) {
      int max = Integer.MIN_VALUE;
      int prefix = 1;
      int suffix = 1;
      int n = arr.length;
      for(int i = 0;i<n;i++) {
          prefix = prefix*arr[i];
          suffix = suffix*arr[n-i-1];
          max = Math.max(max,prefix);
          max = Math.max(max,suffix);
          if(arr[i] == 0) {
             prefix = 1;
          }
          if(arr[n-i-1] == 0) {
              suffix = 1;
          }
      }
      return max;
    }
}
