package AtmMachine;

public class AmountProcessor {
    public static int HUNDRED = 100;
    public static int TWO_HUNDRED = 200;
    public static int FIVE_HUNDRED = 500;

    AmountProcessor currencyLevelAmountProcessor;
    public AmountProcessor(AmountProcessor currencyLevelAmountProcessor){
        this.currencyLevelAmountProcessor = currencyLevelAmountProcessor;
    }

    public void processWithdrawal(int currency, int amountPending){
        if(currencyLevelAmountProcessor!=null){
            currencyLevelAmountProcessor.processWithdrawal(currency,amountPending);
        }
    }
}
