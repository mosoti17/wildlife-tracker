import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
/**
 * name
 */
public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void sighting_instantiatesCorrectly_true() {
      Sighting testSighting = new Sighting( 1 , " north","john");
      assertEquals(true, testSighting instanceof Sighting);
    }
}