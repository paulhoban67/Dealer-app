package com.company;


import repository.Repository;
import service.Service;
import ui.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        Repository repository = new Repository();
        Service service = new Service(repository);
        Ui ui = new Ui(service);
        ui.run();
    }
}
