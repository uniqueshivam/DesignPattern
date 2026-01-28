package DSAPractise;

public class HIndex {
    public int hIndex(int[] citations) {
        int[] arr = new int[1000];
        int max = 0;
        for(Integer i : citations) {
            arr[i]++;
        }

        for(int i = 0;i<1000;i++) {
            if(arr[i]!=0) {
                int count = arr[i];
                if(count<=i) {
                    max = Math.max(max,count);
                } else {
                    max = Math.max(max,i);
                    continue;
                }

                for(int j = i+1;j<1000;j++) {
                    if(arr[j]!=0) {
                        int val = arr[j];
                        count = count+val;
                        if(count<=i) {
                            max = Math.max(max,count);
                        } else  {
                           int diff = count-i;
                           if(val>diff) {
                               max = Math.max(max,i);
                           }
                        }
;                    }
                }
            }
        }
        return max;
    }
}
