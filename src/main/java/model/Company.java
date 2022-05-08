package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    @Column(name = "founding_date", nullable = false, length = 50)
    private String foundingDate;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
    private List<Trip> trips = new ArrayList<>();

    public  void  addTrip(Trip trip){
        trips.add(trip);
        trip.setCompany(this);
    }
    public Company() {
    }

    public Company(String companyName, String foundingDate, long id) {
        this.companyName = companyName;
        this.foundingDate = foundingDate;
        this.id = id;
    }

    public Company(String companyName, String foundingDate) {
        this.companyName = companyName;
        this.foundingDate = foundingDate;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
        this.foundingDate = foundingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", foundingDate=" + foundingDate +
                '}';
    }
}
