package DSAPractise;

public class TrailingZerosInFactorial {
    public int trailingZeroes(int A) {
        int ans = 0;
        int num = 5;
        while(true) {
           int factors = A/num;
           if(factors<1) {
               break;
           }
           ans = ans+factors;
           num = num*5;

        }
        return ans;
    }
}

/**
 * The main logic here is that any number will have multiple of 5 and also multiple of 2,4 which will producy in 0.
 * So we find multiple of 5,125,625...... as the number increases
 */
