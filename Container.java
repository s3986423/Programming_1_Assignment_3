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

    public int getContainerID() {
        return containerID;
    }

    public void setContainerID(int containerID) {
        this.containerID = containerID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    public static double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    public static void setFuelPerKmShip(double fuelPerKmShip) {
        Liquid.fuelPerKmShip = fuelPerKmShip;
    }

    public static double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    public static void setFuelPerKmTruck(double fuelPerKmTruck) {
        Liquid.fuelPerKmTruck = fuelPerKmTruck;
    }
}

class Refrigerated extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public Refrigerated() {
        this.fuelPerKmShip = 4.5;
        this.fuelPerKmTruck = 5.4;
    }

    public static double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    public static void setFuelPerKmShip(double fuelPerKmShip) {
        Refrigerated.fuelPerKmShip = fuelPerKmShip;
    }

    public static double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    public static void setFuelPerKmTruck(double fuelPerKmTruck) {
        Refrigerated.fuelPerKmTruck = fuelPerKmTruck;
    }
}

class openSide extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public openSide() {
        this.fuelPerKmShip = 2.7;
        this.fuelPerKmTruck = 3.2;
    }

    public static double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    public static void setFuelPerKmShip(double fuelPerKmShip) {
        openSide.fuelPerKmShip = fuelPerKmShip;
    }

    public static double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    public static void setFuelPerKmTruck(double fuelPerKmTruck) {
        openSide.fuelPerKmTruck = fuelPerKmTruck;
    }
}

class openTop extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public openTop() {
        this.fuelPerKmShip = 2.8;
        this.fuelPerKmTruck = 3.2;
    }

    public static double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    public static void setFuelPerKmShip(double fuelPerKmShip) {
        openTop.fuelPerKmShip = fuelPerKmShip;
    }

    public static double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    public static void setFuelPerKmTruck(double fuelPerKmTruck) {
        openTop.fuelPerKmTruck = fuelPerKmTruck;
    }
}

class dryStorage extends Container {
    private static double fuelPerKmShip;
    private static double fuelPerKmTruck;

    public dryStorage() {
        this.fuelPerKmShip = 3.5;
        this.fuelPerKmTruck = 4.6;
    }

    public static double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    public static void setFuelPerKmShip(double fuelPerKmShip) {
        dryStorage.fuelPerKmShip = fuelPerKmShip;
    }

    public static double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    public static void setFuelPerKmTruck(double fuelPerKmTruck) {
        dryStorage.fuelPerKmTruck = fuelPerKmTruck;
    }
}


