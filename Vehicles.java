import java.util.ArrayList;

public abstract class Vehicles {
    private int VehicleID;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private Port currentPort;
    private ArrayList<Container> numContainer;
    private ArrayList<Trip> trip;

    public Vehicles() {
    }

    public Vehicles(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        VehicleID = vehicleID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        this.numContainer = numContainer;
        this.trip = trip;
    }

    public abstract void load(Container container);
    public abstract void unload(Container container);
    public abstract boolean calMove(Port port);
    public abstract void Move(Port port);
    public void refuel() {

    }
}
class Ship extends Vehicles{
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
    @Override
    public boolean calMove(Port port) {
        return true;
    }
    @Override
    public void Move(Port port) {

    }
}

abstract class Truck extends Vehicles {
    @Override
    public boolean calMove(Port port) {
        return true;
    }
    @Override
    public void Move(Port port) {

    }
}

class basicTruck extends Truck {
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
}

class reeferTruck extends Truck {
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
}

class tankerTruck extends Truck {
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
}