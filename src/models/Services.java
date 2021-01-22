package models;

public abstract class Services {
    private String serviceName;
    private double areaUsed;
    private int rentalCosts;
    private int maxPeople;
    private String rentalType;
    private String id;

    public Services() {
    }

    public abstract void showInfor();

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setAreaUsed(double areaUsed) {
        this.areaUsed = areaUsed;
    }

    public void setRentalCosts(int rentalCosts) {
        this.rentalCosts = rentalCosts;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getAreaUsed() {
        return areaUsed;
    }

    public int getRentalCosts() {
        return rentalCosts;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public String getRentalType() {
        return rentalType;
    }

    public String getId() {
        return id;
    }

}
