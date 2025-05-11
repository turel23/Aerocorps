import java.io.Serializable;
import java.util.ArrayList;


public class RandomEmergency implements Serializable{
    public static double lostmoney=0.0;
    private boolean emergencyland;
    private String issue;
    private Flight flight;
    private boolean crash;
    //not implemented yet
    public static ArrayList<RandomEmergency> emergencies = new ArrayList<>();

    public RandomEmergency(Flight flight){
        this.flight = flight;
        boolean incident = false;
        this.emergencyland = false;
        if(Math.random()<0.05){
            incident = true;
        }
        if(incident){
            if(Math.random()<0.1){
                this.issue = "engine failure";
                if(Math.random()<0.5){
                    this.issue+=", compression stall";
                }
                this.emergencyland = true;
            }
            else{
                if(Math.random()<0.9){
                    this.issue="bad weather at destination due to ";
                    if(Math.random()<0.3){
                        this.issue+="high temperatures";
                    }
                    double storm = Math.random();
                    if(storm>0.3 && storm<0.6){
                        this.issue +="stormy conditions";
                    }
                    else{
                        this.issue +="extreme storm conditions";
                        this.emergencyland = true;
                    }
                }
            }
        }
        else{
            if(Math.random()>0.2){
                if(Math.random()>0.5){
                    this.issue="light turbulence";
                }
                else{
                    if(Math.random()>0.3){
                        this.issue="moderate turbulence";
                    }
                    else if(Math.random()<0.2){
                        this.issue = "strong turbulence";
                    }
                    else{
                        this.issue = "extreme turbulence";
                        this.emergencyland = true;
                    }
                }
            }
        }
        if(this.emergencyland){
            if(Math.random()<0.08){
                this.crash = true;
            }
        }
    }
    public Flight getFlight(){
        return this.flight;
    }
    public static RandomEmergency PairFlight(Flight flight){
        for(int i=0; i<emergencies.size(); i++){
            if(emergencies.get(i).getFlight().getNumber().equals(flight.getNumber())){
                return emergencies.get(i);
            }
        }
        return null;
    }
    public boolean ifEmerLand(){
        return this.emergencyland;
    }
    public boolean ifCrash(){
        return this.crash;
    }
    public String getIssue(){
        return this.issue;
    }
}
