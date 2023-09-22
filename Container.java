public abstract class Container {
    private int containerID;
    private double weight;
    private static int containerNum = 0;
    private double fuelPerKmShip;
    private double fuelPerKmTruck;
    public Container() {
        this.containerID = containerNum;
        containerNum++;
        this.weight = 0;
    }

    public Container(double weight) {
        this.containerID = containerNum;
        containerNum++;
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



    protected abstract double getFuelPerKmShip();

    protected abstract void setFuelPerKmShip(double fuelPerKmShip);

    protected abstract double getFuelPerKmTruck();

    protected abstract void setFuelPerKmTruck(double fuelPerKmTruck);

}

class Liquid extends Container {
    public Liquid() {
        super();
    }

    public Liquid(double weight) {
        super(weight);
    }

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
    public Refrigerated() {
        super();
    }

    public Refrigerated(double weight) {
        super(weight);

    }

    @Override
    protected double getFuelPerKmShip() {
        return 4.5;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return 5.4;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openSide extends Container {
    public openSide() {
        super();

    }

    public openSide(double weight) {
        super(weight);
    }

    @Override
    protected double getFuelPerKmShip() {
        return 2.7;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return 3.2;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openTop extends Container {
    public openTop() {
        super();
    }

    public openTop(double weight) {
        super(weight);
    }

    @Override
    protected double getFuelPerKmShip() {
        return 4.8;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return 5.3;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class dryStorage extends Container {
    public dryStorage() {
        super();
    }

    public dryStorage(double weight) {
        super(weight);
    }

    @Override
    protected double getFuelPerKmShip() {
        return 3.5;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return 4.6;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}


