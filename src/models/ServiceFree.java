package models;

public class ServiceFree {
    private String serviceFreeName;
    private int unit;
    private int price;

    public ServiceFree() {

    }

    public ServiceFree(String serviceFreeName, int unit, int price) {
        this.serviceFreeName = serviceFreeName;
        this.unit = unit;
        this.price = price;
    }

    public String getServiceFreeName() {
        return serviceFreeName;
    }

    public void setServiceFreeName(String serviceFreeName) {
        this.serviceFreeName = serviceFreeName;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServiceFree{" +
                "serviceName='" + serviceFreeName + '\'' +
                ", unit=" + unit +
                ", price=" + price +
                '}';
    }
}
