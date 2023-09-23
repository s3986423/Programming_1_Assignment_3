import java.util.Scanner;
public class MainMenu {
    public void displayMainMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group name: group 29");
        System.out.println("s3986281, Dong Dang Khoa");
        System.out.println("s3975681, Phan Nguyen Phuoc Tien");
        System.out.println("s3986423, Nguyen Huu Quoc Huy" );
        System.out.println("s3974923, Nguyen Tran Tri An");
        System.out.println("====================================");
        System.out.println("CONTAINER PORT MANAGEMENT MAIN MENU");
        System.out.println("Press a number to continue:");
        System.out.println("'1' to go to Port Manager Menu ");
        System.out.println("'2' to go to System Admin Menu");
        System.out.println("'3' to exit ");
        int menuchoice = scanner.nextInt();
        AdminMenu adminMenu = new AdminMenu();
        PortManagerMenu portManagerMenu = new PortManagerMenu();
        switch (menuchoice){
            case 1:
                portManagerMenu.displayPortManagerMenu();
            case 2:
                adminMenu.displayAdminMenu();
            case 3:
                break;
        }
    }
}
