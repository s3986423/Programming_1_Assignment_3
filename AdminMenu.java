import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    public void displayAdminMenu() {
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Welcome to System Admin Menu");
        System.out.println("'1' to Login");
        System.out.println("'2' to Back to main menu");
        int AdminMenuChoice = scanner.nextInt();

        switch (AdminMenuChoice) {
            case 1:
                System.out.println("Enter username:");
                String username = scanner.next();
                if (username.equals(admin.getUsername())) {
                    System.out.println("Enter password:");
                    String password = scanner.next();
                    if (password.equals(admin.getPassword())) {
                        System.out.println("Login Successfully");
                        // Create an instance of AdminMenu
                        AdminMenu adminMenu = new AdminMenu();
                        // Call the CRUDadminMenu method on the instance
                        adminMenu.CRUDadminMenu(admin);
                    } else {
                        System.out.println("Incorrect password, Try again !");
                        portManagerMenu.displayPortManagerMenu();
                    }
                } else {
                    System.out.println("Incorrect username, Try again !");
                    // Call the displayAdminMenu method on the current instance
                    displayAdminMenu();
                }
                break;
            case 2:
                mainMenu.displayMainMenu();
                break;
        }
    }

    public void CRUDadminMenu(SystemAdmin systemAdmin) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Welcome to CRUD Menu of System Admin");
        System.out.println("'1' to Create");
        System.out.println("'2' to Read");
        System.out.println("'3' to Update");
        System.out.println("'4' to Delete");


        int CRUDadminChoice = scanner.nextInt();

        // Create an instance of SystemAdmin

        switch (CRUDadminChoice) {
            case 1:
                systemAdmin.Create();
            case 2:
                systemAdmin.Read();
                break;
            case 3:
                systemAdmin.Update();
            case 4:
                systemAdmin.Delete();
        }
    }
    }

