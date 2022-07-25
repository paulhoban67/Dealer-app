package repository;

import model.Car;
import model.Dealer;
import model.Tire;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private Map<String, List<Object>> repos;
    private String filename;

    public Repository() {
        this.repos = new HashMap<>();
        initializeMapEmpty();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        readFromFile();
    }

    public void initializeMapEmpty() { // init a empty map for repository
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();

        this.repos.put("car", list1);
        this.repos.put("tire", list2);
        this.repos.put("dealer", list3);
    }

    public Repository(String filename) { // constructor with params and initialization a empty map
        this.filename = filename;
        this.repos = new HashMap<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();

        this.repos.put("car", list1);
        this.repos.put("tire", list2);
        this.repos.put("dealer", list3);
        readFromFile();
    }

    public void readFromFile(){
        try {
            this.repos.clear();
            initializeMapEmpty();

            BufferedReader fileIn=new BufferedReader(new FileReader(this.filename));
            String s;
            while((s=fileIn.readLine())!=null){
                String[] parts =s.split(",");
                // depending on the filename repository, it will be done on a case-by-case basis
                switch (this.filename){
                    case "src/files/Car.csv": {
                        int id=Integer.parseInt(parts[0]);
                        String brand=parts[1];
                        String model=parts[2];
                        String sizeTire=parts[3];
                        Car car = new Car(id, brand, model, sizeTire);
                        this.repos.get("car").add(car);
                        break;
                    }
                    case "src/files/Dealer.csv": {
                        int id=Integer.parseInt(parts[0]);
                        String name=parts[1];
                        List<Car> list = new ArrayList<>();
                        for(int i=2;i< parts.length;i+=4) {
                            int idCar=Integer.parseInt(parts[i]);
                            String brand=parts[i+1];
                            String model=parts[i+2];
                            String sizeTire=parts[i+3];
                            Car car = new Car(idCar, brand, model, sizeTire);
                            list.add(car);
                        }
                        Dealer dealer = new Dealer(id, name, list);
                        this.repos.get("dealer").add(dealer);
                        break;
                    }
                    case "src/files/Tire.csv": {
                        int id=Integer.parseInt(parts[0]);
                        String brand=parts[1];
                        String model=parts[2];
                        String sizeTire=parts[3];
                        String type=parts[4];
                        String season=parts[5];
                        Double price= Double.parseDouble(parts[6]);
                        Double discount=Double.parseDouble(parts[7]);
                        int stock = Integer.parseInt(parts[8]);
                        Tire tire = new Tire(id, brand, model, sizeTire, type, season, price, stock);
                        this.repos.get("tire").add(tire);
                        break;
                    }
                }
            }
            fileIn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeOnFile() throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename));

        switch (this.filename) {
            // depending on the filename repository, it will be done on a case-by-case basis
            case "src/files/Car.csv" -> {
                for (Map.Entry<String, List<Object>> m : this.repos.entrySet()) {
                    if (m.getKey().equals("car")) {
                        StringBuilder line = new StringBuilder();
                        for (Object o : m.getValue()) {
                            line.append(o.toString());
                            line.append("\n");
                        }
                        writer.write(line.toString());
                    }
                }
                break;
            }
            case "src/files/Dealer.csv" -> {
                for (Map.Entry<String, List<Object>> m : this.repos.entrySet()) {
                    if (m.getKey().equals("dealer")) {
                        StringBuilder line = new StringBuilder();
                        for (Object o : m.getValue()) {
                            line.append(o.toString());
                            line.append("\n");
                        }
                        writer.write(line.toString());
                    }
                }
                break;
            }
            case "src/files/Tire.csv" -> {
                for (Map.Entry<String, List<Object>> m : this.repos.entrySet()) {
                    if (m.getKey().equals("tire")) {
                        StringBuilder line = new StringBuilder();
                        for (Object o : m.getValue()) {
                            line.append(o.toString());
                            line.append("\n");
                        }
                        writer.write(line.toString());
                    }
                }
                break;
            }
        }
        writer.close();
    }

    public void addElement(String key, Object o) throws IOException {
        // depending on the key of object to added, it will be done on a case-by-case basis
        switch (key) {
            case "car" -> {
                this.repos.get("car").add(o);
                break;
            }
            case "tire" -> {
                this.repos.get("tire").add(o);
                break;
            }
            case "dealer" -> {
                this.repos.get("dealer").add(o);
                break;
            }
        }
        writeOnFile();
    }

    public void updateElement(String key, Object o) throws IOException {
        switch (key) {
            case "car" -> {
                List<Object> list;
                List<Object> newList = new ArrayList<>();
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String id = list.get(i).toString().split(",")[0];
                    String newId = o.toString().split(",")[0];
                    if(id.equals(newId)) {
                        newList.add(o);
                    }
                    else
                        newList.add(list.get(i));
                }
                list = newList;
                this.repos.remove("car");
                this.repos.put("car", list);
                break;
            }
            case "tire" -> {
                List<Object> list;
                List<Object> newList = new ArrayList<>();
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String id = list.get(i).toString().split(",")[0];
                    String newId = o.toString().split(",")[0];
                    if(id.equals(newId)) {
                        newList.add(o);
                    }
                    else
                        newList.add(list.get(i));
                }
                list = newList;
                this.repos.remove("tire");
                this.repos.put("tire", list);
                break;
            }
            case "dealer" -> {
                List<Object> list;
                List<Object> newList = new ArrayList<>();
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String id = list.get(i).toString().split(",")[0];
                    String newId = o.toString().split(",")[0];
                    if(id.equals(newId)) {
                        newList.add(o);
                    }
                    else
                        newList.add(list.get(i));
                }
                list = newList;
                this.repos.remove("dealer");
                this.repos.put("dealer", list);
                break;
            }
        }
        writeOnFile();
    }


    public void deleteElement(String key, String id) throws IOException {
        switch (key) {
            case "car" -> {
                List<Object> list;
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String idToDelete = list.get(i).toString().split(",")[0];
                    if(idToDelete.equals(id)) {
                        list.remove(i);
                    }
                }
                this.repos.remove("car");
                this.repos.put("car", list);
                break;
            }
            case "tire" -> {
                List<Object> list;
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String idToDelete = list.get(i).toString().split(",")[0];
                    if(idToDelete.equals(id)) {
                        list.remove(i);
                    }
                }
                this.repos.remove("tire");
                this.repos.put("tire", list);
                break;
            }
            case "dealer" -> {
                List<Object> list;
                list = this.repos.get(key);
                for(int i =0;i<list.size(); i++) {
                    String idToDelete = list.get(i).toString().split(",")[0];
                    if(idToDelete.equals(id)) {
                        list.remove(i);
                    }
                }
                this.repos.remove("dealer");
                this.repos.put("dealer", list);
                break;
            }
        }
        writeOnFile();
    }


    public List<Object> show(String key) {
        readFromFile();
        switch (key) {
            case "car" -> {
                return this.repos.get("car");
            }
            case "tire" -> {
                return this.repos.get("tire");
            }
            case "dealer" -> {
                return this.repos.get("dealer");
            }
        }
        return null;
    }
}
