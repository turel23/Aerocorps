import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlightGUI {
    public static void menuFlight(){
        System.out.println("\n1. Create a route 🌎\n\n2. Schedule flight ✈️\n\n3. Flight status");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        try {
            Thread.sleep(300);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        if(response.contains("1")||response.contains("cr")){
            routeCreation();
        }
        if(response.contains("2")||response.contains("ed")){
            scheduleFlight();
        }
        if(response.contains("3")||response.contains("at")){
            flightStatusGUI();
        }
    } 
    public static void routeCreation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPreparing for route creation...");
        boolean continuity=true;
        boolean success = false;
        Airport origin = new Airport(null, 0,0);
        Airport destination = new Airport(null, 0,0);
        if(Database.database.size()==0){
            System.out.println("You have no airports in the database");
            try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            return;
        }
            while(continuity){
                System.out.println("\nOrigin?");
                String response = scanner.nextLine().trim().toLowerCase();
                if(response==""){System.out.println("Cancelling route creation....");return;}
                for(int i=0; i<Database.getDatabase().size(); i++){
                    if(Database.getDatabase().get(i).getAirport().contains(response.toUpperCase())){
                        origin.setAirport(Database.getDatabase().get(i).getAirport());
                        origin.setLatitude(Database.getDatabase().get(i).getLatitude());
                        origin.setLongitude(Database.getDatabase().get(i).getLongitude());
                        success=true;
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(success){
                    System.out.println("\nDestination?");
                    String response1 = scanner.nextLine().trim().toLowerCase();
                    if(response1==""){System.out.println("Cancelling route creation....");return;}
                    for(int i=0; i<Database.getDatabase().size(); i++){
                        if(Database.getDatabase().get(i).getAirport().contains(response1.toUpperCase())){
                            destination.setAirport(Database.getDatabase().get(i).getAirport());
                            destination.setLatitude(Database.getDatabase().get(i).getLatitude());
                            destination.setLongitude(Database.getDatabase().get(i).getLongitude());
                            continuity=false;
                        }
                    }
                    if(continuity==true){System.out.println("Invalid Airport! It may not be in the database.");}
                }
                else{System.out.println("Invalid Airport! It may not be in the database.");}
            
                if(continuity==false){
                    System.out.println("\nThe ground distance is "+RouteCalculator.calculateDistanceNM(origin, destination)+" Nautical Miles.");
                    System.out.println("Flight Number: (Ex. UAL1234, no duplicates)");
                    String res = scanner.nextLine().trim().toUpperCase();
                    String number = res;
                    if(number==""){System.out.println("Cancelling route creation....");return;}
                    System.out.println("Creating route...");
                    Database.addRoute(new Route(origin, destination, number));
                    Demand thedemand = new Demand(new Route(origin, destination, number), LocalDateTime.now());
                    Demand.demands.add(thedemand);
                    System.out.println("\nThe route has been created, and is now saved as "+number);
                }
            }
    }
    public static void scheduleFlight(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm");
        System.out.println("Loading routes....");
        try {
            Thread.sleep(300);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        if(Database.displayRoutes()){
            return;
        }
        try {
            Thread.sleep(300);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        System.out.println("Select a route (Flight number):");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toUpperCase();
        if(response==""){System.out.println("Cancelling Flight....");return;}
        boolean routesuccess=false;
        int routeindex =0;
        Route route =null;
        for(int i=0; i<Database.getRoutes().size(); i++){
            if(Database.getRoutes().get(i).getNumber().contains(response)){
                route = Database.getRoutes().get(i);//get i
                routesuccess=true;
                routeindex=i;
            }
        }
        int level=0;
        String response2="";
        if(routesuccess){
            System.out.println("This route has a distance of "+Database.getRoutes().get(routeindex).getRange()+" NM");
            try {
                Thread.sleep(300);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            
            
            
            System.out.println("Assign an aircraft to the flight:\n~~~FLEET~~~");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Database.DisplayAvailable();
            while(true){
                response2 = scanner.nextLine().trim().toUpperCase();
                if(response2==""){return;}
                if(Database.getAvailable(response2)==null){System.out.println("Sorry, that was not a valid aircraft.");}
                //System.out.println(Flight.getFuelRequired(Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), Database.getRoutes().get(routeindex).getRange(), Database.getAircraft(response2).getFuelEfficiency()));
                else{break;}
            }
            
        }
        while(true){
            System.out.println("Retrieving winds for your route...");
            try {
                Thread.sleep(200);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            StoredWinds.uploadwinds(route);
            StoredWinds.printWinds(route);
            LocalDateTime now1= LocalDateTime.now().plusHours(3);
            String date = now1.format(formatter);
            System.out.println("(VALID UNTIL "+date+" UTC)\n");
            System.out.println("The optimal flight level is FL"+StoredWinds.getOptimumFL(route));
            try {
                Thread.sleep(200);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            while(true){    
                System.out.println("Assign the flight level for your flight: (30000 to 45000, no commas)");
                String altitude = scanner.nextLine().trim().toLowerCase();
                if(altitude==""){System.out.println("Cancelling flight...."); return;}
                level =0;
                try {
                    Integer num = Integer.valueOf(altitude);
                    if(num>=30000 && num<=45000 && num%1000==0){
                        level = num;
                        break;
                    }
                    else{
                        System.out.println("Invalid format. Check range");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid string format. Cannot convert to integer.");
                }
            }
            double fuel = Flight.getFuelRequired(Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), Database.getRoutes().get(routeindex).getRange(), Database.getAircraft(response2).getFuelEfficiency());
            if(response2!=""&&Database.getAircraft(response2)!=null&&Database.getAircraft(response2).getCeiling()>=level&&Flight.getFuelRequired(Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), Database.getRoutes().get(routeindex).getRange(), Database.getAircraft(response2).getFuelEfficiency())<=Database.getAircraft(response2).getFuel()){               
                if(Database.fuel<fuel){
                    System.out.println("You only have "+Math.round(Database.fuel*100.0)/100.0+" gallons of fuel, and the flight requires "+Math.round(Flight.getFuelRequired(Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), Database.getRoutes().get(routeindex).getRange(), Database.getAircraft(response2).getFuelEfficiency())*100.0)/100.0 +" gallons. Try another aircraft or purchase more fuel.");
                    return;
                }
                System.out.println("\nAircraft is certified for this flight. It is estimated to burn about "+fuel+" gallons of fuel.");
                try {
                    Thread.sleep(800);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                while(true){
                    System.out.println("Enter a depature date/time (MM/DD HH:MM)(24 hour zulu)");
                    String response3 = scanner.nextLine().trim().toUpperCase();
                    if(response3==""){System.out.println("Cancelling flight....");return;}
                    if(LocalTimeDateFormat.changeToLocalTime(response3)!=null && LocalTimeDateFormat.changeToLocalTime(response3).isBefore(LocalDateTime.now())){
                        System.out.println("Try a later date");
                        try {
                            Thread.sleep(500);
                          } catch (InterruptedException e) {
                            e.printStackTrace();
                          }
                    }

                    if(LocalTimeDateFormat.changeToLocalTime(response3)!=null && LocalTimeDateFormat.changeToLocalTime(response3).isAfter(LocalDateTime.now())){
                        Flight flight = new Flight(route, Database.getAircraft(response2), LocalTimeDateFormat.changeToLocalTime(response3), level, Flight.getComponent(level), Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)));
                        Database.allflights.add(flight);
                        System.out.println("Estimated Time of Arrival: "+Flight.getETA(route, Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), LocalTimeDateFormat.changeToLocalTime(response3)).format(formatter));
                        try {
                            Thread.sleep(800);
                          } catch (InterruptedException e) {
                            e.printStackTrace();
                          }
                        Database.fuel-=Flight.getFuelRequired(Database.getAircraft(response2).getMach()+Flight.toMach(Flight.getComponent(level)), Database.getRoutes().get(routeindex).getRange(), Database.getAircraft(response2).getFuelEfficiency());
                        Database.availableaircraft.remove(Database.getAircraft(response2));
                        RandomEmergency.emergencies.add(new RandomEmergency(flight));
                            

                        Database.money+=Demand.earning(flight);
                        System.out.println("Flight succesfully created.");
                        try {
                            Thread.sleep(500);
                          } catch (InterruptedException e) {
                            e.printStackTrace();
                          }
                        break;
                    }
                    else if(LocalTimeDateFormat.changeToLocalTime(response3)==null){
                        System.out.println("That was not a valid format of date!");
                        try {
                            Thread.sleep(500);
                          } catch (InterruptedException e) {
                            e.printStackTrace();
                          }
                    }

                }
                break;
            }
            else{System.out.println("Sorry, that was not valid! (The aircraft may have an invalid ceiling for this flight)");}
        }
            
    }

    
    public static void flightStatusGUI(){
        System.out.println("Loading active flights....");
        if(Flight.displayAllFlights()){
            return;
        }
        System.out.println("\nSelect a flight to view (Use the flight number)");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toUpperCase();
        Flight Flight=null;
        for(int i=0; i<Flight.getAllflights().size(); i++){
            if(Flight.getAllflights().get(i).getNumber().equals(response)){
                Flight=Flight.getAllflights().get(i);
            }
        }
        if(Flight!=null){
            flightStatus(Flight);
        }
        else{
            System.out.println("Flight not found");
        }
    }
    public static void flightStatus(Flight flight){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm");
        System.out.println("\nLogging onto Flightradar24....\n");
        String status ="";
        String speed = "";
        LocalDateTime now = LocalDateTime.now();
        if(now.isAfter(flight.getDeparture()) && now.isBefore(Flight.getETAforflight(flight))){
            status = "Enroute to destination, cruising at FL"+Flight.getCruise()+", scheduled to arrive at "+Flight.getETAforflight(flight).format(formatter)+" ✈️";
            speed = "Mach "+flight.getSpeed();
        }
        else if(now.isAfter(flight.getDeparture()) && now.isAfter(Flight.getETAforflight(flight))){
            status = "Arrived at "+flight.getDestination().getAirport()+", landed at "+Flight.getETAforflight(flight).format(formatter)+", parked at the gate 🛬";
            speed = "0 knots";
        }
        else if(now.isBefore(flight.getDeparture()) && now.isBefore(Flight.getETAforflight(flight))){
            status = "At the gate in "+flight.getDestination().getAirport()+" Scheduled for departure at "+flight.getDeparture().format(formatter)+" 🛫";
            speed = "0 knots";
        }
        
        System.out.println("Information for "+flight.getNumber()+"\n--------------------");
        System.out.println("From "+flight.getOrigin().getAirport()+" to "+flight.getDestination().getAirport()+"\n--------------------");
        System.out.println("Scheduled departure "+flight.getDeparture().format(formatter)+", Scheduled arrival "+Flight.getETAforflight(flight).format(formatter)+"\n--------------------");
        System.out.println(status+"\n--------------------");
        System.out.println("Speed: "+speed+"\n--------------------");
        for(int i=0; i<RandomEmergency.emergencies.size(); i++){
            if(RandomEmergency.emergencies.get(i).getFlight().equals(flight)){
                System.out.println("Events: "+RandomEmergency.emergencies.get(i).getIssue());
            }
        }
        try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
    }
}
