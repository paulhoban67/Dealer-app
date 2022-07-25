package model;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String sizeTire;

    public Car(int id, String brand, String model, String sizeTire) throws Exception {
        this.id = id;
        this.brand = brand;
        this.model = model;
        if(sizeTire.matches("[0-9]+/[0-9]+/[0-9]+"))
            this.sizeTire = sizeTire;
        else throw new Exception();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSizeTire() {
        return sizeTire;
    }

    public void setSizeTire(String sizeTire) {
        this.sizeTire = sizeTire;
    }

    @Override
    public String toString() {
        return id +"," + brand +","+ model+"," + sizeTire;
    }
}
