import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Create a sample SystemAdmin
        SystemAdmin admin = new SystemAdmin("admin123", "12345");

        // Create a new Vehicle
        MainMenu mainMenu = new MainMenu();


        // Create containers
//        Container container1 = new Liquid(10.0);
//        Container container2 = new Liquid(13.0);
//        Container container3 = new Liquid(23.0);
//        Container container4 = new Liquid(36.0);
//        Container container5 = new Liquid(46.0);
//        Container container6 = new Liquid(82.46);
//        Container container7 = new dryStorage(20.1);
//        Container container8 = new dryStorage(30.2);
//        Container container9 = new dryStorage(40.3);
//        Container container10 = new dryStorage(50.4);
//        Container container11 = new dryStorage(60.5);
//        Container container12 = new dryStorage(70.6);
//        Container container13 = new Refrigerated(22.2);
//        Container container14 = new Refrigerated(33.3);
//        Container container15 = new Refrigerated(44.4);
//        Container container16 = new Refrigerated(55.5);
//        Container container17 = new Refrigerated(66.6);
//        Container container18 = new Refrigerated(77.7);
//        Container container19 = new openTop(80.3);
//        Container container20 = new openTop(90.4);
//        Container container21 = new openTop(100.5);
//        Container container22 = new openTop(110.6);
//        Container container23 = new openTop(120.7);
//        Container container24 = new openTop(130.8);
//        Container container25 = new openSide(88.8);
//        Container container26 = new openSide(99.9);
//        Container container27 = new openSide(100.0);
//        Container container28 = new openSide(111.1);
//        Container container29 = new openSide(222.2);
//        Container container30 = new openSide(333.3);


        ArrayList<Container> numContainer1 = new ArrayList<>();
        // Create a sample Port and associate it with the SystemAdmin

        Port port = new Port(
                "PortName",
                123.456,
                789.012,
                1000.0,
                true,
                new ArrayList<>(),
                admin);
        PortManager portManager = new PortManager("Khoa", "1234", port, admin);


        mainMenu.displayMainMenu(admin);

        // Create a PortManager and associate it with the Port and SystemAdmin
       

        // Simulate user input for creating containers
      //portManager.Create();
//        //Display all containers
    //   portManager.Read();
//        portManager.Update();
//        portManager.Read();
//        portManager.Delete();
//        portManager.Read();
        //admin.Read();
          //admin.Create();
          //admin.Update();
          //admin.Delete();
         //Simulate user input for creating containers
        //portManager.Create();
        //portManager.Create();

//        portManager.weightEachTypeContainer();
//        portManager.weightEachTypeContainer();


         //Display all containers after creating

        // Simulate user input for updating a container
//        portManager.Update();
//        portManager.Read();
           // Simulate user input for deleting containers

        // Simulate user input for deleting containers

//        // Specify the path to the container data file
//        String filePath = "containerData.txt";
//
//        // Read and print the container data from the file
//        portManager.readAndPrintContainerData(filePath);

    }
}
