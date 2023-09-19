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

    public Vehicles() {}

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
    protected int getVehicleID() {
        return VehicleID;
    }

    protected void setVehicleID(int vehicleID) {
        VehicleID = vehicleID;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected double getCurrentFuel() {
        return currentFuel;
    }

    protected void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    protected double getCarryingCapacity() {
        return carryingCapacity;
    }

    protected void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    protected double getFuelCapacity() {
        return fuelCapacity;
    }

    protected void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    protected Port getCurrentPort() {
        return currentPort;
    }

    protected void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    protected ArrayList<Container> getNumContainer() {
        return numContainer;
    }

    protected void setNumContainer(ArrayList<Container> numContainer) {
        this.numContainer = numContainer;
    }

    protected ArrayList<Trip> getTrip() {
        return trip;
    }

    protected void setTrip(ArrayList<Trip> trip) {
        this.trip = trip;
    }

    public abstract void load(Container container);
    public abstract void unload(Container container);
    public abstract boolean calMove(Port port);
    public abstract void Move(Port port);
    public void refuel() {
        setCurrentFuel(this.fuelCapacity);
    }
}
class Ship extends Vehicles{

    public Ship() {}

    public Ship(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, numContainer, trip);
    }

    // Load(Container) (Ship)
    public void Load(Container container) {
        if (this.getCurrentPort().getContainers().contains(container)){
            this.getCurrentPort().getContainers().remove(this.getCurrentPort().getContainers().indexOf(container));
            this.getNumContainer().add(container);
        }else {
            System.out.println("That container does not exist in the port");
        }
    }

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
    public Truck() {
    }

    public Truck(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, numContainer, trip);
    }

    @Override
    public boolean calMove(Port port) {
        return true;
    }
    @Override
    public void Move(Port port) {

    }
}

class basicTruck extends Truck {
    public basicTruck() {}
    public basicTruck(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, numContainer, trip);
    }
    @Override
    public void load(Container container) {
        if (container instanceof Refrigerated || container instanceof Liquid) {
            System.out.println("This container can not be load on this truck");
        }else if (this.getCurrentPort().getContainers().contains(container)){
            this.getCurrentPort().getContainers().remove(this.getCurrentPort().getContainers().indexOf(container));
            this.getNumContainer().add(container);
        }else {
            System.out.println("That container does not exist in the port");
        }
    }
    @Override
    public void unload(Container container) {

    }
}

class reeferTruck extends Truck {
    public reeferTruck() {}
    public reeferTruck(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, numContainer, trip);
    }
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
}

class tankerTruck extends Truck {
    public tankerTruck() {}
    public tankerTruck(int vehicleID, String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, ArrayList<Container> numContainer, ArrayList<Trip> trip) {
        super(vehicleID, name, currentFuel, carryingCapacity, fuelCapacity, currentPort, numContainer, trip);
    }
    @Override
    public void load(Container container) {

    }
    @Override
    public void unload(Container container) {

    }
}