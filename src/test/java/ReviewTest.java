import org.sql2o.*;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class ReviewTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/rest_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM reviews *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Restaurant_instantiatesCorrectly_true() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    assertEquals(true, myReview instanceof Review);
  }

  @Test
  public void getName_Test() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    assertEquals(myReview.getName(), "Jacob");
  }

  @Test
  public void getRest_Test() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    assertEquals(myReview.getRest(), "Don Pedro");
  }

  @Test
  public void getReview_Test() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    assertEquals(myReview.getReview(), "count Sauces");
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Review.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Review myReview1 = new Review("Jacob", "Don Pedro", "count Sauces");
    Review myReview2 = new Review("Jacob", "Don Pedro", "count Sauces");
    assertTrue(myReview1.equals(myReview2));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    myReview.save();
    assertTrue(Review.all().get(0).equals(myReview));
  }

  @Test
  public void find_findsRestuarantInDatabase_true() {
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    myReview.save();
    Review savedReview = Review.find(myReview.getRest());
    assertTrue(myReview.equals(savedReview));
  }
}
