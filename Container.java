public abstract class Container {
    private int containerID;
    private double weight;

    public Container() {
        this.containerID = 0;
        this.weight = 0;
    }

    public Container(int containerID, double weight) {
        this.containerID = containerID;
        this.weight = weight;
    }
}

class Liquid extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public Liquid() {
        this.fuelPerKmShip = 4.8;
        this.fuelPerKmTruck = 5.3;
    }
}

class Refrigerated extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public Refrigerated() {
        this.fuelPerKmShip = 4.5;
        this.fuelPerKmTruck = 5.4;
    }
}

class openSide extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public openSide() {
        this.fuelPerKmShip = 2.7;
        this.fuelPerKmTruck = 3.2;
    }
}

class openTop extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public openTop() {
        this.fuelPerKmShip = 2.8;
        this.fuelPerKmTruck = 3.2;
    }
}

class dryStorage extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public dryStorage() {
        this.fuelPerKmShip = 3.5;
        this.fuelPerKmTruck = 4.6;
    }
}


