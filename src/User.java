import java.util.Date;

public class User {
    String fristName;
    String lastName;
    Date dob;
    String country;
    String address;
    String city;

    public User(String fristName, String lastName, Date dob, String country, String address, String city) {
        this.fristName = fristName;
        this.lastName = lastName;
        this.dob = dob;
        this.country = country;
        this.address = address;
        this.city = city;
    }

    public User() {

    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFristName() {
        return fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
