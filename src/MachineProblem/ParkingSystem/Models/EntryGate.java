package MachineProblem.ParkingSystem.Models;

public class EntryGate implements Gate{
    private Integer gateId;
    private BoomBarrier boomBarrier;

    public EntryGate(Integer gateId){
        this.gateId = gateId;
    }

    @Override
    public void openGate() {
        System.out.println("Opening entry gate");
        this.boomBarrier.setBoomBarrierOpen(true);
    }

    @Override
    public void closeGate() {
        System.out.println("Closing the entry gate");
        this.boomBarrier.setBoomBarrierOpen(false);
    }
}
