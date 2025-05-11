public class B787 extends Aircraft{
    private final double price =239.3; //million
    private final int burn= 2900; //gallon per hour
    private double fuel = 33340; //inital fuel level
    private final int ceiling = 43000;

    public B787(){
        super("Boeing", "B787", 33340, 300, 239.3, 2900, 43000, 0.84);
    }

    @Override
    public boolean ifPlane(String n){
        boolean ans=false;
        if(n.contains("787")){
            ans=true;
        }
        return ans;
    }
    @Override
    public B787 clone(){
        B787 copy = new B787();
        return copy;
    }
}
