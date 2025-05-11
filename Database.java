//kinda finished
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;



public class Database implements Serializable{
    //memory
    //economic database
    public static double price=88.3;
    public static ArrayList<Double> prices = new ArrayList<Double>();
    //player database
    public static double fuel=1000000.0;
    public static long money = 1000000000L;
    public static ArrayList<Aircraft> aircrafts = new ArrayList<>();
    public static ArrayList<Aircraft> availableaircraft = aircrafts;
    //database database
    public static LocalDateTime recent =LocalDateTime.now().minus(5, ChronoUnit.DAYS);
    public static ArrayList<Airport> database = new ArrayList<>();
    public static ArrayList<Route> routes = new ArrayList<>();
    public static ArrayList<Flight> allflights = new ArrayList<>();
    public static ArrayList<Aircraft> collection = new ArrayList<>();
    static{
        collection.add(new B777());
        collection.add(new A320());
        collection.add(new B737());
        collection.add(new B787());
    }

    private Database(){}

    public static void menuDatabase(){
        System.out.println("Bringing up database...");
        System.out.println("~~~DATABASE~~~\n\n1. Add Airports ➕\n\n2. Remove Airports ➖\n\n3. Display Airports \n\n4. Display Routes\n\n5. Display All Aircraft\n\n6. Display My Aircraft\n\n7. Logbook\n\n8. Stats");
        Scanner scanner = new Scanner(System.in);   
        String response1 = scanner.nextLine().trim().toLowerCase();
        if(response1.contains("1")||response1.contains("add")){
            addAirport();
        }
        if(response1.contains("2")||response1.contains("emo")){
            removeAirport();
        }
        if(response1.contains("3")||response1.contains("ata")){
            displayDatabase();
        }
        if(response1.contains("4")||response1.contains("out")){
            displayRoutes();
        }
        if(response1.contains("5")||response1.contains("air")){
            displayCollection();
        }
        if(response1.contains("6")||response1.contains("my")){
            displayAircraft();
        }
        if(response1.contains("7")||response1.contains("ogb")){
            Flight.displayLogBook();
        }
        if(response1.contains("8")||response1.contains("one")){
            seeMoney();
            seeFuel();
        }
    }
    public static void addAirport(){
        boolean continuity=true;
        double double2=0.0;
        double double3=0.0;
        while(continuity){
            System.out.println("Airport code (ICAO):");
            Scanner scanner = new Scanner(System.in);   
            String response1 = scanner.nextLine().trim().toLowerCase();
            System.out.println("Latitude:");
            String response2 = scanner.nextLine().trim().toLowerCase();
            try{
                double2 = Double.parseDouble(response2);
                continuity=false;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid string format");
                continuity=true;
            }
            System.out.println("Longitude:");
            String response3 = scanner.nextLine().trim().toLowerCase();
            try{
                double3 = Double.parseDouble(response3);
                continuity=false;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid string format");
                continuity=true;
            }
            if(continuity==false){
                database.add(new Airport(response1.toUpperCase(), double2, double3));
                System.out.println("Successfully added "+response1.toUpperCase()+" to the database.");
            }
        }
        
    }
    public static void removeAirport(){
        System.out.println("What airport would you like to remove from the database? (ICAO code)");
        Scanner scanner = new Scanner(System.in);   
        String response = scanner.nextLine().trim().toLowerCase();
        for(int i=0; i<database.size(); i++){
            if(database.get(i).getAirport().equals(response.toUpperCase())){
                database.remove(i);
                System.out.println("Successfully removed "+response.toUpperCase()+" from the database.");
            }
        }
    }
    public static void displayDatabase(){
        System.out.println("\nDATABASE:");
        for(int i=0; i<database.size(); i++){
            System.out.println(database.get(i).getAirport()+", Latitude: "+database.get(i).getLatitude()+", Longitude: "+database.get(i).getLongitude());
        }
        if(database.size()==0){
            System.out.println("No Airports in database");
        }
    }
    public static Aircraft getAircraft(String n){
        for(int i=0; i<aircrafts.size(); i++){
            if(aircrafts.get(i).ifPlane(n)){
                return aircrafts.get(i);
            }
        }
        return null;
    }
    public static ArrayList<Airport> getDatabase(){
        return database;
    }
    public static ArrayList<Route> getRoutes(){
        return routes;
    }
    public static void addRoute(Route route){
        routes.add(route);
    }
    public static boolean displayRoutes(){
        for(int i=0; i<routes.size(); i++){
            System.out.println(routes.get(i).getOrigin().getAirport()+" to "+routes.get(i).getDestination().getAirport()+", flight number: "+routes.get(i).getNumber());
        }
        if(routes.size()==0){
            System.out.println("No routes created");
            return true;
        }
        return false;
    }
    public static void displayCollection(){
        for(int i=0; i<collection.size(); i++){
            System.out.println(collection.get(i).getName()+"- Ceiling: "+collection.get(i).getCeiling()+ " feet, efficiency "+collection.get(i).getFuelEfficiency()+" gallons/hour, fuel: "+(int)collection.get(i).getFuel()+" gallons, capacity: "+collection.get(i).getCapacity()+" passengers, price: $"+collection.get(i).getPrice()+ " million.");
        }
        if(collection.size()==0){
            System.out.println("No aircraft in database");
        }
        try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
    }
    public static void displayAircraft(){
        for(int i=0; i<aircrafts.size(); i++){
            System.out.println(aircrafts.get(i).getName()+"- Ceiling: "+aircrafts.get(i).getCeiling()+ " feet, efficiency "+aircrafts.get(i).getFuelEfficiency()+" gallons/hour, fuel: "+(int)aircrafts.get(i).getFuel()+" gallons, capacity: "+aircrafts.get(i).getCapacity()+" passengers, registration: "+aircrafts.get(i).getReg());
        }
        if(aircrafts.size()==0){
            System.out.println("No aircraft owned");
        }
    }
    public static void seeMoney(){
        if(money<1000L){
            System.out.println("Money: $"+Math.round(money*10.0)/10.0);
        }
        if(money>=1000L&&money<1000000L){
            System.out.println("Money: $"+Math.round(money/100)/10.0+"K");
        }
        else if(money>=1000000L&&money<1000000000L){
            System.out.println("Money: $"+Math.round(money/100000)/10.0+"M");
            
        }
        else if(money>=1000000000L&&money<1000000000000L){
            System.out.println("Money: $"+Math.round(money/100000000)/10.0+"B");
        }
    }
    public static void seeFuel(){
        System.out.println("Fuel: "+Math.round(fuel*100.0)/100.0+" gallons");
    }
    public static void refresh(){
        for(int i=0; i<Flight.getAllflights().size(); i++){
            if(LocalDateTime.now().isAfter(Flight.getETAforflight(Flight.getAllflights().get(i)))){
                if(!Flight.getAllflights().get(i).ifadded() ){
                    if(!RandomEmergency.PairFlight(Flight.getAllflights().get(i)).ifCrash()){
                        availableaircraft.add(allflights.get(i).getAircraft()); 
                    }
                    Flight.getAllflights().get(i).setAdded();
                    if(RandomEmergency.PairFlight(Flight.getAllflights().get(i)).ifEmerLand()){
                        RandomEmergency.lostmoney=500000.0+Math.random()*500000.0;
                        Database.money-=RandomEmergency.lostmoney;
                        NOTAMS.messages =true;
                    }
                    if(RandomEmergency.PairFlight(Flight.getAllflights().get(i)).ifCrash()){
                        Database.aircrafts.remove(Flight.getAllflights().get(i).getAircraft());
                        NOTAMS.messages =true;
                    }
                }
            }
        }
        if(Demand.demands.size()!=0){
            for(int o=0; o<Demand.demands.size(); o++){
                if(Demand.demands.get(o)!=null){
                    Demand.demands.get(o).refreshDemand();
                }
            }
        }
    }
    public static void DisplayAvailable(){
        for(int i=0; i<availableaircraft.size(); i++){
            System.out.println(availableaircraft.get(i).getName()+"- Ceiling: "+availableaircraft.get(i).getCeiling()+ " feet, efficiency "+availableaircraft.get(i).getFuelEfficiency()+" gallons/hour, fuel: "+availableaircraft.get(i).getFuel()+" gallons, capacity: "+availableaircraft.get(i).getCapacity()+" passengers, registration: "+aircrafts.get(i).getReg());
        }
        if(availableaircraft.size()==0){
            System.out.println("No aircraft available");
        }
    }
    public static Aircraft getAvailable(String n){
        for(int i=0; i<availableaircraft.size(); i++){
            if(availableaircraft.get(i).ifPlane(n)){
                return availableaircraft.get(i);
            }
        }
        return null;
    }

}
