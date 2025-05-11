public final class B777 extends Aircraft{
    private final double price =352.16; //million
    private final int burn= 2500; //gallon per hour
    private double fuel = 47890; //inital fuel level
    private final int ceiling = 43000;

    public B777(){
        super("Boeing", "B777", 47890, 350, 352, 2500, 43000, 0.84);
    }

    @Override
    public boolean ifPlane(String n){
        boolean ans=false;
        if(n.contains("777")){
            ans=true;
        }
        return ans;
    }
    @Override
    public B777 clone(){
        B777 copy = new B777();
        return copy;
    }
}
