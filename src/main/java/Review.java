import java.util.List;
import org.sql2o.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
  private int id;
  private String name;
  private String rest;
  private String review;

  public Review(String name, String rest, String review) {
    this.name = name;
    if(name.equals("")){
      this.name = "Anonymous";
    }
    this.rest = rest;
    this.review = review;

  }

  public String getName() {
    return name;
  }

  public String getRest() {
    return rest;
  }

  public String getReview() {
    return review;
  }

  public static List<Review> all() {
    String sql = "SELECT * FROM reviews ORDER BY rest ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(name, rest, review) VALUES (:name, :rest, :review)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("rest", this.rest)
      .addParameter("review", this.review)
      .executeUpdate()
      .getKey();
    }
  }


    @Override
    public boolean equals(Object otherReview) {
      if (!(otherReview instanceof Review)) {
        return false;
      } else {
        Review newReview = (Review) otherReview;
        return this.getReview().equals(newReview.getReview());
      }
    }

  public static Review find(String rest) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews where rest=:rest";
      Review patient = con.createQuery(sql)
      .addParameter("rest", rest)
      .executeAndFetchFirst(Review.class);
      return patient;
    }
  }
}
