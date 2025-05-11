public final class A320 extends Aircraft{
    private final double price =101; //million
    private final int burn= 900; //gallon per hour
    private double fuel = 6400; //inital fuel level
    private final int ceiling = 39000;

    public A320(){
        super("Airbus", "A320neo", 6400, 170, 101.2, 900, 39000, 0.78);
    }

    @Override
    public boolean ifPlane(String n){
        boolean ans=false;
        if(n.contains("320")){
            ans=true;
        }
        return ans;
    }
    @Override
    public A320 clone(){
        A320 copy = new A320();
        return copy;
    }

}
