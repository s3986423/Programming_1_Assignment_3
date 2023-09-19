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
    private ArrayList <Vehicles> vehicles;
    private ArrayList<Trip> trafficHistory;

    // Constructors (including overloaded constructors

    public Port(int portID, String name, double latitude, double longitude, double storingCapacity, boolean landingAbility) {
        this.portID = portID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.portManager = null; // Initialize to null by default
        this.containers = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.trafficHistory = new ArrayList<>();
    }

    // Getter and setter methods
    protected int getPortID() {
        return portID;
    }

    protected void setPortID(int portID) {
        this.portID = portID;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected double getLatitude() {
        return latitude;
    }

    protected void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    protected double getLongitude() {
        return longitude;
    }

    protected void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    protected double getStoringCapacity() {
        return storingCapacity;
    }

    protected void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    protected boolean isLandingAbility() {
        return landingAbility;
    }

    protected void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    protected PortManager getPortManager() {
        return portManager;
    }

    protected void setPortManager(PortManager portManager) {
        this.portManager = portManager;
    }

    protected ArrayList<Container> getContainers() {
        return containers;
    }

    protected void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    protected ArrayList<Vehicles> getVehicles() {
        return vehicles;
    }

    protected void setVehicles(ArrayList<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    protected ArrayList<Trip> getTrafficHistory() {
        return trafficHistory;
    }

    protected void setTrafficHistory(ArrayList<Trip> trafficHistory) {
        this.trafficHistory = trafficHistory;
    }

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
    public double calCurrentCapacity() {
        double currentCapacity = 0;
        for (Container container : this.getContainers()) {
            currentCapacity += container.getWeight();
        }
        return currentCapacity;
    }
    public double calDistance(Port port) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double lat1Rad = Math.toRadians(this.latitude);
        double lon1Rad = Math.toRadians(this.longitude);
        double lat2Rad = Math.toRadians(port.getLatitude());
        double lon2Rad = Math.toRadians(port.getLongitude());

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c; // Distance in kilometers
        return distance;
    }
}
