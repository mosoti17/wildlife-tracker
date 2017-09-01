import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
public class NormalAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void normalanimal_instantiatesCorrectly_true() {
      NormalAnimal testNormalAnimal = new NormalAnimal(" Impala");
      assertEquals(true, testNormalAnimal instanceof NormalAnimal);
    }
    @Test
    public void getName_animalinstantiatesCorrectlywithname_true() {
      NormalAnimal testNormalAnimal = new NormalAnimal(" Impala");
      assertEquals(" Impala", testNormalAnimal.getName());
    }
    @Test
    public void save_animalIsSavedToDatabase_true() {
      NormalAnimal testNormalAnimal = new NormalAnimal("Impala");
      testNormalAnimal.save();
      assertTrue(NormalAnimal.all().get(0).equals(testNormalAnimal));
    }
    @Test
    public void find_animalIsSavedToDatabase_true() {
      NormalAnimal testNormalAnimal1 = new NormalAnimal("Impala");
      testNormalAnimal1.save();
      NormalAnimal testNormalAnimal2 = new NormalAnimal("Impala");
      testNormalAnimal2.save();
      assertEquals(NormalAnimal.find(testNormalAnimal2.getId()), testNormalAnimal2);
    }
    @Test
    public void delete_deletesWaterMonster_true() {
        NormalAnimal testNormalAnimal1 = new NormalAnimal("Impala");
        testNormalAnimal1.save();
      testNormalAnimal1.delete();
      assertEquals(null, NormalAnimal.find(testNormalAnimal1.getId()));
    }
}