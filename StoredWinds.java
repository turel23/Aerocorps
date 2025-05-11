import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class StoredWinds implements Serializable{
    private int[] winds;
    private String number;
    private static LocalDateTime expire=LocalDateTime.now().minusHours(10);
    public static ArrayList<StoredWinds> obj = new ArrayList<StoredWinds>(1);

    public StoredWinds(Route route, int[] n, LocalDateTime e){
        this.number = (route.getNumber());
        this.winds = n;
        this.expire = e;
    }
    public void setwinds(int[] n){
        this.winds = n;
    }
    public String getnumber(){
        return number;
    }
    public int[] getwinds(){
        return this.winds;
    }
    public void settime(){
        this.expire = LocalDateTime.now().plusHours(3);
    }
    public LocalDateTime getexpire(){
        return this.expire;
    }
    public static void uploadwinds(Route route){
        boolean found =false;
        for(int i=0; i<obj.size(); i++){
            if(obj.get(i).getnumber()==route.getNumber()){
                found = true;
                Duration duration = Duration.between(obj.get(i).getexpire(), LocalDateTime.now());
                int hoursdifference = (int)duration.toHours();
                if(hoursdifference>=3){
                    int levels = 16;
                    int pm= 1;
                    if(Math.random()>=0.5){
                        pm=-1;
                    }
                    int[] flightwind = new int[levels];
                    for(int p=0; p<levels; p++){
                        flightwind[p]=(int)(Math.random()*100*pm);
                    }
                    obj.get(i).setwinds(flightwind);
                    obj.get(i).settime();
                }

            }
        }
        if(!found){
            int levels = 16;
            int pm= 1;
            if(Math.random()>=0.5){
                pm=-1;
            }
            int[] flightwind = new int[levels];
            for(int i=0; i<levels; i++){
                flightwind[i]=(int)(Math.random()*100*pm);
            }
            obj.add(new StoredWinds(route, flightwind, LocalDateTime.now().plusHours(3)));
        }
        
    }
    public static void printWinds(Route route){
        int levels = 16;
        int pm= 1;
        int fl=30000;
        for(int i=0; i<obj.size(); i++){
            if(obj.get(i).getnumber().equals(route.getNumber())){
                for(int o=0; o<obj.get(i).getwinds().length; o++){
                    System.out.println(fl+1000*o+": "+obj.get(i).getwinds()[o]+" kts");
                }
            }
        }
    }
    public static int getOptimumFL(Route route){
        int max=-200;
        int level=0;
        for(int i=0; i<obj.size(); i++){
            if(obj.get(i).getnumber().equals(route.getNumber())){
                for(int o=0; o<obj.get(i).getwinds().length; o++){
                    if(max<obj.get(i).getwinds()[o]){
                        max=obj.get(i).getwinds()[o];
                        level=o;
                    }
                }
            }
        }
        return 300+10*level;
    }
    
}
