import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class User  {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public abstract void displayMenu(SystemAdmin admin, MainMenu mainMenu);

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
    public static double calContainerTypeWeight(String containerType){
        String fileName = "containerData.txt"; // Specify the file name

        double totalWeight = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String currentContainerType = null;
            double currentContainerWeight = 0.0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Container Type: ")) {
                    currentContainerType = line.substring("Container Type: ".length());
                } else if (line.startsWith("Container Weight: ")) {
                    currentContainerWeight = Double.parseDouble(line.substring("Container Weight: ".length()));
                } else if (line.equals("-----------------------------------")) {
                    // Check if the current container matches the specified type
                    if (currentContainerType != null && currentContainerType.trim().equals(containerType)) {
                        totalWeight += currentContainerWeight;
                    }
                    // Reset values for the next container
                    currentContainerType = null;
                    currentContainerWeight = 0.0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalWeight;
    }
    public void weightEachTypeContainer() {
        Scanner weightType = new Scanner(System.in);

        System.out.println("Please input the container type you want to calculate total weight for: ");
        System.out.println("'1' for liquid");
        System.out.println("'2' for refrigerated");
        System.out.println("'3' for open side");
        System.out.println("'4' for open top");
        System.out.println("'5' for dry storage");
        int containerType = weightType.nextInt();
        switch (containerType) {
            case 1:
                System.out.println("The total weight of all container type liquid is: "
                        + calContainerTypeWeight("Liquid"));
                break;
            case 2:
                System.out.println("The total weight of all container type refrigerated is: "
                        + calContainerTypeWeight("Refrigerated"));
                break;
            case 3:
                System.out.println("The total weight of all container type open side is: "
                        + calContainerTypeWeight("openSide"));
                break;
            case 4:
                System.out.println("The total weight of all container type open top is: "
                        + calContainerTypeWeight("openTop"));
                break;
            default:
                System.out.println("The total weight of all container type dry storage is: "
                        + calContainerTypeWeight("dryStorage"));
                break;
        }
    }
    public void listAllShipInPort() {

    }
}
    interface CRUD{
    void Create();
    void Read();
    void Update();
    void Delete();
    }
    interface statisticOperations{
    void calFuelUseInDay();
    void weightEachTypeContainer();
    void listAllShipInPort();
    void listAllTripsInDay();
    void listAllTripsInPeriod();
//
    }
    class PortManager extends User implements CRUD  {
        private Port assignedPort; // Reference to the port managed by this manager
        private SystemAdmin admin;
        public PortManager(String username, String password, Port assignedPort, SystemAdmin admin) {
            super(username, password);
            this.assignedPort = assignedPort;
            assignedPort.setPortManager(this);
            this.admin = admin;
            this.admin.getManagersList().add(this);
        }

        public PortManager(String username, String password, SystemAdmin admin) {
            super(username, password);
            this.admin = admin;
            admin.getManagersList().add(this);
        }

        @Override
        public void displayMenu(SystemAdmin admin, MainMenu mainMenu) {
            System.out.println("====================================");
            System.out.println("Welcome to Port Manager Menu!");
            System.out.println("'1' to Login ");
            System.out.println("'2' to go back to Main Menu ");

            Scanner scanner = new Scanner(System.in);

            int PortManangerMenuChoice = scanner.nextInt();

            switch (PortManangerMenuChoice) {
                case 1:
                    System.out.println("Enter username:");
                    String username = scanner.next();
                    for (PortManager portManager : admin.getManagersList()) {
                        if (username.equals(portManager.getUsername())
                        ) {
                            System.out.println("Enter password:");
                            String password = scanner.next();
                            if (password.equals(portManager.getPassword())) {
                                System.out.println("Login Successfully");
                                portManager.PortManagerMenu(mainMenu);
                            } else {
                                System.out.println("Incorrect password, Try again !");
                                this.displayMenu(admin, mainMenu);
                            }
                        } else {
                            System.out.println("Incorrect username, Try again !");
                            this.displayMenu(admin, mainMenu);
                        }
                    }
                    break;
                case 2:
                    mainMenu.displayMainMenu(admin);
                    break;
            }
        }

        public void PortManagerMenu(MainMenu mainMenu){
            System.out.println("====================================");
            System.out.println("Welcome to the Menu of Port Manager");
            System.out.println("'1' to Create Container");
            System.out.println("'2' to Read Container");
            System.out.println("'3' to Update Container");
            System.out.println("'4' to Delete Container");
            System.out.println("'5' to go back to main menu");
            Scanner scanner = new Scanner(System.in);
            int CRUDchoice = scanner.nextInt();
            switch (CRUDchoice){
                case 1:
                    this.Create();
                    this.PortManagerMenu(mainMenu);
                case 2:
                    this.Read();
                    this.PortManagerMenu(mainMenu);
                case 3:
                    this.Update();
                    this.PortManagerMenu(mainMenu);
                case 4:
                    this.Delete();
                    this.PortManagerMenu(mainMenu);
                case 5:
                    System.out.println("Going back to Main Menu");
                    System.out.println("----------------------------------------");
                    mainMenu.displayMainMenu(admin);
            }
        }
        protected String getUsername() {
            return super.getUsername();
        }

        protected void setUsername(String username) {
            super.setUsername(username);
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

        @Override
        public void displayMenu(SystemAdmin admin, MainMenu mainMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("====================================");
            System.out.println("Welcome to System Admin Menu");
            System.out.println("'1' to Login");
            System.out.println("'2' to Back to main menu");
            int AdminMenuChoice = scanner.nextInt();

            switch (AdminMenuChoice) {
                case 1:
                    System.out.println("Enter username:");
                    String username = scanner.next();
                    if (username.equals(admin.getUsername())) {
                        System.out.println("Enter password:");
                        String password = scanner.next();
                        if (password.equals(admin.getPassword())) {
                            System.out.println("Login Successfully");
                            // Create an instance of AdminMenu
                            admin.adminMenu(mainMenu);
                        } else {
                            System.out.println("Incorrect password, Try again !");
                            this.displayMenu(admin, mainMenu);
                        }
                    } else {
                        System.out.println("Incorrect username, Try again !");
                        // Call the displayMenu method on the current instance
                        this.displayMenu(admin, mainMenu);
                    }
                    break;
                case 2:
                    mainMenu.displayMainMenu(this);
                    break;
            }
        }
        public void adminMenu(MainMenu mainMenu) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("====================================");
            System.out.println("Welcome to menu of System Admin");
            System.out.println("'1' to Create");
            System.out.println("'2' to Read");
            System.out.println("'3' to Update");
            System.out.println("'4' to Delete");
            System.out.println("'5' to go back to main menu");


            int adminMenuChoice = scanner.nextInt();

            // Create an instance of SystemAdmin

            switch (adminMenuChoice) {
                case 1:
                    this.Create();
                    adminMenu(mainMenu);
                    break;
                case 2:
                    this.Read();
                    adminMenu(mainMenu);
                    break;
                case 3:
                    this.Update();
                    adminMenu(mainMenu);
                    break;
                case 4:
                    this.Delete();
                    adminMenu(mainMenu);
                    break;
                case 5:
                    System.out.println("Going back to Main Menu");
                    System.out.println("----------------------------------------");
                    mainMenu.displayMainMenu(this);
                    break;
                default:
                    return;
            }
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
            System.out.println("Enter any other number key to go back to admin menu");
            int createChoice = adminCreate.nextInt();
            switch (createChoice) {
                case 1:
                    System.out.println("Enter the ID of the port you want to create the vehicle at: ");
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
                    System.out.println("Please input the port name: ");
                    String portName = adminCreate.next();
                    System.out.println("Please input the port latitude: ");
                    double latitude = adminCreate.nextDouble();
                    System.out.println("Please input the port longitude: ");
                    double longitude = adminCreate.nextDouble();
                    System.out.println("Please input the port storing capacity: ");
                    double storingCapacity = adminCreate.nextDouble();
                    System.out.println("Does the port have landing ability ?");
                    System.out.println("'1' for yes, any other key for no");
                    int landChoice = adminCreate.nextInt();
                    boolean landingAbility = false;
                    switch (landChoice){
                        case 1:
                            landingAbility = true;
                            break;
                        default:
                            break;
                    }
                    System.out.println("The port does not have any port manager by default, please update later!!!");
                    Port newPort = new Port(portName, latitude, longitude, storingCapacity, landingAbility,new ArrayList<Container>(), this);
                    System.out.println("The port have been successfully created");
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
                    break;
                case 4:
                    boolean usernameExists = true;
                    String managerUsername = "";

                    while (usernameExists) {
                        System.out.println("Please enter the username for the new Port Manager: ");
                        managerUsername = adminCreate.next();

                        // Check if the username already exists
                        usernameExists = false;
                        for (PortManager manager : this.getManagersList()) {
                            if (manager.getUsername().equals(managerUsername)) {
                                usernameExists = true;
                                System.out.println("Username '" + managerUsername + "' already exists. Please choose a different username.");
                                break;
                            }
                        }
                    }

                    System.out.println("Please enter the password for the new Port Manager: ");
                    String managerPassword = adminCreate.next();

                    System.out.println("Enter the ID of the port you want to assign to the new Port Manager: ");
                    int portIDToAssign = adminCreate.nextInt();
                    boolean foundPortForAssignment = false;
                    Port assignedPort = null;

                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToAssign) {
                            assignedPort = port;
                            foundPortForAssignment = true;
                            break;
                        }
                    }

                    if (foundPortForAssignment) {

                        System.out.println("Port Manager " + managerUsername + " has been successfully created and assigned to Port " + assignedPort.getName());
                    } else {
                        System.out.println("Port with ID " + portIDToAssign + " not found. Port Manager creation failed.");
                    }
                    break;
                default:
                    System.out.println("Going back to admin menu");
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
            System.out.println("Enter any number key to go back to admin menu");


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
                            System.out.println("Port ID: " + port.getPortID());
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
                    System.out.println("Enter the ID of the port you want to read containers from: ");
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
                        }
                    }
                    if (!foundPortForContainers) {
                        System.out.println("Port with ID " + portIDToReadContainers + " not found.");
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
                            System.out.println("Manager: " + manager.getUsername());
                            System.out.println("Assigned port: " + manager.getAssignedPort());
                            System.out.println("-----------------------------------");
                        }
                    }
                    break;
                default:
                    System.out.println("Going back to admin menu");
                    break;
            }
        }

        @Override
        public void Update() {
            Scanner adminUpdate = new Scanner(System.in);
            System.out.println("Please choose what you want to update: ");
            // Display update options to the user
            System.out.println("'1' for Vehicle");
            System.out.println("'2' for Port");
            System.out.println("'3' for Container");
            System.out.println("'4' for Manager");
            System.out.println("'5' to go back to CRUD Menu");

            int updateChoice = adminUpdate.nextInt();
            switch (updateChoice) {
                case 1:
                    // Update Vehicle
                    System.out.println("Enter the ID of the vehicle you want to update (number only): ");
                    int vehicleIDToUpdate = adminUpdate.nextInt();
                    boolean foundVehicleForUpdate = false;

                    for (Vehicles vehicle : this.getVehiclesList()) {
                        if (vehicle.getVehicleID() == vehicleIDToUpdate) {
                            // Vehicle found, ask users to update the value
                            System.out.println("Enter the new name for the vehicle: ");
                            String newName = adminUpdate.next();

                            System.out.println("Enter the new carrying capacity for the vehicle: ");
                            double newCarryingCapacity = adminUpdate.nextDouble();

                            System.out.println("Enter the new fuel capacity for the vehicle: ");
                            double newFuelCapacity = adminUpdate.nextDouble();

                            vehicle.setName(newName);
                            vehicle.setCarryingCapacity(newCarryingCapacity);
                            vehicle.setFuelCapacity(newFuelCapacity);

                            this.getVehiclesList().remove(vehicle);

                            System.out.println("Vehicle updated successfully.");
                            foundVehicleForUpdate = true;
                        }
                    }

                    if (!foundVehicleForUpdate) {
                        System.out.println("Vehicle with ID " + vehicleIDToUpdate + " not found.");
                    }
                    break;
                case 2:
                    // Update Port
                    System.out.println("Enter the ID of the port you want to update: ");
                    int portIDToUpdate = adminUpdate.nextInt();
                    boolean foundPortForUpdate = false;

                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToUpdate) {
                            // Port found, ask users to update the value
                            System.out.println("Enter the new name for the port: ");
                            String newName = adminUpdate.next();

                            System.out.println("Enter the new latitude for the port: ");
                            double newLatitude = adminUpdate.nextDouble();

                            System.out.println("Enter the new longitude for the port: ");
                            double newLongitude = adminUpdate.nextDouble();

                            System.out.println("Enter the new storing capacity for the port: ");
                            double newStoringCapacity = adminUpdate.nextDouble();

                            System.out.println("Change Landing Ability?");
                            System.out.println("'1' for yes, any other key for no");
                            int landChoice = adminUpdate.nextInt();
                            boolean landingAbility = (landChoice == 1);

                            // Update all the properties at once
                            port.setName(newName);
                            port.setLatitude(newLatitude);
                            port.setLongitude(newLongitude);
                            port.setStoringCapacity(newStoringCapacity);
                            port.setLandingAbility(landingAbility);

                            System.out.println("Port updated successfully.");
                            foundPortForUpdate = true;
                        }
                    }
                    if (!foundPortForUpdate) {
                        System.out.println("Port with ID " + portIDToUpdate + " not found.");
                    }
                    break;
                case 3:
                    // Update Container
                    System.out.println("Enter the ID of the container you want to update (number only): ");
                    int containerIDToUpdate = adminUpdate.nextInt();
                    boolean foundContainerForUpdate = false;

                    for (Port port : this.getPortList()) {
                        for (Container container : port.getContainers()) {
                            if (container.getContainerID() == containerIDToUpdate) {
                                // Container found, now ask the user for the updated values
                                System.out.println("Enter the new weight for the container: ");
                                double newWeight = adminUpdate.nextDouble();

                                // First, remove the existing container
                                port.getContainers().remove(container);

                                // Add the updated container to the port
                                port.getContainers().add(container);

                                System.out.println("Container updated successfully.");
                                foundContainerForUpdate = true;
                            }
                        }
                    }
                    if (!foundContainerForUpdate) {
                        System.out.println("Container with ID " + containerIDToUpdate + " not found.");
                    }
                    break;
                default:
                    System.out.println("Going back to admin menu");
                    break;
            }
        }

        @Override
        public void Delete() {
            Scanner adminDelete = new Scanner(System.in);
            System.out.println("Please choose what you want to delete: ");
            // Display delete options to the user
            System.out.println("'1' to delete a Vehicle");
            System.out.println("'2' to delete a Port");
            System.out.println("'3' to delete a Container");
            System.out.println("'4' to delete a Manager");
            System.out.println("'5' to go back to CRUD menu");

            int deleteChoice = adminDelete.nextInt();
            switch (deleteChoice) {
                case 1:
                    // Delete a Vehicle
                    System.out.println("Enter the ID of the vehicle you want to delete (number only): ");
                    int vehicleIDToDelete = adminDelete.nextInt();
                    boolean foundVehicleForDeletion = false;

                    for (Vehicles vehicle : this.getVehiclesList()) {
                        if (vehicle.getVehicleID() == vehicleIDToDelete) {
                            // Vehicle found, remove it from the list
                            this.getVehiclesList().remove(vehicle);
                            System.out.println("Vehicle with ID " + vehicleIDToDelete + " has been deleted.");
                            foundVehicleForDeletion = true;
                        }
                    }
                    if (!foundVehicleForDeletion) {
                        System.out.println("Vehicle with ID " + vehicleIDToDelete + " not found.");
                    }
                    break;
                case 2:
                    // Delete a Port
                    System.out.println("Enter the ID of the port you want to delete: ");
                    int portIDToDelete = adminDelete.nextInt();
                    boolean foundPortForDeletion = false;

                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToDelete) {
                            // Port found, remove it from the list
                            this.getPortList().remove(port);
                            System.out.println("Port with ID " + portIDToDelete + " has been deleted.");
                            foundPortForDeletion = true;
                        }
                    }
                    if (!foundPortForDeletion) {
                        System.out.println("Port with ID " + portIDToDelete + " not found.");
                    }
                    break;
                case 3:
                    // Delete a Container
                    System.out.println("Enter the ID of the container you want to delete (number only): ");
                    int containerIDToDelete = adminDelete.nextInt();
                    boolean foundContainerForDeletion = false;

                    for (Port port : this.getPortList()) {
                        for (Container container : port.getContainers()) {
                            if (container.getContainerID() == containerIDToDelete) {
                                // Container found, remove it from the port's containers
                                port.getContainers().remove(container);
                                System.out.println("Container with ID " + containerIDToDelete + " has been deleted.");
                                foundContainerForDeletion = true;
                            }
                        }
                        if (foundContainerForDeletion) {
                        }
                    }
                    if (!foundContainerForDeletion) {
                        System.out.println("Container with ID " + containerIDToDelete + " not found.");
                    }
                    break;
                case 4:
                    // Delete a Manager
                    System.out.println("Enter the username of the manager you want to delete: ");
                    String managerUsernameToDelete = adminDelete.next();
                    boolean foundManagerForDeletion = false;

                    for (PortManager manager : this.getManagersList()) {
                        if (manager.getUsername().equals(managerUsernameToDelete)) {
                            // Manager found, remove it from the list
                            this.getManagersList().remove(manager);
                            System.out.println("Manager with username '" + managerUsernameToDelete + "' has been deleted.");
                            foundManagerForDeletion = true;
                        }
                    }
                    if (!foundManagerForDeletion) {
                        System.out.println("Manager with username '" + managerUsernameToDelete + "' not found.");
                    }
                    break;
                default:
                    System.out.println("Going back to the admin menu");
                    break;
            }
        }

    }
