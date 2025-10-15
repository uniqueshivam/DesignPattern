package DSAPractise;

public class EquilibriumIndexOfArray {
    public int findEquilibriumIndex(int[] arr) {
        int sum = 0;
        for(Integer i :arr) {
            sum+=i;
        }
        int startSum = 0;
        int point = 0;
        while(point<arr.length) {
            sum = sum-arr[point];
            if(startSum == sum) {
                return point;
            } else  {
                startSum = startSum+arr[point];
            }
            point++;
        }
        return -1;
    }
}
