import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.junit.*;
import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Italian");
    assertThat(pageSource()).contains("Pizza");
    assertThat(pageSource()).contains("Mexican");
    assertThat(pageSource()).contains("Burger");
    assertThat(pageSource()).contains("Food Carts");
    assertThat(pageSource()).contains("All Restaurants");
  }

  @Test
  public void ItalianTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Italian"));
    assertThat(pageSource()).contains("Gilda's Italian Restaurant");
    assertThat(pageSource()).contains("Ristorante Roma");
  }

  @Test
  public void PizzaTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Pizza"));
    assertThat(pageSource()).contains("Sizzle Pie");
  }

  @Test
  public void MexiTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Mexican"));
    assertThat(pageSource()).contains("PePe's");
  }

  @Test
  public void BurgerTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Burger"));
    assertThat(pageSource()).contains("Five Guys");
  }

  @Test
  public void FoodCartTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Food Carts"));
    assertThat(pageSource()).contains("Don Pedro");
  }

  @Test
  public void allTest() {
    goTo("http://localhost:4567/");
    click("a", withText("All Restaurants"));
    assertThat(pageSource()).contains("Don Pedro");
    assertThat(pageSource()).contains("Gilda's Italian Restaurant");
    assertThat(pageSource()).contains("Ristorante Roma");
    assertThat(pageSource()).contains("Sizzle Pie");
    assertThat(pageSource()).contains("PePe's");
    assertThat(pageSource()).contains("Five Guys");
  }

  @Test
  public void DynamicRestaurantTest() {
    goTo("http://localhost:4567/cuisines/Italian/restaurants");
    click("a", withText("Gilda's Italian Restaurant"));
    assertThat(pageSource()).contains("Lasagne");
  }

  @Test
  public void addNewRestaurantTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Food Carts"));
    fill("#nameOfRestaurant").with("Small Pharohs");
    fill("#description").with("new york and egyptian food");
    fill("#dish").with("Gyro");
    submit(".btn");
    click("a", withText("Go back to Food Carts"));
    assertThat(pageSource()).contains("Small Pharohs");
    assertThat(pageSource()).contains("Gyro");
  }

  @Test
  public void addNewReviewTest() {
    goTo("http://localhost:4567/cuisines/Italian/restaurants");
    click("a", withText("Gilda's Italian Restaurant"));
    fill("#name").with("Garrett");
    fill("#review").with("Awesome Pasta");
    submit(".btn");
    click("a", withText("Go back to Gilda's Italian Restaurant"));
    assertThat(pageSource()).contains("Awesome Pasta");
    assertThat(pageSource()).contains("Garrett");
  }


}
