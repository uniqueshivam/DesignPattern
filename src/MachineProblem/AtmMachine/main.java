package MachineProblem.AtmMachine;

import MachineProblem.AtmMachine.AtmCommandController.CashWithDrawlCommand;
import MachineProblem.AtmMachine.AtmCommandController.Commander;
import MachineProblem.AtmMachine.Entities.Customer;
import MachineProblem.AtmMachine.Entities.User;
import MachineProblem.AtmMachine.Enum.Access;
import MachineProblem.AtmMachine.OutputDevices.Display;
import MachineProblem.AtmMachine.Repository.DataBaseRepo;
import MachineProblem.AtmMachine.Strategy.CustomerValidation;
import MachineProblem.AtmMachine.Strategy.UserValidationStrategy;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Display display = new Display();
        Dispenser dispenser = new Dispenser();
        StatementDispenser statementDispenser = new StatementDispenser();
        UserValidationStrategy userValidationStrategy;
        DataBaseRepo dataBaseRepo = new DataBaseRepo();
        AtmMachine atmMachine = new AtmMachine(display,dispenser,statementDispenser);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter user access");
        Access acc = Access.valueOf(sc.nextLine());
        if(Access.USER == acc){
            System.out.println("Please enter card details ");
            String card = sc.nextLine();
            System.out.println("Please enter how you want to proceed");
            String operation = sc.nextLine();
            switch (operation){
                case "CASH":
                    System.out.println("Please enter the amount to proceed");
                    Integer amount = sc.nextInt();
                    System.out.println("Please enter the customerId");
                    String customerId = sc.nextLine();
                    User customer = new Customer(customerId,"Shivam","4559911234");
                    userValidationStrategy = new CustomerValidation();
                    Commander cashCommand = new CashWithDrawlCommand(customer,amount,userValidationStrategy,dataBaseRepo);
                    atmMachine.processOperation(cashCommand);

            }
        }
    }
}
