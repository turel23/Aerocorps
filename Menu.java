import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Menu {

    public static void main(String[] args){
        
        introduction();
        while(true){
            Database.refresh();
            ifMessages();
            try {
                Thread.sleep(500);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            int ans= menu();
            try {
                Thread.sleep(300);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            if(ans==1){
                FlightGUI.menuFlight();
            }
            else if (ans==2) {
                AircraftGUI.MenuAircraft();
            }
            else if (ans==3) {
                EconomyGUI.MenuEconomy();
            }
            else if (ans==4) {
                Database.menuDatabase();
            }
            else if (ans==5) {
                try {
                    ProgressManager.saveAll();
                    System.out.println("✈️ Saved successfully!");
                } catch (IOException e) {
                        System.err.println("⚠️ Failed to save data: " + e.getMessage());
                }  
            }
            else if (ans==6) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm");
                LocalDateTime now1= LocalDateTime.now();
                String date = now1.format(formatter);
                System.out.println("It is currently "+date+" in zulu time");
                try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
            }
            else if (ans==7) {
                NOTAMS.NOTAMSmenu();
            }
        }
    }
    public static void introduction(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm");
        LocalDateTime now1= LocalDateTime.now();
        String date = now1.format(formatter);
        System.out.println("\n[Aerocorps]\n");
        System.out.println("Version 1.1.9 - Added money and economy and saving system");
        System.out.println(date);
        LocalTimeDateFormat.changeToLocalTime(date);
        loadNew();
        tips(); //can remove if annoying
        
    }
    public static int menu(){
        int ans=0;
        System.out.println("~~~~~~~~~~~~~~~\n\n✈️  GAME MENU  ✈️");
        System.out.println("\n1. Flights ✈️\n\n2. Aircraft 🛒\n\n3. Economy 📈\n\n4. Database 📍\n\n5. Save Progress 📁(experimental) \n\n6. Current Time ⏰\n\n7. NOTAMS");
        Scanner scanner = new Scanner(System.in);   
        String response = scanner.nextLine().trim().toLowerCase();
        if(response.contains("1")||response.contains("fl")){
            ans=1;
        }
        if(response.contains("2")||response.contains("cra")){
            ans=2;
        }
        if(response.contains("3")||response.contains("on")){
            ans=3;
        }
        if(response.contains("4")||response.contains("at")){
            ans=4;
        }
        if(response.contains("5")||response.contains("av")){
            ans=5;
        }
        if(response.contains("6")||response.contains("im")){
            ans=6;
        }
        if(response.contains("7")||response.contains("not")){
            ans=7;
        }
        if(response.contains("0")){
            Database.money=1000000000L;
            Database.fuel = 1000000.0;
            Database.aircrafts.addAll(Database.collection);
        }
        return ans;

    }
    public static void loadNew(){
        System.out.println("\n1. New game\n\n2. Load existing");
        Scanner scanner = new Scanner(System.in);   
        String response = scanner.nextLine().trim().toLowerCase();
        if(response.contains("2") || response.contains("oa")){
            try {
                ProgressManager.loadAll();
            } catch (Exception e) {
                System.out.println("");
            }
        }
    }
    
    public static void tips() {
        ArrayList<String> tips = new ArrayList<>();
        tips.add("Use ICAO codes when adding airports");
        tips.add("Did you know: the prices in-game are based off of real-world prices?");
        tips.add("Use the coordinates of real-world airports when adding airports");
        tips.add("Don't try to refresh the game for better fuel prices, it won't work");
        tips.add("Invest in jet fuel for profit");
        tips.add("There is a secret code to unlock infinite money");
        tips.add("Inputs are not case sensitive");
        tips.add("All times are presented in UTC");
        tips.add("All times are based on real world time");
        tips.add("There is a small chance your plane could crash!");
        tips.add("You can lose up to a million from aircraft accidents and emergency landings");
        tips.add("It is really rare for a plane to crash");
        tips.add("When typing in inputs, keep it basic (eg. 777, 36000)");
        tips.add("24-hour clocks are used here");
        

        // Create list of indices (0 to tips.size()-1)
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < tips.size(); i++) {
            indices.add(i);
        }

        // Shuffle for random order
        Collections.shuffle(indices);

        // Display 4 random tips with loading effect
        for (int i = 0; i < 4 && !indices.isEmpty(); i++) {
            int progress = (i + 1) * 25; // 25%, 50%, 75%, 100%
            int randomIndex = indices.remove(0); // Get and remove first shuffled index
            
            System.out.println("Loaded " + progress + "%....." + tips.get(randomIndex));
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void ifMessages(){
        if(NOTAMS.messages){
            System.out.println("\n\n⚠️You have important messages in NOTAMS. Please check your inbox");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
