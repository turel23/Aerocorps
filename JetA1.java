import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;


public class JetA1 implements Serializable{
    public static void seePrice(){
        System.out.println("Fetching jet A-1 fuel prices....");
        try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        LocalDateTime now = LocalDateTime.now();
        
        Duration duration = Duration.between(Database.recent, now);
        int hoursDifference = (int)duration.toHours();
        Database.recent = now;
        
        for(int i =0; i< hoursDifference; i++){
            if(Database.price-88.3>=0){
                if(Math.random()<((double)((Database.price-88.3)/20.0)+0.5)){
                    Database.price-=Math.random()*(1.1479);
                }
                else{
                    Database.price+=Math.random()*(1.1479);
                }
            }
            if(Database.price-88.3<0){
                if(Math.random()<((double)((88.3-Database.price)/20.0)+0.5)){
                    Database.price+=Math.random()*(1.1479);
                }
                else{
                    Database.price-=Math.random()*(1.1479);

                }
            }
            if(hoursDifference-i<=6&& hoursDifference-i>1){
                Database.prices.add(Database.price);
            }
        }
        if(Database.prices.size()>5){
            for(int i=Database.prices.size()-1; i>=5; i--){
                Database.prices.remove(i);
            }
        }
        System.out.println("Prices from the last 5 hours:");
        for(int i=0; i<Database.prices.size(); i++){
            System.out.println((Database.prices.size()-i)+" hour(s) ago: $"+Math.round(Database.prices.get(Database.prices.size()-5+i)*100.0)/100.0+" per barrel");
        }
        System.out.println("Fuel price now: $"+Math.round(Database.price*100.0)/100.0+" per barrel (1 barrel = 42 gallons)");
        System.out.println("Fuel prices update every hour");
        try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
    }
    
    public static void buyFuel(){
        seePrice();
        System.out.println("1. 1,000 gallons\n\n2. 10,000 gallons");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        if(response.contains("1") || response.contains("1000")){
            System.out.println("Buy 1,000 gallons of fuel for $"+Math.round(Database.price*23.8*100.0)/100.0+"?");
            String thousand = scanner.nextLine().trim().toLowerCase();
            if(thousand.contains("y")&&Database.money<Database.price*23.8){System.out.println("You can't afford this");return;}
            if(thousand.contains("y")){
                Database.money-=Database.price*23.8;
                System.out.println("Transaction successful");
                Database.fuel+=1000.0;
            }
            else{
                System.out.println("Cancelling transaction....");
                return;
            }

        }
        if(response.contains("2") || response.contains("urc")){
            System.out.println("Buy 10,000 gallons of fuel for $"+Math.round(Database.price*238.1*100.0)/100.0+"?");
            String thousand = scanner.nextLine().trim().toLowerCase();
            if(thousand.contains("y")&&Database.money<Database.price*238.1){System.out.println("You can't afford this");return;}
            if(thousand.contains("y")){
                Database.money-=Database.price*238.1;
                System.out.println("Transaction successful");
                Database.fuel+=10000.0;
            }
            else{
                System.out.println("Cancelling transaction....");
                return;
            }
        }
    }
    public static void sellFuel(){
        seePrice();
        System.out.println("1. 1,000\n\n2. 10,000");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(response.contains("1") || response.contains("1000")){
            System.out.println("Sell 1,000 gallons of fuel for $"+Math.round(Database.price*23.8*100.0)/100.0+"?");
            String thousand = scanner.nextLine().trim().toLowerCase();
            if(thousand.contains("y")&&Database.fuel<1000.0){System.out.println("You don't have enough fuel to sell");return;}
            if(thousand.contains("y")){
                Database.money+=Database.price*23.8;
                System.out.println("Transaction successful");
                Database.fuel-=1000.0;
            }
            else{
                System.out.println("Cancelling transaction....");
                return;
            }

        }
        if(response.contains("2") || response.contains("urc")){
            System.out.println("Sell 10,000 gallons of fuel for $"+Math.round(Database.price*238.1*100.0)/100.0+"?");
            String thousand = scanner.nextLine().trim().toLowerCase();
            if(thousand.contains("y")&&Database.fuel<10000.0){System.out.println("You don't have enough fuel to sell");return;}
            if(thousand.contains("y")){
                Database.money+=Database.price*238.1;
                System.out.println("Transaction successful");
                Database.fuel-=10000.0;
            }
            else{
                System.out.println("Cancelling transaction....");
                return;
            }
        }
          
    }
    
    
}
