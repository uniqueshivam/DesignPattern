package AtmMachine;

public class HundredAmountProcessor extends AmountProcessor{
    public int amountRemaining = 10000;
    public HundredAmountProcessor(AmountProcessor currencyLevelAmountProcessor) {
        super(currencyLevelAmountProcessor);
    }

    @Override
    public void processWithdrawal(int currency, int amountPending) {
        super.processWithdrawal(currency, amountPending);
    }
}
