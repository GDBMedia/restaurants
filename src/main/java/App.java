import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("cuisines", Restaurant.allCuisines());
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("cuisines/:cuisine/restaurants", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String cuisine = request.params(":cuisine");
      model.put("cuisine", cuisine);
      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("restaurants", Restaurant.listRestaurantsByCuisine(cuisine));
      model.put("template", "templates/cuisine-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurant-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String nameOfRestaurant = request.queryParams("nameOfRestaurant");
      String cuisine = request.queryParams("cuisine");
      String description = request.queryParams("description");
      String dish = request.queryParams("dish");
      Restaurant newRestaurant = new Restaurant(nameOfRestaurant, cuisine, description, dish);
      newRestaurant.save();

      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("restaurant", nameOfRestaurant);
      model.put("cuisine", cuisine);
      model.put("template", "templates/restaurant-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("cuisines/:cuisine/restaurants/:restaurant", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rest = request.params(":restaurant");
      String restaurant = rest.replaceAll("-", " ");
      Restaurant newRestaurant = Restaurant.find(restaurant);

      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("rest", restaurant);
      model.put("reviews", newRestaurant.getReviews());
      model.put("restaurant", newRestaurant);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/review-success", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String name = request.queryParams("name");
      String rest = request.queryParams("rest");
      String review = request.queryParams("review");
      String restUrl = request.queryParams("restUrl");

      Review newReview = new Review(name, rest, review);
      newReview.save();

      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("restUrl", restUrl);
      model.put("name", name);
      model.put("rest", rest);
      model.put("template", "templates/review-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurants", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("restaurants", Restaurant.all());
      model.put("cuisines", Restaurant.allCuisines());
      model.put("template", "templates/restaurants.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("restaurants/:restaurant", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String rest = request.params(":restaurant");
      String restaurant = rest.replaceAll("-", " ");
      Restaurant newRestaurant = Restaurant.find(restaurant);
      model.put("rest", restaurant);
      model.put("navrestaurants", Restaurant.all());
      model.put("navcuisines", Restaurant.allCuisines());
      model.put("reviews", newRestaurant.getReviews());
      model.put("restaurant", newRestaurant);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
