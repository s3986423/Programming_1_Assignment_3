import java.time.LocalDate;

public class Trip {
    private Vehicles vehicles;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private String Status;

    public Trip() {}

    public Trip(Vehicles vehicles, LocalDate departureDate, LocalDate arrivalDate, Port departurePort, Port arrivalPort, String status) {
        this.vehicles = vehicles;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        Status = status;
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
}
