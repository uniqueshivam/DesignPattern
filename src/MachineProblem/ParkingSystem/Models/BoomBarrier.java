package MachineProblem.ParkingSystem.Models;


public class BoomBarrier {
    private String id;
    private boolean BoomBarrierOpen = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBoomBarrierOpen() {
        return BoomBarrierOpen;
    }

    public void setBoomBarrierOpen(boolean boomBarrierOpen) {
        BoomBarrierOpen = boomBarrierOpen;
    }
}
