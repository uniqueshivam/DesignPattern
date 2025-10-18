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

    public int findDuplicateOptimal(int[] arr) {
        //the optimized solution uses the concept of cycle in the linked list, it's little difficult to understand
        int slow= arr[0];
        int fast = arr[0];

        do {
            slow = arr[slow];
            fast = arr[arr[fast]];

        } while(slow!=fast);
        fast = arr[0];
        while(slow!=fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }
}
