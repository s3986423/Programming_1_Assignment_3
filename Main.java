public class Main {
    public static void main(String[] args) {
        // Create a sample Port and PortManager
        SystemAdmin admin = new SystemAdmin("admin123", "12345");

        Port port = new Port(
                "PortName",    // Port Name (String)
                123.456,       // Latitude (double)
                789.012,       // Longitude (double)
                10000.0,       // Storing Capacity (double)
                true,           // Landing Ability (boolean)
                admin           // System Admin (System Admin)
        );
        PortManager portManager = new PortManager("managerUsername", "managerPassword", port, admin);

        // Simulate user input for creating containers
//      portManager.Create();
//        //Display all containers
//        portManager.Read();
//        portManager.Update();
//        portManager.Read();
//        portManager.Delete();
//        portManager.Read();
        try {
            // Simulate user input for creating containers
            portManager.Create();
            // Display all containers after creating

            // Simulate user input for updating containers
            portManager.Update();
            // Display all containers after updating

        admin.Create();
        admin.Read();
            // Simulate user input for deleting containers


            // Display all containers after deleting

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
