public abstract class Container {
    private int containerID;
    private double weight;
    private double fuelPerKmShip;
    private double fuelPerKmTruck;
    public Container() {
        this.containerID = 0;
        this.weight = 0;
    }

    public Container(int containerID, double weight) {
        this.containerID = containerID;
        this.weight = weight;
    }

    protected int getContainerID() {
        return containerID;
    }

    protected void setContainerID(int containerID) {
        this.containerID = containerID;
    }

    protected double getWeight() {
        return weight;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract double getFuelPerKmShip();

    public abstract void setFuelPerKmShip(double fuelPerKmShip);

    public abstract double getFuelPerKmTruck();

    public abstract void setFuelPerKmTruck(double fuelPerKmTruck);

    public static void main(String[] args) {
        Container c = new Liquid();
    }
}

class Liquid extends Container {
    @Override
    public double getFuelPerKmShip() {
        return 4.8;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return 5.3;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class Refrigerated extends Container {
    @Override
    public double getFuelPerKmShip() {
        return 4.5;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return 5.4;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openSide extends Container {
    @Override
    public double getFuelPerKmShip() {
        return 2.7;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return 3.2;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openTop extends Container {
    @Override
    public double getFuelPerKmShip() {
        return 4.8;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return 5.3;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class dryStorage extends Container {
    @Override
    public double getFuelPerKmShip() {
        return 3.5;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return 4.6;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}


