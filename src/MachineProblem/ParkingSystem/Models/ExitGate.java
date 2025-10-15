package MachineProblem.ParkingSystem.Models;

public class ExitGate implements Gate{
    private Integer gateId;
    private BoomBarrier boomBarrier;

    public ExitGate(Integer gateId) {

        this.gateId = gateId;
    }
    @Override
    public void openGate() {
        System.out.println("Opening exit gate");
        this.boomBarrier.setBoomBarrierOpen(true);
    }

    @Override
    public void closeGate() {
        System.out.println("Opening exit gate");
        this.boomBarrier.setBoomBarrierOpen(true);
    }
}
