import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.time.LocalDate;

public abstract class Vehicles {
    private int VehicleID;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private Port currentPort;
    private static int vehicleNum = 0;
    private ArrayList<Container> numContainer;
    private ArrayList<Trip> trip;

    private SystemAdmin admin;

    public Vehicles() {}

    public Vehicles(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        VehicleID = vehicleNum;
        vehicleNum++;
        this.name = name;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        currentPort.getVehicles().add(this);
        this.numContainer = new ArrayList<>();
        this.trip = new ArrayList<>();
        this.admin = admin;
        admin.getVehiclesList().add(this);
    }

    protected int getVehicleID() {
        return VehicleID;
    }

    protected void setVehicleID(int vehicleID) {
        VehicleID = vehicleID;
    }

    protected abstract String getIDprefix();

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

    public void load(Container container) {
        if (this.calCurrentCapacity() + container.getWeight() > this.getCarryingCapacity()) {
            System.out.println("The vehicle does not have enough capacity for this container");
        } else if (this.getCurrentPort() == null) {
            System.out.println("The vehicle is moving and can not load the container");
        } else if (!this.getCurrentPort().getContainers().contains(container)) {
            System.out.println("The container does not exist in the port");
        } else {
            this.getCurrentPort().getContainers().remove(container);
            this.getNumContainer().add(container);
        }
    }

    public void unload(Container container) {
        if (this.getCurrentPort().calCurrentCapacity() + container.getWeight() > this.getCurrentPort().getStoringCapacity()) {
            System.out.println("The port does not have enough capacity for this container");
        } else if (this.getCurrentPort() == null) {
            System.out.println("The vehicle is moving and cannot unload the container");
        } else if (!this.getNumContainer().contains(container)) {
            System.out.println("The container does not exist on the vehicles");
        } else {
            this.getCurrentPort().getContainers().add(container);
            this.getNumContainer().remove(container);
        }
    }

    public abstract boolean calMove(Port port);

    public abstract void Move(Port arrivalPort, LocalDate arrivalDate);

    public abstract void Arrived();

    public double calCurrentCapacity() {
        double currentCapacity = 0;
        for (Container container : this.numContainer) {
            currentCapacity += container.getWeight();
        }
        return currentCapacity;
    }

    public void refuel() {
        setCurrentFuel(this.fuelCapacity);
    }

}
class Ship extends Vehicles {

    public Ship() {}

    public Ship(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        super(name, currentFuel, carryingCapacity, fuelCapacity, currentPort, admin);
    }

    @Override
    protected String getIDprefix() {
        return "sh-";
    }

    @Override
    public boolean calMove(Port port) {
        double distance = this.getCurrentPort().calDistance(port);
        double fuelPerKm = 0;
        for (Container container : this.getNumContainer()) {
            fuelPerKm += container.getFuelPerKmShip();
        }
        if (fuelPerKm * distance > this.getCurrentFuel()){
            return false;
        }
        return true;
    }
    @Override
    public void Move(Port arrivalPort, LocalDate arrivalDate) {
        if (!calMove(arrivalPort)) {
            System.out.println("This vehicle cannot move");
        } else {
            Trip trip = new Trip(this, LocalDate.now(), arrivalDate, this.getCurrentPort(), arrivalPort, "moving");
        }
    }

    @Override
    public void Arrived() {
        Trip trip = this.getTrip().get(this.getTrip().size()-1);
        trip.setStatus("Completed");
        this.setCurrentPort(trip.getArrivalPort());

        double distance = this.getCurrentPort().calDistance(trip.getDeparturePort());
        double fuelPerKm = 0;
        for (Container container : this.getNumContainer()) {
            fuelPerKm += container.getFuelPerKmShip();
        }
        this.setCurrentFuel(this.getCurrentFuel() - (distance * fuelPerKm));
    }
}

abstract class Truck extends Vehicles {
    public Truck() {
    }

    public Truck(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        super(name, currentFuel, carryingCapacity, fuelCapacity, currentPort, admin);
    }

    @Override
    protected String getIDprefix() {
        return "tr-";
    }

    @Override
    public boolean calMove(Port port) {
        if (this.getCurrentPort() == null) {
            return false;
        }
        else if (this.getCurrentPort().getLandingAbility() == false || port.getLandingAbility() == false) {
            return false;
        }
        double distance = this.getCurrentPort().calDistance(port);
        double fuelPerKm = 0;
        for (Container container : this.getNumContainer()) {
            fuelPerKm += container.getFuelPerKmTruck();
        }
        if (fuelPerKm * distance > this.getCurrentFuel()){
            return false;
        }
        return true;
    }
    @Override
    public void Move(Port arrivalPort, LocalDate arrivalDate) {
        if (!calMove(arrivalPort)) {
            System.out.println("This vehicle cannot move");
        } else {
            Trip trip = new Trip(this, LocalDate.now(), arrivalDate, this.getCurrentPort(), arrivalPort, "Moving");
        }
    }

    @Override
    public void Arrived() {
        Trip trip = this.getTrip().get(this.getTrip().size()-1);
        trip.setStatus("Completed");
        this.setCurrentPort(trip.getArrivalPort());
        double distance = this.getCurrentPort().calDistance(trip.getDeparturePort());
        double fuelPerKm = 0;
        for (Container container : this.getNumContainer()) {
            fuelPerKm += container.getFuelPerKmTruck();
        }
        this.setCurrentFuel(this.getCurrentFuel() - (distance * fuelPerKm));
    }
}

class basicTruck extends Truck {
    public basicTruck() {}
    public basicTruck(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        super(name, currentFuel, carryingCapacity, fuelCapacity, currentPort, admin);
    }

    @Override
    public void load(Container container) {
        if (container instanceof Refrigerated || container instanceof Liquid) {
            System.out.println("The container can not be load on this truck");
        }else super.load(container);
    }
}

class reeferTruck extends Truck {
    public reeferTruck() {}
    public reeferTruck(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        super(name, currentFuel, carryingCapacity, fuelCapacity, currentPort, admin);
    }
    @Override
    public void load(Container container) {
        if (container instanceof Refrigerated) {
            super.load(container);
        }else {
            System.out.println("The container can not be load on this truck");
        }
    }
}

class tankerTruck extends Truck {
    public tankerTruck() {}
    public tankerTruck(String name, double currentFuel, double carryingCapacity, double fuelCapacity, Port currentPort, SystemAdmin admin) {
        super(name, currentFuel, carryingCapacity, fuelCapacity, currentPort, admin);
    }
    @Override
    public void load(Container container) {
        if (container instanceof Liquid) {
            super.load(container);
        }else {
            System.out.println("The container can not be load on this truck");
        }
    }
}