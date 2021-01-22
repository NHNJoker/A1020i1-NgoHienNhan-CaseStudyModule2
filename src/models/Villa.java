package models;

public class Villa extends Services {
    private String typeOfRoom;
    private String otherAmenities;
    private double acreagePool;
    private int numOfFloor;

    public Villa() {

    }

    public Villa(String typeOfRoom, String otherAmenities, double acreagePool, int numOfFloor) {
        this.typeOfRoom = typeOfRoom;
        this.otherAmenities = otherAmenities;
        this.acreagePool = acreagePool;
        this.numOfFloor = numOfFloor;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public String getOtherAmenities() {
        return otherAmenities;
    }

    public void setOtherAmenities(String otherAmenities) {
        this.otherAmenities = otherAmenities;
    }

    public double getAcreagePool() {
        return acreagePool;
    }

    public void setAcreagePool(double acreagePool) {
        this.acreagePool = acreagePool;
    }

    public int getNumOfFloor() {
        return numOfFloor;
    }

    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "id="+getId()+'\''+
                "serviceName="+getServiceName()+'\''+
                "areaUsed="+getAreaUsed()+'\''+
                "rentalCost="+getRentalCosts()+'\''+
                "maxPeople="+getMaxPeople()+'\''+
                "rentalType="+getRentalType()+'\''+
                "typeOfRoom='" + typeOfRoom + '\'' +
                ", otherAmenities='" + otherAmenities + '\'' +
                ", acreagePool=" + acreagePool +
                ", numOfFloor=" + numOfFloor +
                '}';
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
        System.out.println("Type of room:");
        System.out.println(this.getTypeOfRoom());
        System.out.println("Other amenities:");
        System.out.println(this.getOtherAmenities());
        System.out.println("Acreage pool:");
        System.out.println(this.getAcreagePool());
        System.out.println("Num of floor:");
        System.out.println(this.getNumOfFloor());
    }

}
