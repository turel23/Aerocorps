import java.io.Serializable;

public class Airport implements Serializable{
    private static final long serialVersionUID = 1L;
    private String code;
    private double latitude;
    private double longitude;
    public Airport(String code, double latitude, double longitude){
        this.code=code;
        this.latitude=latitude;
        this.longitude=longitude;
    }
    public double getLongitude(){
        return longitude;
    }
    public double getLatitude(){
        return latitude;
    }
    public String getAirport(){
        return code;
    }
    public void setAirport(String n){
        code=n;
    }
    public void setLatitude(double n){
        latitude=n;
    }
    public void setLongitude(double n){
        longitude=n;
    }
}
