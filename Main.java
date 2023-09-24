import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a sample SystemAdmin
        SystemAdmin admin = new SystemAdmin("admin123", "12345");
        // Create a new Vehicle
        MainMenu mainMenu = new MainMenu();
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
    }
}
