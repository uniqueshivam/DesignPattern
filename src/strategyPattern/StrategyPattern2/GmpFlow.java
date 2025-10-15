package strategyPattern.StrategyPattern2;

public class GmpFlow {
    private GateOpeningStrategy gateOpeningStrategy;

    public void SetGateOpeningStrategy(GateOpeningStrategy gateOpeningStrategy){
        this.gateOpeningStrategy = gateOpeningStrategy;
    }

    public void openGate(String lpr){
        this.gateOpeningStrategy.openGate(lpr);
    }

}
