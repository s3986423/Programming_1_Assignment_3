import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public abstract class User {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void createContainerAtPort(Port port) {
        Scanner scanner = new Scanner(System.in);

        //Input the container weight
        System.out.println("Please input the container weight: ");
        double containerWeight = scanner.nextDouble();

        // Display container type options to the user
        System.out.println("Select the container type:");
        System.out.println("1. Liquid");
        System.out.println("2. Refrigerated");
        System.out.println("3. Open Side");
        System.out.println("4. Open Top");
        System.out.println("5. Dry Storage");

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
            // Check if the port have enough storing capacity for the container
            if (port.calCurrentCapacity() + container.getWeight() > port.getStoringCapacity()){
                System.out.println("The port does not have enough capacity for the container");
            }else {
                // Now, add the created container directly to the port's list
                port.getContainers().add(container);

                // Optionally, you can print a confirmation message
                System.out.println("Container added to the port.");
            }
        }
        scanner.close();
    }
    public void readContainerAtPort(Port port){
        ArrayList<Container> containersAtPort = port.getContainers();

        if (containersAtPort.isEmpty()) {
            System.out.println("No containers at the port.");
        } else {
            System.out.println("Containers at the port:");
            for (Container container : containersAtPort) {
                System.out.println("Container ID: C-" + container.getContainerID());
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
            System.out.println("Enter the ID of the container you want to update: ");
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
                System.out.println("Container with ID " + containerIDToUpdate + " not found.");
            }
        }
        update.close();
    }
    public void deleteContainerAtPort(Port port) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Container> containersAtPort = port.getContainers();

        if (containersAtPort.isEmpty()) {
            System.out.println("No containers at the port.");
        } else {
            System.out.println("Enter the ID of the container you want to delete: ");
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
                System.out.println("Container with ID " + containerIDToDelete + " not found.");
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
        private SystemAdmin admin;
        public PortManager(String username, String password, Port assignedPort, SystemAdmin admin) {
            super(username, password);
            this.assignedPort = assignedPort;
            assignedPort.setPortManager(this);
            this.admin = admin;
            this.admin.getManagersList().add(this);
        }
        @Override
        public void Create(){
            createContainerAtPort(this.assignedPort);
        }


        @Override
        public void Read() {
            readContainerAtPort(this.assignedPort);
        }
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

        @Override
        public void Create() {
            Scanner adminCreate = new Scanner(System.in);
            System.out.println("Please choose what you want to create: ");
            // Display create options to the user
            System.out.println("1. Vehicle");
            System.out.println("2. Port");
            System.out.println("3. Container");
            System.out.println("4. Manager");

            int createChoice = adminCreate.nextInt();
            switch (createChoice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Enter the ID of the port you want to create the container at: ");
                    int portIDToCreate = adminCreate.nextInt();
                    boolean foundPort = false;
                    for (Port port : this.getPortList()) {
                        if (port.getPortID() == portIDToCreate) {
                            // Port found, create a container
                            this.createContainerAtPort(port);
                            foundPort = true;
                            adminCreate.close();
                            break;
                        }
                    }
                    if (foundPort = false) {
                        System.out.println("The port ID does not exist");
                        adminCreate.close();
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
            System.out.println("1. All Vehicles");
            System.out.println("2. All Ports");
            System.out.println("3. All Containers at a port");
            System.out.println("4. All Containers on a vehicle");
            System.out.println("5. All Managers");

        }

        @Override
        public void Update() {

        }

        @Override
        public void Delete() {

        }
    }

