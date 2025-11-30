package DSAPractise;

public class SegregateZeroAndOne {
    public int[] solve(int[] A) {
       int start = 0;
       int end = A.length-1;

        while(start < end) {
            if(A[start] == 0) {
                start++;
            } else if(A[end] == 1) {
                end--;
            } else {  // A[start]==1 && A[end]==0
                swap(A, start, end);
                start++;
                end--;
            }
        }
       return A;
    }

    public void swap(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }
}
