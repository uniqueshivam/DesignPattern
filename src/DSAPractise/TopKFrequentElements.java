package DSAPractise;

import lombok.Getter;

import java.util.*;

public class TopKFrequentElements {
    public int[] getKFrequentElement(int[] arr, int k) {
        if(arr.length == 1){
            return arr;
        }
        Arrays.sort(arr);
        int[] ans = new int[k];
        List<value> valueList= new ArrayList<>();
        int count = 1;
        int val = arr[0];
        for(int i = 1;i<arr.length;i++) {
            if(arr[i] == arr[i-1]){
                count++;
            } else {
                value v = new value(val,count);
                count = 1;
                val = arr[i];
                valueList.add(v);
            }

            if(i == arr.length-1) {
                value v = new value(val,count);
                valueList.add(v);
            }
        }

        valueList.sort(new Comparator<value>() {
            @Override
            public int compare(value o1, value o2) {
                return Integer.compare(o1.getCounter(), o2.getCounter());
            }
        });

        int counter = 0;
        for(int i = valueList.size()-1;i>=valueList.size()-k;i--){
            ans[counter] = valueList.get(i).getValue();
            counter++;
        }
        return ans;
    }
}

@Getter
class value {
    int value;
    int counter;
    public value (int value, int counter) {
        this.value  = value;
        this.counter = counter;
    }

    public int getValue(){
        return this.value;
    }

    public int getCounter(){
        return this.counter;
    }

}
