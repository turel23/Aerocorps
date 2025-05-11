import java.util.Scanner;

public class EconomyGUI {//not ready to be released yet
    public static void MenuEconomy(){
        System.out.println("1. Purchase Fuel\n\n2. Sell Fuel");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        if(response.contains("1") || response.contains("ice")){
            JetA1.buyFuel();
        }
        if(response.contains("2") || response.contains("urc")){
            JetA1.sellFuel();
        }
    }
}
