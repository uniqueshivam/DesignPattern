package DSAPractise;

public class FindTheDuplicateNumber {
    public int findDuplicate(int[] arr) {
        int max = 0;
        for(Integer i : arr) {
            if(i>max){
                max = i;
            }
        }

        int[] freq = new int[max+1];

        for(Integer i : arr) {
            freq[i]++;
        }

        for(int i = 0;i< freq.length;i++) {
            if(freq[i]>1){
                return i;
            }
        }
        return max;
    }

//    public int findDuplicateOptimal(int[] arr) {
//        int slow= 0;
//        int fast = 0;
//
//    }
}
