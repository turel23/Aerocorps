import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDateTime;


public class Demand implements Serializable{
    private static final long serialVersionUID = 1L;
    public static ArrayList<Demand> demands = new ArrayList<Demand>(0);
    private double demandpercent;
    private LocalDateTime time;
    boolean overbooked;
    private Route route;
    
    public Demand(Route route, LocalDateTime time){
        this.demandpercent = Math.random();
        this.route = route;
        this.overbooked=false;
        if(Math.random()<0.8){
            this.demandpercent=Math.random()*0.2+0.6;
        }
        else{
            if(Math.random()<0.8){
                this.demandpercent=Math.random()*0.25+0.8;
            }
            else{
                this.demandpercent=Math.random()*0.5+0.1;
            }
        }
        if(this.demandpercent>1.0){
            this.overbooked=true;
            this.demandpercent=1.0;
        }
        this.time=time.plusDays(1);
    }
    public void refreshDemand(){
        if(LocalDateTime.now().isAfter(this.time)){
            if(Math.random()<0.8){
                this.demandpercent=Math.random()*0.2+0.6;
            }
            else{
                if(Math.random()<0.8){
                    this.demandpercent=Math.random()*0.25+0.8;
                }
                else{
                    this.demandpercent=Math.random()*0.5+0.1;
                }
            }
            if(this.demandpercent>1.0){
                this.overbooked=true;
                this.demandpercent=1.0;
            }
            this.time=time.plusDays(1);
        }
    }
    public static int getPassengers(Flight flight){
        int loc = 0;
        for(int i=0; i<demands.size(); i++){
            if(demands.get(i).ifRoute(flight.getRoute())){
                loc=i;
                
            }
        }
        double demand = demands.get(loc).demandpercent;
        return (int)(flight.getAircraft().getCapacity()*demand);
    }
    public static boolean ifOverbooked(Flight flight){
        int loc = 0;
        for(int i=0; i<demands.size(); i++){
            if(demands.get(i).ifRoute(flight.getRoute())){
                loc=i;
                
            }
        }
        return demands.get(loc).overbooked;
    }
    public Route getRoute(){
        return this.route;
    }
    public boolean ifRoute(Route route){
        if(this.route == route){
            return true;
        }
        return false;
    }
    public static double earning(Flight flight){
        int loc = 0;
        for(int i=0; i<demands.size(); i++){
            if(demands.get(i).ifRoute(flight.getRoute())){
                loc=i;
                
            }
        }
        double demand = demands.get(loc).demandpercent;
        int passengers = (int)(flight.getAircraft().getCapacity()*demand);
        double earn = passengers*flight.getRange()*6.75;
        return earn;
    }
}
