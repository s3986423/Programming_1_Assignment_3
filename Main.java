public class Main {
    public static void main(String[] args) {
        // Create a sample Port and PortManager
        SystemAdmin admin = new SystemAdmin("admin123", "12345");

        Port port = new Port(
                1,             // Port ID (int)
                "PortName",    // Port Name (String)
                123.456,       // Latitude (double)
                789.012,       // Longitude (double)
                10000.0,       // Storing Capacity (double)
                true,           // Landing Ability (boolean)
                admin
        );
        PortManager portManager = new PortManager("managerUsername", "managerPassword", port);

        // Simulate user input for creating containers
        portManager.Create();
        // Display all containers
        portManager.Read();
        portManager.Update();
        portManager.Read();
        portManager.Delete();
        portManager.Read();
    }
}
