import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Animal
 */
public abstract class Animal {
    public String name;
    public int id;
    public String age;
    public String health;
    public String type;
    // public Timestamp lastSighting;
    // public static final String[] age = {"newborn", "young ",  "adult"};
    // public static final String[] health = {"healthy", "ill", "okay"};

    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public String getHealth(){
        return health;
    }
    public String getAge(){
        return age;
    }
    @Override
    public boolean equals(Object otherAnimal){
      if (!(otherAnimal instanceof Animal)) {
        return false;
      } else {
        Animal newAnimal = (Animal) otherAnimal;
        return this.getName().equals(newAnimal.getName())&&
        this.getAge().equals(newAnimal.getAge())&&
        this.getHealth().equals(newAnimal.getHealth())
        ;
      }
    }
    
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM animals WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.id)
          .executeUpdate();
        }
      }

}