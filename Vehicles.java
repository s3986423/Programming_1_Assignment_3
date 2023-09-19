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
}
