import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a sample SystemAdmin

        // Create a new Vehicle
        PortManagerMenu portManagerMenu = new PortManagerMenu();
        MainMenu mainMenu = new MainMenu();
        ArrayList<Container> numContainer1 = new ArrayList<>();
        SystemAdmin admin = new SystemAdmin("admin123", "12345");
        Port port = new Port(
                "PortName",
                123.456,
                789.012,
                1000.0,
                true,
                numContainer1,
                admin);
        PortManager portManager = new PortManager("Khoa", "1234", port, admin);
        MainMenu menu = new MainMenu();
        menu.displayMainMenu();
    }
}
