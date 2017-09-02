import java.sql.Timestamp;
/**
 * Sighting
 */
public class Sighting {
private int animalId;
private String location;
private String rangerName;
private int id;
private Timestamp sighted;
    public Sighting (int animalId, String location, String rangerName ) {
        this.animalId=animalId;
        this.location=location;
        this.rangerName=rangerName;
        
    }
    public int getAnimalId(){
        return animalId;
    }
    public String getLocation(){
        return location;
    }
    public String getRangerName(){
        return rangerName;
    }
    public Timestamp getSighted(){
        return sighted;
    }
}