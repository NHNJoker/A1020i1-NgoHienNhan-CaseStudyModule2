package models;

public class House extends Services {
    private String typeOfRoom;
    private String otherAmenities;
    private int numOfFloor;

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

    public int getNumOfFloor() {
        return numOfFloor;
    }

    public void setNumOfFloor(int numOfFloor) {
        this.numOfFloor = numOfFloor;
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
        System.out.println("Num of floor:");
        System.out.println(this.getNumOfFloor());
    }

}
