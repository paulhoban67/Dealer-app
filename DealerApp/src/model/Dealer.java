package model;

import java.util.List;

public class Dealer {
    private int id;
    private String name;
    private List<Car> listCars;

    public Dealer(int id, String name, List<Car> listCars) {
        this.id = id;
        this.name = name;
        this.listCars=listCars;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getListCars() {
        return listCars;
    }

    public void setListCars(List<Car> listCars) {
        this.listCars = listCars;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i=0; i<this.listCars.size(); i++) {
            s.append(this.listCars.get(i).toString());
            if(i<this.listCars.size()-1) s.append(",");
        }
        return id +","+ name + "," +s;
    }
}
