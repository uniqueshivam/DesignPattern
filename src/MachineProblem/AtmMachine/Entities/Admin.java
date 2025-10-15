package MachineProblem.AtmMachine.Entities;

import MachineProblem.AtmMachine.Enum.Access;

public class Admin implements User{
    private String adminId;
    private String name;
    private Access access;
    @Override
    public void performAction() {

    }

    @Override
    public String getUserId() {
        return this.adminId;
    }

    public Admin(String customerId, String name, Access access) {
        this.adminId = customerId;
        this.name = name;
        this.access = access;
    }

    public String getCustomerId() {
        return adminId;
    }

    public String getName() {
        return name;
    }

    public Access getAccess() {
        return access;
    }
}

