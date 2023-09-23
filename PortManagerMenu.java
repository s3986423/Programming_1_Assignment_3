import java.util.ArrayList;
import java.util.Scanner;
public class PortManagerMenu {
    public void displayPortManagerMenu(){

        Scanner scanner = new Scanner(System.in);
        PortManagerMenu portManagerMenu = new PortManagerMenu();
        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMainMenu();
        ArrayList<Container> numContainer1 = new ArrayList<>();
        int PortManangerMenuChoice = scanner.nextInt();
        SystemAdmin admin = new SystemAdmin("admin123", "12345");
        Port port = new Port(
                "PortName",
                123.456,
                789.012,
                1000.0,
                true,
                numContainer1,
                admin);
        PortManager portManager = new PortManager("Khoa", "1234",port, admin);
        System.out.println("====================================");
        System.out.println("Welcome to Port Manager Menu!");
        System.out.println("'1' to Login ");
        System.out.println("'2' to go back to Main Menu ");



        switch (PortManangerMenuChoice){
            case 1:
                System.out.println("Enter username:");;
                String username = scanner.nextLine();
                if (username.equals(portManager.getUsername())
                ) {
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    if (password.equals(portManager.getPassword())){
                        System.out.println("Login Successfully");
                        portManagerMenu.PortManagerCRUD();
                    }
                    else {
                        System.out.println("Incorrect password, Try again !");
                    }
                }
            case 2:
                mainMenu.displayMainMenu();
        }
    }


    public void PortManagerCRUD(){


        }
    }



