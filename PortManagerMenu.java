import java.util.ArrayList;
import java.util.Scanner;
public class PortManagerMenu {

    public void displayPortManagerMenu() {
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
        System.out.println("====================================");
        System.out.println("Welcome to Port Manager Menu!");
        System.out.println("'1' to Login ");
        System.out.println("'2' to go back to Main Menu ");

        Scanner scanner = new Scanner(System.in);

        int PortManangerMenuChoice = scanner.nextInt();

        switch (PortManangerMenuChoice) {
            case 1:
                System.out.println("Enter username:");
                String username = scanner.next();
                if (username.equals(portManager.getUsername())
                ) {
                    System.out.println("Enter password:");
                    String password = scanner.next();
                    if (password.equals(portManager.getPassword())) {
                        System.out.println("Login Successfully");
                        portManagerMenu.PortManagerCRUD();
                    } else {
                        System.out.println("Incorrect password, Try again !");
                        portManagerMenu.displayPortManagerMenu();
                    }
                } else {
                    System.out.println("Incorrect username, Try again !");
                    portManagerMenu.displayPortManagerMenu();
                }
                break;

            case 2:
                mainMenu.displayMainMenu();
                break;
        }
    }


    public void PortManagerCRUD(){
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
        System.out.println("====================================");
        System.out.println("Welcome to CRUD Menu of Port Manager");
        System.out.println("'1' to Create Container");
        System.out.println("'2' to Read Container");
        System.out.println("'3' to Update Container");
        System.out.println("'4' to Delete Container");
        Scanner scanner = new Scanner(System.in);
        int CRUDchoice = scanner.nextInt();
        switch (CRUDchoice){
            case 1:
                portManager.Create();
                portManagerMenu.PortManagerCRUD();
            case 2:
                portManager.Read();
                portManagerMenu.PortManagerCRUD();
            case 3:
                portManager.Update();
                portManagerMenu.PortManagerCRUD();
            case 4:
                portManager.Delete();
                portManagerMenu.PortManagerCRUD();
        }
    }
}



