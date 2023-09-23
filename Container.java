import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Container {
    private int containerID;
    private double weight;
    private static int containerNum = 0;
    protected double fuelPerKmShip;
    protected double fuelPerKmTruck;
    public Container() {
        this.containerID = getLastContainerID() + 1;
        this.weight = 0;
    }

    public Container(double weight) {
        this.containerID = getLastContainerID()+1;
        this.weight = weight;
    }

    public static int getLastContainerID(){
        String fileName = "containerData.txt"; // Specify the file name

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String lastContainerID = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Container ID: c-")) {
                    lastContainerID = line.substring("Container ID: c-".length());
                }
            }
            if (lastContainerID != null) {
                return Integer.parseInt(lastContainerID);
            } else {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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
        this.fuelPerKmShip = 4.8;
        this.fuelPerKmTruck = 5.3;
    }

    public Liquid(double weight) {
        super(weight);
        this.fuelPerKmShip = 4.8;
        this.fuelPerKmTruck = 5.3;
    }

    @Override
    public double getFuelPerKmShip() {
        return this.fuelPerKmShip;
    }

    @Override
    public void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    public double getFuelPerKmTruck() {
        return this.fuelPerKmTruck;
    }

    @Override
    public void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class Refrigerated extends Container {
    public Refrigerated() {
        super();
        this.fuelPerKmShip = 4.5;
        this.fuelPerKmTruck = 5.4;
    }

    public Refrigerated(double weight) {
        super(weight);
        this.fuelPerKmShip = 4.5;
        this.fuelPerKmTruck = 5.4;
    }

    @Override
    protected double getFuelPerKmShip() {
        return this.fuelPerKmShip;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return this.fuelPerKmTruck;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openSide extends Container {
    public openSide() {
        super();
        this.fuelPerKmShip = 2.7;
        this.fuelPerKmTruck = 3.2;
    }

    public openSide(double weight) {
        super(weight);
        this.fuelPerKmShip = 2.7;
        this.fuelPerKmTruck = 3.2;
    }

    @Override
    protected double getFuelPerKmShip() {
        return this.fuelPerKmShip;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return this.fuelPerKmTruck;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class openTop extends Container {
    public openTop() {
        super();
        this.fuelPerKmShip = 4.8;
        this.fuelPerKmTruck = 5.3;
    }

    public openTop(double weight) {
        super(weight);
        this.fuelPerKmShip = 4.8;
        this.fuelPerKmTruck = 5.3;
    }

    @Override
    protected double getFuelPerKmShip() {
        return this.fuelPerKmShip;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return this.fuelPerKmTruck;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}

class dryStorage extends Container {
    public dryStorage() {
        super();
        this.fuelPerKmShip = 3.5;
        this.fuelPerKmTruck = 4.6;
    }

    public dryStorage(double weight) {
        super(weight);
        this.fuelPerKmShip = 3.5;
        this.fuelPerKmTruck = 4.6;
    }

    @Override
    protected double getFuelPerKmShip() {
        return fuelPerKmShip;
    }

    @Override
    protected void setFuelPerKmShip(double fuelPerKmShip) {}

    @Override
    protected double getFuelPerKmTruck() {
        return fuelPerKmTruck;
    }

    @Override
    protected void setFuelPerKmTruck(double fuelPerKmTruck) {}
}


