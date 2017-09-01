import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
/**
 * EndangeredAnimal
 */
public class EndangeredAnimal extends Animal implements DatabaseManagement{
    public static final String DATABASE_TYPE = "endangered";
    public EndangeredAnimal (String name, String age, String health) {
        this.name =name;
        this.age = age;
        this.health =health;
        type = DATABASE_TYPE;
        
    }
    
    public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO animals (name,age,health, type) VALUES (:name,:age, :health, :type)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("age", this.age)
            .addParameter("health", this.health)
            .addParameter("type", this.type)
            .executeUpdate()
            .getKey();
        }
      }
      public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
          return con.createQuery(sql)
          .executeAndFetch(EndangeredAnimal.class);
        }     
}
public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      EndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return animal;
    }
  }




}