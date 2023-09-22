public class Main {
    public static void main(String[] args) {
        // Create a sample SystemAdmin
        SystemAdmin admin = new SystemAdmin("admin123", "12345");

        // Create a sample Port and associate it with the SystemAdmin
        Port port = new Port(
                "PortName",
                123.456,
                789.012,
                1000.0,
                true,
                admin
        );

        // Create a PortManager and associate it with the Port and SystemAdmin
        PortManager portManager = new PortManager("managerUsername", "managerPassword", port, admin);

        // Simulate user input for creating containers
        portManager.Create();

        // Display all containers after creating
        portManager.Read();

        // Simulate user input for updating a container
        portManager.Update();


        // Specify the path to the container data file
        String filePath = "containerData.txt";

        // Read and print the container data from the file
        portManager.readAndPrintContainerData(filePath);
    }
}
