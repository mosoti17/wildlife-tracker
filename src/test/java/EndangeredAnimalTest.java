import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void normalanimal_instantiatesCorrectly_true() {
      EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(" Rhino","young", "okay");
      assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void getName_animalInstantatesWithName_true() {
      EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(" Rhino","young", "okay");
      assertEquals(" Rhino", testEndangeredAnimal.getName());
    }
    @Test
    public void getage_animalInstantatesWithage_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(" Rhino","young", "okay");
        assertEquals("young", testEndangeredAnimal.getAge());
      }
      @Test
      public void gethealth_animalInstantatesWithhealth_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal(" Rhino","young", "okay");
        assertEquals("okay", testEndangeredAnimal.getHealth());
      }
     
     
      @Test
      public void save_reursTrueIfNameIsTheSame_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Rhino","young", "okay");
        testEndangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
      }
      @Test
      public void find_returnsMonsterWithSameId_secondMonster() {
        EndangeredAnimal testEndangeredAnimal1 = new EndangeredAnimal("Rhino","young", "okay");
        testEndangeredAnimal1.save();
        EndangeredAnimal testEndangeredAnimal2 = new EndangeredAnimal("Lion","", "okay");
        testEndangeredAnimal2.save();
        assertEquals(EndangeredAnimal.find(testEndangeredAnimal2.getId()), testEndangeredAnimal2);
      }
      @Test
      public void delete_deletesWaterMonster_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Rhino","young", "okay");
        testEndangeredAnimal.save();
        testEndangeredAnimal.delete();
        assertEquals(null, EndangeredAnimal.find(testEndangeredAnimal.getId()));
      }
   
}