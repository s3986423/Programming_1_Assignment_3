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
    public Container createContainer() {
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
        return container;

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
        public PortManager(String username, String password, Port assignedPort) {
            super(username, password);
            this.assignedPort = assignedPort;
            assignedPort.setPortManager(this);
        }
        @Override
        public void Create(){
            Container container = this.createContainer();
            if (container != null) {
                // Check if the port have enough storing capacity for the container
                if (this.assignedPort.calCurrentCapacity() + container.getWeight() > this.assignedPort.getStoringCapacity()){
                    System.out.println("The port does not have enough capacity for the container");
                }else {
                    // Now, add the created container directly to the port's list
                    assignedPort.getContainers().add(container);

                    // Optionally, you can print a confirmation message
                    System.out.println("Container added to the port.");
                }
            }
        }


        @Override
        public void Read() {
            ArrayList<Container> containersAtPort = assignedPort.getContainers();

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
        public void Update() {
            Scanner update = new Scanner(System.in);
            List<Container> containersAtPort = assignedPort.getContainers();

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
        }
        @Override
        public void Delete() {
            Scanner scanner = new Scanner(System.in);
            List<Container> containersAtPort = assignedPort.getContainers();

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
    class SystemAdmin extends User {
        private ArrayList<Port> portList;

        public SystemAdmin(String username, String password){
            super(username, password);
            this.portList = new ArrayList<>();
        }

        protected ArrayList<Port> getPortList() {
            return portList;
        }



     }

