import java.io.Serializable;

public class Route extends RouteCalculator implements Serializable{
    private static final long serialVersionUID = 1L;
    private Airport origin;
    private Airport destination;
    private String flightnumber;
    public Route(Airport origin, Airport destination, String flightnumber){
        this.origin= origin;
        this.destination=destination;
        this.flightnumber=flightnumber;
    }
    public Airport getOrigin(){
        return origin;
    }
    public Airport getDestination(){
        return destination;
    }
    public String getNumber(){
        return flightnumber;
    }
    public double getRange(){
        return calculateDistanceNM(origin, destination);
    }

}
