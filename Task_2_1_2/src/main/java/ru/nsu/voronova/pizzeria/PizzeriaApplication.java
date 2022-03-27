package ru.nsu.voronova.pizzeria;

import ru.nsu.voronova.json.BakerJSON;
import ru.nsu.voronova.json.CourierJSON;
import ru.nsu.voronova.json.JSONReader;
import ru.nsu.voronova.json.PizzeriaJSON;

public class PizzeriaApplication implements Runnable {
    private final static long RUNNING_TIME = 30 * 1000;
    private PizzeriaJSON pizzeriaJSON;
    private Pizzeria pizzeria;

    private void setPizzeriaJSON() {
        JSONReader jsonReader = new JSONReader();
        jsonReader.open();
        pizzeriaJSON = jsonReader.read();
        jsonReader.close();
    }

    private void setPizzeria() {
        if (pizzeriaJSON == null) {
            System.err.println("Missing pizzeria configuration.");
            return;
        }
        if (pizzeriaJSON.queueSize() <= 0) {
            System.err.println("Queue size must be a positive number.");
            return;
        }
        if (pizzeriaJSON.storageSize() <= 0) {
            System.err.println("Storage size must be a positive number.");
            return;
        }
        BakerJSON[] bakersJSON = pizzeriaJSON.bakers();
        if (bakersJSON == null || bakersJSON.length == 0) {
            System.err.println("A pizzeria cannot operate without bakers. Please add bakers to the pizzeria configuration.");
            return;
        }
        CourierJSON[] couriersJSON = pizzeriaJSON.couriers();
        if (couriersJSON == null || couriersJSON.length == 0) {
            System.err.println("A pizzeria cannot operate without couriers. Please add couriers to the pizzeria configuration.");
            return;
        }
        pizzeria = new Pizzeria(pizzeriaJSON);
    }

    public PizzeriaApplication() {
        setPizzeriaJSON();
        setPizzeria();
    }

    @Override
    public void run() {
        if (pizzeria == null) {
            System.err.println("Failed to start pizzeria application.");
            System.exit(1);
        }
        Thread pizzeriaThread = new Thread(pizzeria);
        pizzeriaThread.start();
        try {
            Thread.sleep(RUNNING_TIME);
            pizzeria.stop();
            System.exit(0);
        } catch (InterruptedException exception) {
            System.err.println("The pizzeria application ended with an error.");
            System.exit(2);
        }
    }
}
