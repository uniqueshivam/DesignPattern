package strategyPattern.StrategyPattern2;

public class ReverseQr implements GateOpeningStrategy{
    @Override
    public void openGate(String lpr) {
        System.out.println("Opening gate with reverse qr for number "+lpr);
    }
}
