import org.sql2o.*;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {
  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/rest_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM rests *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void Restaurant_instantiatesCorrectly_true() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals(true, myRestaurant instanceof Restaurant);
  }

  @Test
  public void getName_restInstantiatesWithName_String() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals("Don Pedro", myRestaurant.getName());
  }

  @Test
  public void getNameUrl_restInstantiatesWithName_String() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals("Don-Pedro", myRestaurant.getUrlName());
  }

  @Test
  public void getCuisine_restInstantiatesWithCuisine_String() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals("Mexican", myRestaurant.getCuisine());
  }

  @Test
  public void getDescription_restInstantiatesWithDescription_String() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals("burrito place", myRestaurant.getDescription());
  }

  @Test
  public void getDish_restInstantiatesWithDish_String() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertEquals("Burrito", myRestaurant.getDish());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Restaurant firstRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    Restaurant secondRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
  }

  @Test
  public void find_findsRestuarantInDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getName());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }

  @Test
  public void find_allCuisines() {
    assertEquals(Restaurant.allCuisines().size(), 5);
  }

  @Test
  public void listRestaurantsByCuisine_test() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    myRestaurant.save();
    String cuisine = "Mexican";
    assertTrue(Restaurant.listRestaurantsByCuisine(cuisine).get(0).equals(myRestaurant));
  }

  @Test
  public void getReviews_test() {
    Restaurant myRestaurant = new Restaurant("Don Pedro", "Mexican", "burrito place", "Burrito");
    myRestaurant.save();
    Review myReview = new Review("Jacob", "Don Pedro", "count Sauces");
    myReview.save();
    assertEquals(myRestaurant.getReviews().get(0).getRest(), myRestaurant.getName());
  }

}
