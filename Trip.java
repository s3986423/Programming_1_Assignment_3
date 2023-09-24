import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Trip {
    private Vehicles vehicles;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private String Status;

    public Trip() {}

    public Trip(Vehicles vehicles, LocalDate departureDate, LocalDate arrivalDate, Port departurePort, Port arrivalPort, String status, SystemAdmin admin) {
        this.vehicles = vehicles;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        this.Status = status;
        vehicles.getTrip().add(this);
        departurePort.getTrafficHistory().add(this);
        arrivalPort.getTrafficHistory().add(this);
        admin.getTripList().add(this);
        writeTripToFile(this, "tripData.txt");
    }
    public void writeTripToFile(Trip trip, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            // Write container information to the file
            writer.write("Departure Date: " + trip.departureDate);
            writer.newLine();
            writer.write("Arrival Date: " + trip.arrivalDate);
            writer.newLine();
            writer.write("Departure Port: " + trip.getDeparturePort().getName());
            writer.newLine();
            writer.write("Arrival Port: " + trip.getArrivalPort().getName());
            writer.newLine();
            writer.write("Status: " + trip.getStatus());
            writer.newLine();
            writer.write("-----------------------------------");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected Vehicles getVehicles() {
        return vehicles;
    }

    protected void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    protected LocalDate getDepartureDate() {
        return departureDate;
    }

    protected void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    protected LocalDate getArrivalDate() {
        return arrivalDate;
    }

    protected void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    protected Port getDeparturePort() {
        return departurePort;
    }

    protected void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    protected Port getArrivalPort() {
        return arrivalPort;
    }

    protected void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    protected String getStatus() {
        return Status;
    }

    protected void setStatus(String status) {
        Status = status;
    }

    public double getFuelConsumption() {
        double fuelPerKm = 0;
        String vehicleType = this.vehicles.getClass().getSimpleName();
        if (vehicleType.equalsIgnoreCase("Ship")) {
            for (Container container : this.vehicles.getNumContainer()) {
                fuelPerKm += container.getFuelPerKmShip();
            }
        } else if (vehicleType.equalsIgnoreCase("basicTruck") || vehicleType.equalsIgnoreCase("reeferTruck") || vehicleType.equalsIgnoreCase("tankerTruck")){
            for (Container container : this.vehicles.getNumContainer()) {
                fuelPerKm += container.getFuelPerKmTruck();
            }
        }
        // Calculate the distance between the current port and the destination port
        double distance = this.getDeparturePort().calDistance(this.getArrivalPort());

        // Calculate fuel consumption based on the container type and the fuel consumption rate
        return fuelPerKm * distance;
    }

}
