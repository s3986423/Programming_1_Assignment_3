import java.util.ArrayList;

public class Port {
    private int portID; //The ID of the port
    private String name; //Name of the port
    private double latitude; //The latitude coordinate of the port
    private double longitude; //the longitude coordinate of the port
    private double storingCapacity; //Storing capacity of the port
    private boolean landingAbility; //the Landing ability of the port
    private PortManager portManager; //Port manager who take control of the port
    private ArrayList<Container> containers; //List of containers at the port
    private ArrayList<Vehicles> vehicles; //List of vehicles at the port
    private ArrayList<Trip> trafficHistory; //History of trips

    public Port() {}

    public int getPortID() {
        return portID;
    }

    public void setPortID(int portID) {
        this.portID = portID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public PortManager getPortManager() {
        return portManager;
    }

    public void setPortManager(PortManager portManager) {
        this.portManager = portManager;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }

    public ArrayList<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public ArrayList<Trip> getTrafficHistory() {
        return trafficHistory;
    }

    public void setTrafficHistory(ArrayList<Trip> trafficHistory) {
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
