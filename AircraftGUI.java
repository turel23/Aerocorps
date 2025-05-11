import java.util.Scanner;

public class AircraftGUI {
    public static void MenuAircraft(){
        System.out.println("1. Purchase Aircraft\n\n2. Sell Aircraft");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        if(response.contains("1") || response.contains("ice")){
            buyAircraft();
        }
        if(response.contains("2") || response.contains("urc")){
            sellAircraft();
        }
    }
    public static void buyAircraft(){
        Database.displayCollection();
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        for(int i=0; i<Database.collection.size(); i++){
            if(Database.collection.get(i).ifPlane(response)){
                if(Database.collection.get(i).getPrice()*1000000>Database.money){System.out.println("You can't afford this aircraft"); return;}
                System.out.println("Amount of aircraft to purchase:");
                Scanner scanned = new Scanner(System.in);
                String reponse = scanner.nextLine().trim().toLowerCase();
                int amount = Integer.valueOf(reponse);
                if(reponse==""||reponse=="0"){System.out.println("Cancelling Transaction...."); return;}
                System.out.println("Purchasing "+amount+" of aircraft");
                if(Database.money<Database.collection.get(i).getPrice()*1000000*amount){System.out.println("You can't afford these "+amount+" aircraft");}
                Database.money-=Database.collection.get(i).getPrice()*1000000.0*amount;
                Aircraft purchase = Database.collection.get(i);

                for(int o=0; o<amount; o++){
                    Aircraft newaircraft = purchase.clone();
                    Database.aircrafts.add(newaircraft);
                }
                System.out.println("Transaction successful");
            }
        }
        try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
    }
    public static void sellAircraft(){
        Database.displayAircraft();
        System.out.println("Select aircraft to sell");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        boolean exists = false;
        int count = 0;
        Aircraft aircraft = null;
        for(int i=0; i<Database.aircrafts.size(); i++){
            if(Database.aircrafts.get(i).ifPlane(response)){
                aircraft = Database.aircrafts.get(i);
                exists = true;
                count++;
            }
        }
        if(!exists){System.out.println("You do not own this aircraft."); return;}
        System.out.println("Quantity of aircraft to sell, you have "+count+" of this aircraft:");
        String second = scanner.nextLine().trim().toLowerCase();
        Integer num = Integer.valueOf(second);
        if(num>count){System.out.println("You do not own this amount of aircraft."); return;}
        for(int i=0; i<num; i++){
            Database.aircrafts.remove(aircraft);
            Database.money+=aircraft.getPrice()*1000000.0;
        }
    }
}
