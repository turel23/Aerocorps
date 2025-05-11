import java.io.Serializable;
import java.util.ArrayList;


public abstract class Aircraft implements Serializable{
    private static final long serialVersionUID = 1L;
    private String manufacturer;
    private String model;
    private double fuel;
    private int capacity;
    private double price;
    private final int burn;
    private final int ceiling;
    private final double mach;
    public static ArrayList<Aircraft> flightsinflight = new ArrayList<>();
    
    private boolean inflight;
    private String registration;

    public Aircraft(String manufacturer, String model, double fuel, int capacity, double price, int burn, int ceiling, double mach){
        this.manufacturer = manufacturer;
        this.model=model;
        this.fuel=fuel;
        this.capacity=capacity;
        this.price=price;
        this.burn=burn;
        this.ceiling=ceiling;
        this.mach=mach;
        this.registration= "N"+(int)(10000+Math.random()*90000);
    }
    public void setReg(){
        this.registration= "N"+(int)(10000+Math.random()*90000);
    }
    public String getReg(){
        return this.registration;
    }
    public Aircraft(Aircraft aircraft, boolean inflight){
        this.manufacturer = getManufacturer();
        this.model=getModel();
        this.fuel=getFuel();
        this.capacity=getCapacity();
        this.price=getPrice();
        this.burn=getFuelEfficiency();
        this.ceiling=getCeiling();
        this.mach=getMach();
        this.inflight=inflight;
    }
    public Aircraft(Aircraft aircraft, String registration){
        this.manufacturer = getManufacturer();
        this.model=getModel();
        this.fuel=getFuel();
        this.capacity=getCapacity();
        this.price=getPrice();
        this.burn=getFuelEfficiency();
        this.ceiling=getCeiling();
        this.mach=getMach();
        this.registration = registration;
    }
    

    public String getManufacturer(){
        return manufacturer;
    }
    public String getModel(){
        return model;
    }
    public void tripFuel(double duration){
        fuel-=(double)(duration*burn);
    }
    public void refuel(double gallon){
        fuel+=gallon;
    }
    public int getFuelEfficiency(){
        return burn;
    }
    public double getPrice(){
        return price;
    }

    public int getCeiling(){
        return ceiling;
    }
    public String getName(){
        return model;
    }
    public double getFuel(){
        return fuel;
    }
    public int getCapacity(){
        return capacity;
    }
    public double getMach(){
        return mach;
    }
    public int amountofPlanes(Aircraft n){
        int count=0;
        for(int i=0; i<Database.aircrafts.size(); i++){
            if(ifPlane(Database.aircrafts.get(i).getModel())){
                count++;
            }
        }
        return count;
    }

    abstract boolean ifPlane(String n);
    public abstract Aircraft clone();
}
