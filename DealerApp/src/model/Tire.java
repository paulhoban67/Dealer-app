package model;

public class Tire {
    private int id;
    private String brand;
    private String model;
    private String sizeTire;
    private String type;
    private String season;
    private Double price;
    private Double discount;
    private int stock;

    public Tire(int id, String brand, String model, String sizeTire, String type, String season, Double price, int stock) throws Exception {
        this.id = id;
        this.brand = brand;
        this.model = model;
        if(sizeTire.matches("[0-9]+/[0-9]+/[0-9]+"))
            this.sizeTire = sizeTire;
        else throw new Exception("Format required: latime/inaltime/raza");
        if(type.equals("normal") || type.equals("run-flat"))
            this.type = type;
        else throw new Exception("Type required: normal/run-flat");
        if(season.equals("winter") || season.equals("summer"))
            this.season = season;
        else throw new Exception("Season required: summer/winter");
        this.price = price;
        this.discount = 0.0;
        this.stock = stock;
        if(this.season.equals("summer")) this.discount+=10;
        if(this.season.equals("winter")) this.discount+=15;
        if(this.type.equals("run-flat")) this.discount+=5;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return id +"," + brand +","+ model+"," + sizeTire+ ","+type+","+season+","+price+","+discount+","+stock;
    }
}
