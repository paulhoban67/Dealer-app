package ui;

import model.Car;
import model.Dealer;
import model.Tire;
import service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Service service;

    public Ui(Service service) {
        this.service = service;
    }

    // Create update delete show for Car, Dealer, Tire
    public void addCar() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Car: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("BRAND of Car: ");
        String brand = scanner.readLine();
        System.out.print("MODEL of Car: ");
        String model = scanner.readLine();
        System.out.print("SIZE Tire: ");
        String sizeTire;
        try {
            sizeTire = scanner.readLine();
            Car car = new Car(id, brand, model, sizeTire);
            List<Object> list = new ArrayList<>();
            list.add(car);
            this.service.changeFilename("src/files/Car.csv");
            this.service.addElement("car", list);
        }
        catch (Exception e) {
            System.out.println("Format required: latime/inaltime/raza");
            addCar();
        }
    }
    public void updateCar() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Car to update: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("new BRAND of Car: ");
        String brand = scanner.readLine();
        System.out.print("new MODEL of Car: ");
        String model = scanner.readLine();
        System.out.print("new SIZE Tire: ");
        String sizeTire;
        try {
            sizeTire = scanner.readLine();
            Car car = new Car(id, brand, model, sizeTire);
            List<Object> list = new ArrayList<>();
            list.add(car);
            this.service.changeFilename("src/files/Car.csv");
            this.service.updateElement("car",list);
        }
        catch (Exception e) {
            System.out.println("Format required: latime/inaltime/raza");
            updateCar();
        }

    }
    public void deleteCar() throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Car to delete: ");
        String id = scanner.readLine();
        this.service.changeFilename("src/files/Car.csv");
        this.service.deleteElement("car",id);
    }
    public void showCar() {
        this.service.changeFilename("src/files/Car.csv");
        List<Object> list = new ArrayList<>(this.service.showElement("car"));
        System.out.println("CAR:");
        for(Object obj: list) {
            System.out.println(obj.toString());
        }
    }

    public void addDealer() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Dealer: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("NAME of Dealer: ");
        String name = scanner.readLine();

        System.out.println("CARS:");
        this.showCar();
        System.out.println("(if stop, type exit) Id Car: ");
        String optiune = scanner.readLine();
        List<Car> list = new ArrayList<>();
        while(!optiune.equals("exit")) {
            String[] parts = this.service.getElement("car",Integer.parseInt(optiune)).toString().split(",");
            int idCar=Integer.parseInt(parts[0]);
            String brand=parts[1];
            String model=parts[2];
            String sizeTire=parts[3];
            Car car = new Car(idCar,brand,model,sizeTire);
            list.add(car);

            this.showCar();
            System.out.println("(if stop, type exit) Id Car: ");
            optiune = scanner.readLine();
        }

        Dealer dealer = new Dealer(id, name,list);
        List<Object> listD = new ArrayList<>();
        listD.add(dealer);
        this.service.changeFilename("src/files/Dealer.csv");
        this.service.addElement("dealer",listD);
    }
    public void updateDealer() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Dealer to update: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("new NAME of Dealer: ");
        String name = scanner.readLine();

        String[] parts = this.service.getElement("dealer",id).toString().split(",");
        List<Car> listC = new ArrayList<>();
        for(int i=2;i< parts.length;i+=4) {
            int idCar=Integer.parseInt(parts[i]);
            String brand=parts[i+1];
            String model=parts[i+2];
            String sizeTire=parts[i+3];
            Car car = new Car(idCar, brand, model, sizeTire);
            listC.add(car);
        }

        Dealer dealer = new Dealer(id, name,listC);
        List<Object> list = new ArrayList<>();
        list.add(dealer);
        this.service.changeFilename("src/files/Dealer.csv");
        this.service.updateElement("dealer",list);
    }
    public void deleteDealer() throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Dealer to delete: ");
        String id = scanner.readLine();
        this.service.changeFilename("src/files/Dealer.csv");
        this.service.deleteElement("dealer",id);
    }
    public void showDealer() {
        this.service.changeFilename("src/files/Dealer.csv");
        List<Object> list = new ArrayList<>(this.service.showElement("dealer"));
        System.out.println("DEALER:");
        for(Object obj: list) {
            System.out.println(obj.toString());
        }
    }

    public void addTire() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Tire: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("BRAND of Tire: ");
        String brand = scanner.readLine();
        System.out.print("MODEL of Tire: ");
        String model = scanner.readLine();
        System.out.print("SIZE Tire in inch: ");
        String sizeTire;
        try {
            sizeTire = scanner.readLine();
            System.out.print("TYPE of Tire: ");
            String type = scanner.readLine();
            System.out.print("SEASON of Tire: ");
            String season = scanner.readLine();
            System.out.print("PRICE of Tire: ");
            double price = Double.parseDouble(scanner.readLine());
            System.out.print("STOCK of Tire: ");
            int stock = Integer.parseInt(scanner.readLine());
            Tire tire = new Tire(id, brand, model, sizeTire, type, season, price, stock);
            List<Object> list = new ArrayList<>();
            list.add(tire);
            this.service.changeFilename("src/files/Tire.csv");
            this.service.addElement("tire", list);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            addTire();
        }
    }
    public void updateTire() throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Tire to update: ");
        int id = Integer.parseInt(scanner.readLine());
        System.out.print("new BRAND of Tire: ");
        String brand = scanner.readLine();
        System.out.print("new MODEL of Tire: ");
        String model = scanner.readLine();
        System.out.print("new SIZE Tire: ");
        String sizeTire;
        try {
            sizeTire = scanner.readLine();
            System.out.print("new TYPE of Tire: ");
            String type = scanner.readLine();
            System.out.print("new SEASON of Tire: ");
            String season = scanner.readLine();
            System.out.print("new PRICE of Tire: ");
            double price = Double.parseDouble(scanner.readLine());
            System.out.print("new STOCK of Tire: ");
            int stock = Integer.parseInt(scanner.readLine());
            Tire tire = new Tire(id, brand, model, sizeTire, type, season, price, stock);
            List<Object> list = new ArrayList<>();
            list.add(tire);
            this.service.changeFilename("src/files/Tire.csv");
            this.service.updateElement("tire", list);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            updateTire();
        }
    }
    public void deleteTire() throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID Tire to delete: ");
        String id = scanner.readLine();
        this.service.changeFilename("src/files/Tire.csv");
        this.service.deleteElement("tire",id);
    }
    public void showTire() {
        this.service.changeFilename("src/files/Tire.csv");
        List<Object> list = new ArrayList<>(this.service.showElement("tire"));
        System.out.println("TIRE:");
        for(Object obj: list) {
            System.out.println(obj.toString());
        }
    }

    public void buyTires() throws Exception {
        System.out.println("Who DEALER you are?");
        this.service.changeFilename("src/files/Dealer.csv");
        showDealer();
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        String option = scanner.readLine();
        menuDealer(option);
    }
    public void menuDealer(String dealer) throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = this.service.getElement("dealer",Integer.parseInt(dealer)).toString().split(",");
        System.out.println();
        System.out.println(parts[1]);
        List<Car> listC = new ArrayList<>();

        createListWithCar(listC, parts); // create a list with dealer's car

        for(Car car: listC) {
            System.out.println(car.toString());
        }

        System.out.println("Who car want to buy tires?");
        String newOption = scanner.readLine();

        cumparare(listC, newOption, dealer);

    }
    public void cumparare(List<Car> listC, String newOption, String dealer) throws Exception {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        for(Car car: listC) {
            if(car.getId() == Integer.parseInt(newOption)) {
                System.out.println("Size tire: " + car.getSizeTire());
                System.out.println("TIRE in stock with "+car.getSizeTire()+" dimension: ");
                List<Object> list = new ArrayList<>(this.service.showElement("tire"));
                // filter tire
                filterBySizeTire(car, list);

                System.out.println("Type tire (normal/run-flat): ");
                String type = scanner.readLine();
                System.out.println("TIRE in stock with "+type+" TYPE: ");

                filterBySizeTireAndType(car, list, type);

                System.out.println("Season tire (summer/winter): ");
                String season = scanner.readLine();
                System.out.println("TIRE in stock with "+season+" SEASON: ");

                filterBySizeTireAndTypeAndSeason(car, list, type,season);

                System.out.println("Who tires want to buy for "+car.getBrand()+" "+car.getModel()+"?");
                String o = scanner.readLine();
                System.out.println("How much tires?");
                int nr = Integer.parseInt(scanner.readLine());
                String price="", priceDisc="";

                // final price for tires
                String re=buyTire(list, o, nr, price, priceDisc);
                if (re.equals("Out of stock")) {
                    System.out.println("Out of stock");
                    menuDealer(dealer);
                }
                else {
                    System.out.print("Total price: ");
                    System.out.println(re.split("-")[0]);
                    System.out.print("Total price with discount:");
                    System.out.println(re.split("-")[1]);
                    System.out.println("Continue transaction? (yes/no)");
                    String op = scanner.readLine();
                    if (op.equals("yes"))
                        buy(o, nr);
                }
            }
        }

    }
    public void createListWithCar(List<Car> listC, String[] parts) throws Exception {
        for(int i=2;i< parts.length;i+=4) {
            int idCar=Integer.parseInt(parts[i]);
            String brand=parts[i+1];
            String model=parts[i+2];
            String sizeTire=parts[i+3];
            Car car = new Car(idCar, brand, model, sizeTire);
            listC.add(car);
        }
    }
    public void filterBySizeTire(Car car,List<Object> list){
        this.service.changeFilename("src/files/Tire.csv");
        for(Object obj: list) {
            if(obj.toString().split(",")[3].equals(car.getSizeTire())) {
                System.out.println(obj.toString());
            }
        }
    }
    public void filterBySizeTireAndType(Car car, List<Object> list, String type){
        for(Object obj: list) {
            if(obj.toString().split(",")[3].equals(car.getSizeTire()) &&
                    obj.toString().split(",")[4].equals(type)) {
                System.out.println(obj.toString());
            }
        }
    }
    public void filterBySizeTireAndTypeAndSeason(Car car, List<Object> list, String type, String season) {
        for(Object obj: list) {
            if(obj.toString().split(",")[3].equals(car.getSizeTire()) &&
                    obj.toString().split(",")[5].equals(season) &&
                    obj.toString().split(",")[4].equals(type)) {
                System.out.println(obj.toString());
            }
        }
    }
    public String buyTire(List<Object> list, String o, int nr, String price, String priceDisc) {
        double pricee = 0, priceDiscc=0;
        for(Object obj: list) {
            if(obj.toString().split(",")[0].equals(o)) {
                if(Integer.parseInt(obj.toString().split(",")[8])<nr)
                    return "Out of stock";
                else
                {
                    pricee=Double.parseDouble(obj.toString().split(",")[6])*nr;
                    price = String.valueOf(pricee);
                    priceDiscc=pricee-Double.parseDouble(obj.toString().split(",")[7])*pricee/100;
                    priceDisc = String.valueOf(priceDiscc);
                    return price +"-"+ priceDisc;
                }
            }
        }
        return "";
    }
    public void buy(String id, int stock) throws Exception {
        this.service.changeFilename("src/files/Tire.csv");
        List<Object> list = new ArrayList<>(this.service.showElement("tire"));
        for(Object obj: list) {
            String[] parts =obj.toString().split(",");
            if(parts[0].equals(id)) {
                Tire tire = new Tire(Integer.parseInt(parts[0]),
                        parts[1], parts[2],
                        parts[3], parts[4], parts[5],
                        Double.parseDouble(parts[6]), Integer.parseInt(parts[8])-stock);
                List<Object> l = new ArrayList<>();
                l.add(tire);
                this.service.updateElement("tire",l); // update stock
                System.out.println("The tires "+parts[1]+" "+parts[2]+" "+parts[3]+" purchased!");
            }
        }
    }

    void menu() {
        System.out.println("_______________");
        System.out.println("1. Dealer");
        System.out.println("2. Car");
        System.out.println("3. Tire");
        System.out.println("4. Buy Tires");
        System.out.println("5. Exit");
        System.out.println("_______________");
    }

    void menuCrud() {
        System.out.println("_____________");
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Show");
        System.out.println("5. Back");
        System.out.println("_____________");
    }

    public void run() throws Exception {
        menu();
        System.out.print("Option: ");
        Scanner scanner = new Scanner(System.in);
        int option=scanner.nextInt();
        while(option!=5){
            switch (option) {
                case 1 -> {
                    menuCrud();
                    System.out.print("Option: ");
                    int option1 = scanner.nextInt();
                    while (option1 != 5) {
                        switch (option1) {
                            case 1 -> addDealer();
                            case 2 -> updateDealer();
                            case 3 -> deleteDealer();
                            case 4 -> showDealer();
                        }
                        menuCrud();
                        System.out.println("Option: ");
                        option1 = scanner.nextInt();
                    }
                    menu();
                    System.out.print("Option: ");
                    option = scanner.nextInt();
                }
                case 2 -> {
                    menuCrud();
                    System.out.print("Option: ");
                    int option1 = scanner.nextInt();
                    while (option1 != 5) {
                        switch (option1) {
                            case 1 -> addCar();
                            case 2 -> updateCar();
                            case 3 -> deleteCar();
                            case 4 -> showCar();
                        }
                            menuCrud();
                            System.out.println("Option: ");
                            option1 = scanner.nextInt();
                        }
                        menu();
                        System.out.print("Option: ");
                        option = scanner.nextInt();
                    }
                case 3 -> {
                    menuCrud();
                    System.out.print("Option: ");
                    int option1 = scanner.nextInt();
                    while (option1 != 5) {
                        switch (option1) {
                            case 1 -> addTire();
                            case 2 -> updateTire();
                            case 3 -> deleteTire();
                            case 4 -> showTire();
                            }
                        menuCrud();
                        System.out.println("Option: ");
                        option1 = scanner.nextInt();
                    }
                    menu();
                    System.out.print("Option: ");
                    option = scanner.nextInt();
                }
                case 4 -> {
                    buyTires();
                    menu();
                    System.out.print("Option: ");
                    option = scanner.nextInt();
                }
                default -> {
                    menu();
                    System.out.print("Option: ");
                    option = scanner.nextInt();
                }
            }
        }
        System.out.println("EXIT PROGRAM");
    }
}
