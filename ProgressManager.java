import java.io.*;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ProgressManager {
    private static final String SAVE_FILE = "aviation_save.ser";
    
    public static void saveAll() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SAVE_FILE))) {
            // Save both collections as an array
            oos.writeObject(new Object[]{ Database.collection, Database.database, Database.routes, Database.allflights, Database.aircrafts, StoredWinds.obj, Database.money, Database.fuel, Database.prices, Demand.demands,
             Database.recent, Database.price});
        }
    }
    
    public static void loadAll() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SAVE_FILE))) {
            Object[] loaded = (Object[]) ois.readObject();
            Database.collection.clear();
            Database.collection.addAll((ArrayList<Aircraft>) loaded[0]);
            
            Database.database.clear();
            Database.database.addAll((ArrayList<Airport>) loaded[1]);
            
            Database.routes.clear();
            Database.routes.addAll((ArrayList<Route>) loaded[2]);
            
            Database.allflights.clear();
            Database.allflights.addAll((ArrayList<Flight>) loaded[3]);
            Database.aircrafts.clear();
            Database.aircrafts.addAll((ArrayList<Aircraft>) loaded[4]);
            StoredWinds.obj.clear();
            StoredWinds.obj.addAll((ArrayList<StoredWinds>) loaded[5]);
            Database.money=(long)loaded[6];
            Database.fuel=(double)loaded[7];
            Database.prices.clear();
            Database.prices.addAll((ArrayList<Double>) loaded[8]);
            Demand.demands.clear();
            Demand.demands.addAll((ArrayList<Demand>) loaded[9]);
            Database.recent = (LocalDateTime)loaded[10];
            Database.price = (double)loaded[11];
            

        }
    }
}