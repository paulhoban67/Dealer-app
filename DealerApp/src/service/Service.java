package service;

import model.Car;
import model.Dealer;
import model.Tire;
import repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public void changeFilename(String filename) {
        this.repository.setFilename(filename);
    }

    public void addElement(String key, List<Object> list) throws Exception {
        String s = list.get(0).toString();
        String[] parts =s.split(",");
        switch (key) {
            case "car" -> {
                if (!this.repository.getFilename().equals("src/files/Car.csv"))
                    changeFilename("src/files/Car.csv");
                Car car = new Car(Integer.parseInt(parts[0]),
                        parts[1], parts[2], parts[3]);
                this.repository.addElement("car", car);
                break;
            }
            case "dealer" -> {
                if (!this.repository.getFilename().equals("src/files/Dealer.csv"))
                    changeFilename("src/files/Dealer.csv");
                List<Car> listCar = new ArrayList<>();
                for(int i=2;i< parts.length;i+=4) {
                    int idCar=Integer.parseInt(parts[i]);
                    String brand=parts[i+1];
                    String model=parts[i+2];
                    String sizeTire=parts[i+3];
                    Car car = new Car(idCar, brand, model, sizeTire);
                    listCar.add(car);
                }
                Dealer dealer = new Dealer(Integer.parseInt(parts[0]), parts[1], listCar);
                this.repository.addElement("dealer", dealer);
                break;
            }
            case "tire" -> {
                if (!this.repository.getFilename().equals("src/files/Tire.csv"))
                    changeFilename("src/files/Tire.csv");
                Tire tire = new Tire(Integer.parseInt(parts[0]),
                        parts[1], parts[2],
                        parts[3], parts[4], parts[5],
                        Double.parseDouble(parts[6]), Integer.parseInt(parts[8]));
                this.repository.addElement("tire", tire);
                break;
            }
        }
    }

    public void updateElement(String key, List<Object> list) throws Exception {
        String s = list.get(0).toString();
        String[] parts =s.split(",");
        switch (key) {
            case "car" -> {
                if (!this.repository.getFilename().equals("src/files/Car.csv"))
                    changeFilename("src/files/Car.csv");
                Car car = new Car(Integer.parseInt(parts[0]),
                        parts[1], parts[2], parts[3]);
                this.repository.updateElement("car", car);
                break;
            }
            case "dealer" -> {
                if (!this.repository.getFilename().equals("src/files/Dealer.csv"))
                    changeFilename("src/files/Dealer.csv");
                List<Car> listCar = new ArrayList<>();
                for(int i=2;i< parts.length;i+=4) {
                    int idCar = Integer.parseInt(parts[i]);
                    String brand = parts[i + 1];
                    String model = parts[i + 2];
                    String sizeTire = parts[i + 3];
                    Car car = new Car(idCar, brand, model, sizeTire);
                    listCar.add(car);
                }
                Dealer dealer = new Dealer(Integer.parseInt(parts[0]), parts[1], listCar);
                this.repository.updateElement("dealer", dealer);
                break;
            }
            case "tire" -> {
                if (!this.repository.getFilename().equals("src/files/Tire.csv"))
                    changeFilename("src/files/Tire.csv");
                Tire tire = new Tire(Integer.parseInt(parts[0]),
                        parts[1], parts[2],
                        parts[3], parts[4], parts[5],
                        Double.parseDouble(parts[6]), Integer.parseInt(parts[8]));
                this.repository.updateElement("tire", tire);
                break;
            }
        }
    }

    public void deleteElement(String key, String id) throws IOException {
        switch (key) {
            case "car" -> {
                if (!this.repository.getFilename().equals("src/files/Car.csv"))
                    changeFilename("src/files/Car.csv");
                this.repository.deleteElement("car", id);
                break;
            }
            case "dealer" -> {
                if (!this.repository.getFilename().equals("src/files/Dealer.csv"))
                    changeFilename("src/files/Dealer.csv");
                this.repository.deleteElement("dealer", id);
                break;
            }
            case "tire" -> {
                if (!this.repository.getFilename().equals("src/files/Tire.csv"))
                    changeFilename("src/files/Tire.csv");
                this.repository.deleteElement("tire", id);
                break;
            }
        }
    }

    public List<Object> showElement(String key) {
        switch (key) {
            case "car" -> {
                if (!this.repository.getFilename().equals("src/files/Car.csv"))
                    changeFilename("src/files/Car.csv");
                break;
            }
            case "dealer" -> {
                if (!this.repository.getFilename().equals("src/files/Dealer.csv"))
                    changeFilename("src/files/Dealer.csv");
                break;
            }
            case "tire" -> {
                if (!this.repository.getFilename().equals("src/files/Tire.csv"))
                    changeFilename("src/files/Tire.csv");
                break;
            }
        }


        return this.repository.show(key);
    }

    public Object getElement(String key, int id) {
        List<Object> list = new ArrayList<>();
        switch (key) {
            case "car" -> {
                list = this.showElement(key);
                for(Object car: list) {
                    if (car.toString().split(",")[0].equals(String.valueOf(id)))
                        return car;
                }
            }
            case "dealer" -> {
                list = this.showElement(key);
                for(Object dealer: list) {
                    if (dealer.toString().split(",")[0].equals(String.valueOf(id)))
                        return dealer;
                }
            }
            case "tire" -> {
                list = this.showElement(key);
                for(Object tire: list) {
                    if (tire.toString().split(",")[0].equals(String.valueOf(id)))
                        return tire;
                }
            }
        }
        return null;
    }
}

