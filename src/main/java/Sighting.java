import java.sql.Timestamp;
import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
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
    public int getId(){
        return id;
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
    public String getDateNew(){
      return DateFormat.getDateTimeInstance().format(sighted);
    }
    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sighting)) {
          return false;
        } else {
          Sighting newSighting = (Sighting) otherSighting;
          return this.getLocation().equals(newSighting.getLocation())&&
                this.getAnimalId()==newSighting.getAnimalId()&&
                 this.getRangerName().equals(newSighting.getRangerName());
        }
      }
      public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings ;";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql)
          .executeAndFetch(Sighting.class);
        }
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO sightings (location, rangername, animalid,sighted) VALUES ( :location,:rangername, :animalid, now())";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("animalid", this.animalId)
            .addParameter("location", this.location)
            .addParameter("rangername", this.rangerName)
            .executeUpdate()
            .getKey();
        }
      }
      public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM Sightings where id=:id";
          Sighting Sighting = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Sighting.class);
          return Sighting;
        }
      }


}
