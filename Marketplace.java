import java.util.Scanner;

public class Marketplace {
    public static void menuMarketplace(){
        System.out.println("Loading Marketplace....");
        System.out.println("~~~Marketplace~~~\n\n1. Purchase Aircraft");
        Scanner scanner = new Scanner(System.in);   
        String response = scanner.nextLine().trim().toLowerCase();
        if(response.contains("1")||response.contains("ur")){
            buyAircraft();
        }
    }
    public static void buyAircraft(){
        System.out.println("This feature is coming soon!");
        //to be developed later

    }
}
