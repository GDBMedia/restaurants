// import java.util.List;
// import org.sql2o.*;
// import java.text.ParseException;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.util.Date;
//
// public class Patient {
//   private int id;
//   private String first_name;
//   private String last_name;
//   private int doctorId;
//   private String birthdate;
//
//   public Patient(String first_name, String last_name, String birthdate, int doctorId) {
//     this.first_name = first_name;
//     this.last_name = last_name;
//     this.birthdate = birthdate;
//     this.doctorId = doctorId;
//
//   }
//
//   public String getFirstName() {
//     return first_name;
//   }
//
//   public String getLastName() {
//     return last_name;
//   }
//
//   public int getId() {
//     return id;
//   }
//
//   public int getDoctorId() {
//     return doctorId;
//   }
//   public String getBirthdate() {
//     return birthdate;
//   }
//
//   public String formatBirthday(){
//     SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
//     SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd yyyy");
//     try {
//       String reformattedStr = myFormat.format(fromUser.parse(birthdate));
//       return reformattedStr;
//     } catch (ParseException e) {
//       return "broken";
//     }
//
//   }
//
//   public static List<Patient> all() {
//     String sql = "SELECT * FROM patients ORDER BY last_name ASC";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Patient.class);
//     }
//   }
//
//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO patients(first_name, last_name, birthdate, doctorid) VALUES (:first_name, :last_name, :birthdate, :doctorid)";
//       this.id = (int) con.createQuery(sql, true)
//       .addParameter("first_name", this.first_name)
//       .addParameter("last_name", this.last_name)
//       .addParameter("birthdate", this.birthdate)
//       .addParameter("doctorid", this.doctorId)
//       .executeUpdate()
//       .getKey();
//     }
//   }
//
//   public static Patient find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM patients where id=:id";
//       Patient patient = con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetchFirst(Patient.class);
//       return patient;
//     }
//   }
// }
