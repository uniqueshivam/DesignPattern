package strategyPattern.StrategyPattern2;

public class main {
    public static void main(String[] args) {
        GmpFlow gmpFlow = new GmpFlow();
        gmpFlow.SetGateOpeningStrategy(new LPR());
        gmpFlow.openGate("DL2CF4439");

        gmpFlow.SetGateOpeningStrategy(new ReverseQr());
        gmpFlow.openGate("BR0176654");
    }
}
