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
        }
        @Override
         public void Create(){
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
                // Now, add the created container directly to the port's list
                assignedPort.getContainers().add(container);

                // Optionally, you can print a confirmation message
                System.out.println("Container added to the port.");
            }
        }


         @Override
         public void Read() {
             List<Container> containersAtPort = assignedPort.getContainers();

             if (containersAtPort.isEmpty()) {
                 System.out.println("No containers at the port.");
             } else {
                 System.out.println("Containers at the port:");
                 for (Container container : containersAtPort) {
                     System.out.println("Container ID: " + container.getContainerID());
                     System.out.println("Container type:" +container.getClass().getSimpleName());
                     System.out.println("Container Weight: " + container.getWeight());
                     System.out.println("Fuel Per Km (Ship): " + container.getFuelPerKmShip());
                     System.out.println("Fuel Per Km (Truck): " + container.getFuelPerKmTruck());
                     System.out.println("-----------------------------------");
                 }
             }
         }
         @Override
         public void Update(){

         }
         @Override
         public void Delete(){

         }
    }
     class SystemAdmin extends User {
        public SystemAdmin(String username, String password){
            super(username, password);
        }




         public static void main(String[] args) {
             // Create a sample Port and PortManager
             Port port = new Port(
                     1,             // Port ID (int)
                     "PortName",    // Port Name (String)
                     123.456,       // Latitude (double)
                     789.012,       // Longitude (double)
                     10000.0,       // Storing Capacity (double)
                     true           // Landing Ability (boolean)
             );
             PortManager portManager = new PortManager("managerUsername", "managerPassword", port);

             // Simulate user input for creating containers
             // Display all containers
             portManager.Read();
         }
     }

