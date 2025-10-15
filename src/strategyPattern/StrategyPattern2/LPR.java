package strategyPattern.StrategyPattern2;

public class LPR implements GateOpeningStrategy{
    @Override
    public void openGate(String lpr) {
        System.out.println("opening gate with lpr technique for no "+ lpr);
    }
}
