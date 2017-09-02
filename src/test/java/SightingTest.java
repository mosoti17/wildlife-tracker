import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

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

    @Test
    public void equals_sightingsAreTheSame_true() {
        Sighting testSighting1 = new Sighting( 1 , " north","john");
        Sighting testSighting2 = new Sighting( 1 , " north","john");
        assertEquals(true, testSighting1.equals(testSighting2));
      }
      @Test
      public void save_sightingsSaveToDatabase_true() {
        Sighting testSighting = new Sighting( 1 , "north","john");
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
      }
      @Test
      public void all_returnsAllSightings_true() {
          Sighting testSighting1 = new Sighting( 1 , "north","john");
          testSighting1.save();
          Sighting testSighting2 = new Sighting( 2 , "south","Steve");
          testSighting2.save();
          assertTrue(Sighting.all().get(0).equals(testSighting1));
          assertTrue(Sighting.all().get(1).equals(testSighting2));
        }
        @Test
        public void find_returnsSightingWithSameId_true() {
            Sighting testSighting1 = new Sighting( 1 , "north","john");
            testSighting1.save();
            Sighting testSighting2 = new Sighting( 2 , "south","Steve");
            testSighting2.save();
            assertEquals(Sighting.find(testSighting2.getId()),testSighting2);
          }


}
