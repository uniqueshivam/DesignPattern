package MachineProblem.AtmMachine;

import MachineProblem.AtmMachine.AtmCommandController.Commander;
import MachineProblem.AtmMachine.OutputDevices.Display;

public class AtmMachine {
    private Display display;
    private Dispenser dispenser;
    private StatementDispenser statementDispenser;

    public AtmMachine(Display display, Dispenser dispenser, StatementDispenser statementDispenser) {
        this.display = display;
        this.dispenser = dispenser;
        this.statementDispenser = statementDispenser;
    }

    public void processOperation(Commander commander){
        commander.executeCommand();
        display.notify("Amount withdrawn");
        dispenser.dispenseCash(500);

    }
}
