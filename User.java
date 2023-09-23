import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void createContainerAtPort(Port port) {
        Scanner scanner = new Scanner(System.in);

        // Input the container weight
        System.out.println("Please input the container weight: ");
        double containerWeight = scanner.nextDouble();

        // Check if the port has enough storing capacity for the container
        if (port.calCurrentCapacity() + containerWeight > port.getStoringCapacity()) {
            System.out.println("The port does not have enough capacity for the container.");
            return; // Exit the method without creating the container
        }

        // Display container type options to the user
        System.out.println("Select the container type:");
        System.out.println("'1' for Liquid");
        System.out.println("'2' for Refrigerated");
        System.out.println("'3' for Open Side");
        System.out.println("'4' for Open Top");
        System.out.println("'5' for Dry Storage");

        int containerTypeChoice = scanner.nextInt();
        Container container = null; // Initialize the container variable

        switch (containerTypeChoice) {
            case 1:
                container = new Liquid(containerWeight);
                break;
            case 2:
                container = new Refrigerated(containerWeight);
                break;
            case 3:
                container = new openSide(containerWeight);
                break;
            case 4:
                container = new openTop(containerWeight);
                break;
            case 5:
                container = new dryStorage(containerWeight);
                break;
            default:
                System.out.println("You did not enter a valid value");
                break;
        }

        if (container != null) {
            // Now, add the created container directly to the port's list
            port.getContainers().add(container);

            // Optionally, you can print a confirmation message
            System.out.println("Container added to the port.");
        }

        // Write container data to the file
        String filePath = "containerData.txt";
        writeContainerToFile(container, filePath);
    }


    // Method to write container data to a file
    public void writeContainerToFile(Container container, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            // Write container information to the file
            writer.write("Container ID: c-" + container.getContainerID());
            writer.newLine();
            writer.write("Container Type: " + container.getClass().getSimpleName());
            writer.newLine();
            writer.write("Container Weight: " + container.getWeight());
            writer.newLine();
            writer.write("Fuel Per Km (Ship): " + container.getFuelPerKmShip());
            writer.newLine();
            writer.write("Fuel Per Km (Truck): " + container.getFuelPerKmTruck());
            writer.newLine();
            writer.write("-----------------------------------");
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static void readAndPrintContainerData(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    // Print each line from the file
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }



        }






    public void readContainerAtPort(Port port){
        ArrayList<Container> containersAtPort = port.getContainers();

        if (containersAtPort.isEmpty()) {
            System.out.println("No containers at the port.");
        } else {
            System.out.println("Containers at the port:");
            for (Container container : containersAtPort) {
                System.out.println("Container ID: c-" + container.getContainerID());
                System.out.println("Container type:" +container.getClass().getSimpleName());
                System.out.println("Container Weight: " + container.getWeight());
                System.out.println("Fuel Per Km (Ship): " + container.getFuelPerKmShip());
                System.out.println("Fuel Per Km (Truck): " + container.getFuelPerKmTruck());
                System.out.println("-----------------------------------");
            }
        }
    }
    public void updateContainerAtPort(Port port) {
        Scanner update = new Scanner(System.in);
        ArrayList<Container> containersAtPort = port.getContainers();

        if (containersAtPort.isEmpty()) {
            System.out.println("No container at port.");
        } else {
            System.out.println("Enter the ID of the container you want to update: c-");
            int containerIDToUpdate = update.nextInt();
            boolean containerFound = false;

            for (Container container : containersAtPort) {
                if (container.getContainerID() == containerIDToUpdate) {
                    // Container found, update its weight
                    System.out.println("Enter the new weight for the container: ");
                    double newWeight = update.nextDouble();
                    container.setWeight(newWeight);
                    containerFound = true;
                    System.out.println("Container updated successfully.");
                    break;
                }
            }

            if (!containerFound) {
                System.out.println("Container with ID c-" + containerIDToUpdate + " not found.");
            }
        }

    }
    public void deleteContainerAtPort(Port port) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Container> containersAtPort = port.getContainers();

        if (containersAtPort.isEmpty()) {
            System.out.println("No containers at the port.");
        } else {
            System.out.println("Enter the ID of the container you want to delete: c-");
            int containerIDToDelete = scanner.nextInt();
            boolean containerFound = false;

            for (Container container : containersAtPort) {
                if (container.getContainerID() == containerIDToDelete) {
                    // Container found, remove it from the list
                    containersAtPort.remove(container);
                    containerFound = true;
                    System.out.println("Container deleted successfully.");
                    break;
                }
            }

            if (!containerFound) {
                System.out.println("Container with ID c-" + containerIDToDelete + " not found.");
            }
        }
    }
}
    interface CRUD{
    void Create();
    void Read();
    void Update();
    void Delete();
    }
    class PortManager extends User implements CRUD {
        private Port assignedPort; // Reference to the port managed by this manager
        private String username; // Add username field
        private SystemAdmin admin;
        public PortManager(String username, String password, Port assignedPort, SystemAdmin admin) {
            super(username, password);
            this.username = username; // Set the username
            this.assignedPort = assignedPort;
            assignedPort.setPortManager(this);
            this.admin = admin;
            this.admin.getManagersList().add(this);
        }

        protected String getUsername() {
            return username;
        }

        protected void setUsername(String username) {
            this.username = username;
        }

        protected Port getAssignedPort() {
            return assignedPort;
        }

        @Override
        public void Create(){
            createContainerAtPort(this.assignedPort);
        }
        @Override
        public void Read() {
            readContainerAtPort(this.assignedPort);
        }
        @Override
        public void Update() {
            updateContainerAtPort(this.assignedPort);
        }
        @Override
        public void Delete() {
            deleteContainerAtPort(this.assignedPort);
        }
    }
    class SystemAdmin extends User implements CRUD {
        private ArrayList<Port> portList;
        private ArrayList<Vehicles> vehiclesList;
        private ArrayList<PortManager> managersList;

        public SystemAdmin(String username, String password){
            super(username, password);
            this.portList = new ArrayList<>();
            this.managersList = new ArrayList<>();
            this.vehiclesList = new ArrayList<>();
        }

        protected ArrayList<Vehicles> getVehiclesList() {
            return vehiclesList;
        }

        protected ArrayList<PortManager> getManagersList() {
            return managersList;
        }

        protected ArrayList<Port> getPortList() {
            return portList;
        }

        private void createVehicleAtPort (Port port) {
            Scanner createVehicle = new Scanner(System.in);
            System.out.println("Please input the name of your vehicle: ");
            String vehicleName = createVehicle.next();
            System.out.println("---");
            System.out.println("Please input your vehicle carrying capacity: ");
            double carryingCapacity = createVehicle.nextDouble();
            System.out.println("---");
            System.out.println("Please input your vehicle fuel capacity: ");
            double fuelCapacity = createVehicle.nextDouble();
            System.out.println("Your vehicle will start out with its fuel full");
            System.out.println("---");
            System.out.println("Please input the type of vehicle you want to create: ");
            System.out.println("'1' for Ship");
            System.out.println("'2' for Basic Truck");
            System.out.println("'3' for Reefer Truck");
            System.out.println("'4' for Tanker Truck");
            int vehicleType = createVehicle.nextInt();
            Vehicles vehicle = null;
            switch (vehicleType) {
                case 1:
                    vehicle = new Ship(vehicleName, fuelCapacity, carryingCapacity, fuelCapacity, port, this);
                    break;
                case 2:
                     vehicle = new basicTruck(vehicleName, fuelCapacity, carryingCapacity, fuelCapacity, port, this);
                     break;
                case 3:
                    vehicle = new reeferTruck(vehicleName, fuelCapacity, carryingCapacity, fuelCapacity, port, this);
                    break;
                case 4:
                    vehicle = new tankerTruck(vehicleName, fuelCapacity, carryingCapacity, fuelCapacity, port, this);
                    break;
                default:
                    System.out.println("You did not enter a valid value");
                    break;
            }
            System.out.println("---");
        }

        @Override
        public void Create() {
            Scanner adminCreate = new Scanner(System.in);
            System.out.println("Please choose what you want to create: ");
            // Display create options to the user
            System.out.println("'1' for Vehicle");
            System.out.println("'2' for Port");
            System.out.println("'3' for Container");
            System.out.println("'4' for Manager");

            int createChoice = adminCreate.nextInt();
            switch (createChoice) {
                case 1:
                    System.out.println("Enter the ID of the port you want to create the vehicle at: p-");
                    int portIDToCreate1 = adminCreate.nextInt();
                    boolean foundPort1 = false;
                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToCreate1) {
                            this.createVehicleAtPort(port);
                            foundPort1 = true;
                            break;
                        }
                    }
                    if (foundPort1 == false) {
                        System.out.println("The port ID does not exist");
                    }
                    System.out.println("-------------------------------------------------");
                    break;
                case 2:
                    
                    break;
                case 3:
                    System.out.println("Enter the ID of the port you want to create the container at: ");
                    int portIDToCreate3 = adminCreate.nextInt();
                    boolean foundPort3 = false;
                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToCreate3) {
                            // Port found, create a container
                            this.createContainerAtPort(port);
                            foundPort3 = true;
                            break;
                        }
                    }
                    if (foundPort3 == false) {
                        System.out.println("The port ID does not exist");
                    }
                    System.out.println("-------------------------------------------------");
                case 4:
                    break;
                default:
                    System.out.println("You did not enter a valid value");
                    break;
            }

        }

        @Override
        public void Read() {
            Scanner adminRead = new Scanner(System.in);
            System.out.println("Please choose what you want to read: ");
            // Display create options to the user
            System.out.println("'1' for all Vehicles");
            System.out.println("'2' for all Ports");
            System.out.println("'3' for all Containers at a port");
            System.out.println("'4' for all Containers on a vehicle");
            System.out.println("'5' for all Managers");

            int readChoice = adminRead.nextInt();
            switch (readChoice) {
                case 1:
                    // Option 1: Read all vehicles
                    if (this.getVehiclesList().isEmpty()) {
                        System.out.println("No vehicles available.");
                    } else {
                        System.out.println("All Vehicles:");
                        for (Vehicles vehicle : this.getVehiclesList()) {
                            System.out.println("Vehicle ID: " +vehicle.getIDprefix() + vehicle.getVehicleID());
                            System.out.println("Type of vehicle: " + vehicle.getClass().getSimpleName());
                            System.out.println("Name: " + vehicle.getName());
                            System.out.println("Current Fuel: " + vehicle.getCurrentFuel());
                            System.out.println("Carrying Capacity: " + vehicle.getCarryingCapacity());
                            System.out.println("Fuel Capacity: " + vehicle.getFuelCapacity());
                            System.out.println("Current Port: " + (vehicle.getCurrentPort() != null ? vehicle.getCurrentPort().getName() : "None"));
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                case 2:
                    // Option 2: Read all Ports
                    if (this.getPortList().isEmpty()) {
                        System.out.println("No ports available.");
                    } else {
                        System.out.println("All Ports:");
                        for (Port port : this.getPortList()) {
                            System.out.println("Port ID: p-" + port.getPortID());
                            System.out.println("Name: " + port.getName());
                            System.out.println("Latitude: " + port.getLatitude());
                            System.out.println("Longitude: " + port.getLongitude());
                            System.out.println("Storing Capacity: " + port.getStoringCapacity());
                            System.out.println("Landing Ability: " + port.getLandingAbility());
                            System.out.println("Port Manager: " + (port.getPortManager() != null ? port.getPortManager().getUsername() : "None"));
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                case 3:
                    // Option 3: Read all containers at a port
                    System.out.println("Enter the ID of the port you want to read containers from: p-");
                    int portIDToReadContainers = adminRead.nextInt();
                    boolean foundPortForContainers = false;
                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToReadContainers) {
                            if (port.getContainers().isEmpty()) {
                                System.out.println("No containers at this port.");
                            } else {
                                System.out.println("Containers at Port " + port.getName() + ":");
                                for (Container container : port.getContainers()) {
                                    System.out.println("Container ID: c-" + container.getContainerID());
                                    System.out.println("Container Type: " + container.getClass().getSimpleName());
                                    System.out.println("Container Weight: " + container.getWeight());
                                    System.out.println("-----------------------------------");
                                }
                            }
                            foundPortForContainers = true;
                            break;
                        }
                    }
                    if (!foundPortForContainers) {
                        System.out.println("Port with ID p-" + portIDToReadContainers + " not found.");
                    }
                    break;
                case 4:
                    // Option 4: Read all containers on a vehicle
                    System.out.println("Enter the ID of the vehicle you want to read containers from (number only): ");
                    int vehicleIDToReadContainers = adminRead.nextInt();
                    boolean foundVehicleForContainers = false;
                    for (Vehicles vehicle : this.getVehiclesList()) {
                        if (vehicle.getVehicleID() == vehicleIDToReadContainers) {
                            if (vehicle.getNumContainer().isEmpty()) {
                                System.out.println("No containers on this vehicle.");
                            } else {
                                System.out.println("Containers on Vehicle " + vehicle.getName() + ":");
                                for (Container container : vehicle.getNumContainer()) {
                                    System.out.println("Container ID: c-" + container.getContainerID());
                                    System.out.println("Container Type: " + container.getClass().getSimpleName());
                                    System.out.println("Container Weight: " + container.getWeight());
                                    System.out.println("-----------------------------------");
                                }
                            }
                            foundVehicleForContainers = true;
                            break;
                        }
                    }
                    if (!foundVehicleForContainers) {
                        System.out.println("Vehicle with ID " + vehicleIDToReadContainers + " not found.");
                    }
                    break;
                case 5:
                    // Option 5: Read all Port Managers
                    if (this.getManagersList().isEmpty()) {
                        System.out.println("No port managers available.");
                    } else {
                        System.out.println("All Port Managers:");
                        for (PortManager manager : this.getManagersList()) {
                            // Modify this part to display relevant information about Port Managers
                            System.out.println("Manager: " + manager.getUsername());
                            System.out.println("Assigned port: " + manager.getAssignedPort());// Modify this line
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                default:
                    System.out.println("You did not enter a valid value.");
                    break;
            }
        }

        @Override
        public void Update() {

        }

        @Override
        public void Delete() {

        }
    }

