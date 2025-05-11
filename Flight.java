import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Flight extends Route implements Serializable{
    private static final long serialVersionUID = 1L;
    private Route route;
    private Aircraft aircraft;
    private LocalDateTime departureTime;
    private int component;
    private static int cruiseLevel;
    private  double speed;
    private static final String[] winds = new String[16];
    private static final int[] flwinds = new int[16];
    private boolean alreadyadded;

    
    public Flight(Route route, Aircraft aircraft, LocalDateTime departureTime, int cruiseLevel, int component, double speed){
        super(route.getOrigin(), route.getDestination(), route.getNumber());
        this.speed = speed;
        this.route=route;
        this.aircraft=aircraft;
        this.departureTime=departureTime;
        this.cruiseLevel=cruiseLevel;
        this.component=component;
        this.aircraft = aircraft;
        this.alreadyadded=false;
    }
    
    public boolean ifadded(){
        return this.alreadyadded;
    }
    public void setAdded(){
        this.alreadyadded=true;
    }
    public Aircraft getAircraft(){
        return aircraft;
    }
    public static int getCruise(){
        return cruiseLevel/100;
    }
    public static int getComponent(int a){
        int i = (a-30000)/1000;
        return flwinds[i];
    }
    public static double toMach(int i){
        return (double)(i/666.7);
    }
    public static double getFuelRequired(double mach, double distance, double burn){
        return Math.round((distance/(mach*666.7))*burn*10.0)/10.0;
    }
    public static ArrayList<Flight> getAllflights(){
        return Database.allflights;
    }
    public static LocalDateTime getETA(Route route, double speed, LocalDateTime departure){
        double flighttime = route.getRange()/(speed*666.7);
        int hours = (int)(flighttime);
        int minutes=(int)((flighttime-hours)*60);
        return departure.plusHours(hours).plusMinutes(minutes+40);
    }
    public double getSpeed(){
        return speed;
    }
    public Route getRoute(){
        return route;
    }
    public LocalDateTime getDeparture(){
        return departureTime;
    }
    public static LocalDateTime getETAforflight(Flight flight){
        double flighttime = flight.getRoute().getRange()/(flight.getSpeed()*666.7);
        int hours = (int)(flighttime);
        int minutes=(int)((flighttime-hours)*60);
        return flight.getDeparture().plusHours(hours).plusMinutes(minutes+40);
    }
    public static boolean displayAllFlights(){
        for(int i=0; i<Database.allflights.size(); i++){
            System.out.println(Database.allflights.get(i).getNumber()+" "+Database.allflights.get(i).getOrigin().getAirport()+" - "+Database.allflights.get(i).getDestination().getAirport()+", type: "+Database.allflights.get(i).getAircraft().getModel());
        }
        if(Database.allflights.size()==0){
            System.out.println("You have no flights");
            return true;
        }
        return false;
    }
    public static void displayLogBook(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm");
        for(int i=0; i<Database.allflights.size(); i++){
            System.out.println(Database.allflights.get(i).getNumber()+" "+Database.allflights.get(i).getOrigin().getAirport()+" - "+Database.allflights.get(i).getDestination().getAirport()+", FL"+Database.allflights.get(i).getCruise()+", departed "+Database.allflights.get(i).getDeparture().format(formatter)+", arrived "+getETAforflight(Database.allflights.get(i)).format(formatter)+", earned $"+Math.round(Demand.earning(Database.allflights.get(i))*100.0)/100.0+", seated "+Demand.getPassengers(Database.allflights.get(i))+" passengers. ");
            if(Demand.ifOverbooked(Database.allflights.get(i))){
                System.out.print("This flight was overbooked.");
            }
        }
        if(Database.allflights.size()==0){
            System.out.println("No logged flights");
        }
    }
}
