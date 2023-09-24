import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a sample SystemAdmin
        SystemAdmin admin = new SystemAdmin("admin123", "12345");
        initializeData(admin);
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMainMenu(admin);
        // Create a PortManager and associate it with the Port and SystemAdmin
    }

    public static void initializeData(SystemAdmin admin) {
        // Create containers
        Container container1 = new Liquid(10.0);
        Container container2 = new Liquid(13.0);
        Container container3 = new Liquid(23.0);
        Container container4 = new Liquid(36.0);
        Container container5 = new Liquid(46.0);
        Container container6 = new Liquid(82.46);
        Container container7 = new dryStorage(20.1);
        Container container8 = new dryStorage(30.2);
        Container container9 = new dryStorage(40.3);
        Container container10 = new dryStorage(50.4);
        Container container11 = new dryStorage(60.5);
        Container container12 = new dryStorage(70.6);
        Container container13 = new Refrigerated(22.2);
        Container container14 = new Refrigerated(33.3);
        Container container15 = new Refrigerated(44.4);
        Container container16 = new Refrigerated(55.5);
        Container container17 = new Refrigerated(66.6);
        Container container18 = new Refrigerated(77.7);
        Container container19 = new openTop(80.3);
        Container container20 = new openTop(90.4);
        Container container21 = new openTop(100.5);
        Container container22 = new openTop(110.6);
        Container container23 = new openTop(120.7);
        Container container24 = new openTop(130.8);
        Container container25 = new openSide(88.8);
        Container container26 = new openSide(99.9);
        Container container27 = new openSide(100.0);
        Container container28 = new openSide(111.1);
        Container container29 = new openSide(222.2);
        Container container30 = new openSide(333.3);

        // Create a sample Port and associate it with the SystemAdmin
        Port port1 = new Port(
                "Port1",
                123.45612,
                90.74623,
                1500.0,
                true,
                admin);
        port1.getContainers().add(container1);
        port1.getContainers().add(container7);
        port1.getContainers().add(container13);
        port1.getContainers().add(container19);
        port1.getContainers().add(container25);

        Port port2 = new Port(
                "Port2",
                127.34698,
                189.09647,
                1200.0,
                true,
                admin);
        port2.getContainers().add(container2);
        port2.getContainers().add(container8);
        port2.getContainers().add(container14);
        port2.getContainers().add(container26);

        Port port3 = new Port(
                "Port3",
                -36.15537,
                -155.86624,
                1000.0,
                true,
                admin);
        port3.getContainers().add(container9);
        port3.getContainers().add(container15);
        port3.getContainers().add(container20);
        port3.getContainers().add(container27);

        Port port4 = new Port(
                "Port4",
                33.43349,
                75.57086,
                1200.0,
                false,
                admin);
        Port port5 = new Port(
                "Port5",
                -62.41663,
                143.16146,
                1300.0,
                false,
                admin);

        //Create PortManagers and assigned them with the ports
        PortManager portManager1 = new PortManager("Khoa", "s3986281", port1, admin);
        PortManager portManager2 = new PortManager("Huy", "s3986423", port2, admin);
        PortManager portManager3 = new PortManager("Tien", "s3975681", port3, admin);
        PortManager portManager4 = new PortManager("An", "s3974923", port4, admin);
        PortManager portManager5 = new PortManager("Mast", "s1234567", port5, admin);

        // Create vehicles and assign it to a specific port
        Vehicles vehicle1 = new basicTruck("Basic Truck 1", 769, 700, 800, port1, admin);
        Vehicles vehicle2 = new basicTruck("Basic Truck 2", 570, 700, 800, port1, admin);
        Vehicles vehicle3 = new basicTruck("Basic Truck 3", 678, 700, 800, port2, admin);
        Vehicles vehicle4 = new basicTruck("Basic Truck 4", 789, 700, 800, port2, admin);
        Vehicles vehicle5 = new basicTruck("Basic Truck 5", 688, 700, 800, port3, admin);
        Vehicles vehicle6 = new reeferTruck("Reefer Truck 1", 654, 800, 900, null, admin);
        Vehicles vehicle7 = new reeferTruck("Reefer Truck 2", 779, 800, 900, null, admin);
        Vehicles vehicle8 = new reeferTruck("Reefer Truck 3", 765, 800, 900, null, admin);
        Vehicles vehicle9 = new reeferTruck("Reefer Truck 4", 568, 800, 900, null, admin);
        Vehicles vehicle10 = new reeferTruck("Reefer Truck 5", 566, 800, 900, null, admin);
        Vehicles vehicle11 = new tankerTruck("Tanker Truck 1", 799, 1000, 1000, null, admin);
        Vehicles vehicle12 = new tankerTruck("Tanker Truck 2", 856, 1000, 1000, null, admin);
        Vehicles vehicle13 = new tankerTruck("Tanker Truck 3", 866, 1000, 1000, null, admin);
        Vehicles vehicle14 = new tankerTruck("Tanker Truck 4", 888, 1000, 1000, port3, admin);
        Vehicles vehicle15 = new tankerTruck("Tanker Truck 5", 931, 1000, 1000, port5, admin);
        Vehicles vehicle16 = new Ship("Ship 1", 699, 900, 900, port1, admin);
        Vehicles vehicle17 = new Ship("Ship 2", 687, 900, 900, port3, admin);
        Vehicles vehicle18 = new Ship("Ship 3", 787, 900, 900, port2, admin);
        Vehicles vehicle19 = new Ship("Ship 4", 734, 900, 900, port2, admin);
        Vehicles vehicle20 = new Ship("Ship 5", 758, 900, 900, port4, admin);


//        Trip trip1 = new Trip(vehicle1, LocalDate.of(2023,9,18), LocalDate.of(2023,9,19), port1, port2, "completed");
//        Trip trip2 = new Trip(vehicle1, LocalDate.of(2023,9,19), LocalDate.of(2023,9,22), port2, port1, "completed");
//        Trip trip3 = new Trip(vehicle2, LocalDate.of(2023, 9,18), LocalDate.of(2023, 9,20), port1, port3, "completed");
//        Trip trip4 = new Trip(vehicle3, LocalDate.of(2023, 9,17), LocalDate.of(2023, 9,20), port2, port3, "completed");
//        Trip trip5 = new Trip(vehicle4, LocalDate.of(2023, 9,16), LocalDate.of(2023, 9,20), port2, port5, "completed");
//        Trip trip6 = new Trip(vehicle5, LocalDate.of(2023, 9,17), LocalDate.of(2023, 9,20), port3, port4, "completed");
//        Trip trip7 = new Trip(vehicle6, LocalDate.of(2023, 9,18), LocalDate.of(2023, 9,25), port1, port4, "moving");
//        Trip trip8 = new Trip(vehicle7, LocalDate.of(2023, 9,19), LocalDate.of(2023, 9,24), port2, port5, "moving");
//        Trip trip9 = new Trip(vehicle8, LocalDate.of(2023, 9,19), LocalDate.of(2023, 9,26), port3, port5, "moving");
//        Trip trip10 = new Trip(vehicle9, LocalDate.of(2023, 9,24), LocalDate.of(2023, 9,29), port3, port2, "moving");
//        Trip trip11 = new Trip(vehicle10, LocalDate.of(2023, 9,25), LocalDate.of(2023, 9,28), port5, port1, "moving");
//        Trip trip12 = new Trip(vehicle11, LocalDate.of(2023, 9,24), LocalDate.of(2023, 9,27), port5, port2, "moving");
//        Trip trip13 = new Trip(vehicle12, LocalDate.of(2023, 9,22), LocalDate.of(2023, 9,26), port4, port2, "moving");
//        Trip trip14 = new Trip(vehicle13, LocalDate.of(2023, 9,22), LocalDate.of(2023, 9,26), port1, port2, "moving");
//        Trip trip15 = new Trip(vehicle14, LocalDate.of(2023, 9,23), LocalDate.of(2023, 9,25), port3, port1, "moving");
//        Trip trip16 = new Trip(vehicle15, LocalDate.of(2023, 9,23), LocalDate.of(2023, 9,24), port5, port2, "completed");
//        Trip trip17 = new Trip(vehicle16, LocalDate.of(2023, 9,24), LocalDate.of(2023, 9,30), port3, port2, "moving");
//        Trip trip18 = new Trip(vehicle17, LocalDate.of(2023, 9,25), LocalDate.of(2023, 9,29), port4, port3, "moving");
//        Trip trip19 = new Trip(vehicle18, LocalDate.of(2023, 9,26), LocalDate.of(2023, 9,30), port1, port5, "moving");
//        Trip trip20 = new Trip(vehicle19, LocalDate.of(2023, 9,15), LocalDate.of(2023, 9,22), port3, port5, "completed");
//        Trip trip21 = new Trip(vehicle20, LocalDate.of(2023, 9,14), LocalDate.of(2023, 9,20), port1, port4, "completed");
//        Trip trip22 = new Trip(vehicle14, LocalDate.of(2023, 9,12), LocalDate.of(2023, 9,17), port5, port3, "completed");
//        Trip trip23 = new Trip(vehicle13, LocalDate.of(2023, 9,18), LocalDate.of(2023, 9,22), port5, port1, "completed");
//        Trip trip24 = new Trip(vehicle19, LocalDate.of(2023, 9,16), LocalDate.of(2023, 9,19), port4, port1, "completed");
//        Trip trip25 = new Trip(vehicle16, LocalDate.of(2023, 9,24), LocalDate.of(2023, 9,28), port3, port2, "moving");

    }
}
