import java.util.ArrayList;

public class Port {
    private int portID;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;
    private boolean landingAbility;
    private PortManager portManager;
    private ArrayList<Container> containers;
    private ArrayList<Vehicles> vehicles;
    private ArrayList<Trip> trafficHistory;

    public Port() {}

    public Port(int portID, String name, double latitude, double longititude, double storingCapacity, boolean landingAbility, PortManager portManager, ArrayList<Container> containers, ArrayList<Vehicles> vehicles, ArrayList<Trip> trafficHistory) {
        this.portID = portID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longititude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.portManager = portManager;
        this.containers = containers;
        this.vehicles = vehicles;
        this.trafficHistory = trafficHistory;
    }
    public double calDistance(Port port) {
        return 0;
    }
}

