import java.util.Scanner;

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
            Container container;
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

        }
         @Override
         public void Read(){

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
}
