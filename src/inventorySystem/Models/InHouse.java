package inventorySystem.Models;

public class InHouse extends Part {
    private int machineId;

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}
