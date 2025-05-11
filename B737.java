public class B737 extends Aircraft{
    private final double price =99.7; //million
    private final int burn= 850; //gallon per hour
    private double fuel = 6875; //inital fuel level
    private final int ceiling = 41000;

    public B737(){
        super("Boeing", "B737", 6875, 172, 99.7, 850, 41000, 0.85);
    }

    @Override
    public boolean ifPlane(String n){
        boolean ans=false;
        if(n.contains("737")){
            ans=true;
        }
        return ans;
    }
    @Override
    public B737 clone(){
        B737 copy = new B737();
        return copy;
    }

}
