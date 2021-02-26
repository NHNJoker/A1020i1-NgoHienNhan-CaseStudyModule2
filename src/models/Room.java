package models;

public class Room extends Services {


    private ServiceFree serviceFreeObj;

    public ServiceFree getServiceFreeObj() {
        return serviceFreeObj;
    }

    public void setServiceFreeObj(ServiceFree serviceFreeObj) {
        this.serviceFreeObj = serviceFreeObj;
    }

    public void showInfor() {
        System.out.println("Id:");
        System.out.println(this.getId());
        System.out.println("Acreage use:");
        System.out.println(this.getAreaUsed());
        System.out.println("Service name:");
        System.out.println(this.getServiceName());
        System.out.println("Rental costs:");
        System.out.println(this.getRentalCosts());
        System.out.println("Rental type:");
        System.out.println(this.getRentalType());
        System.out.println("Max people:");
        System.out.println(this.getMaxPeople());
        System.out.println("Service free:");
        System.out.println(getServiceFreeObj().getServiceFreeName());
    }
}
