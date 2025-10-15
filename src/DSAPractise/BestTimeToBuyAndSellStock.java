package DSAPractise;

public class BestTimeToBuyAndSellStock {
    public int getMaxProfit(int[] arr) {
        int purchase = 0;
        int maxProfit = 0;
        //made the initial purchase with arr[0]

        for(int i = 0;i<arr.length;i++) {
            // if any element is found with lower than purchase then select that as the purchase
            if(arr[i]<=arr[purchase]) {
                purchase =i;
            } else {
                int diff = arr[i] - arr[purchase];
                maxProfit = Math.max(maxProfit,diff);
            }
        }
        return maxProfit;
    }
}
