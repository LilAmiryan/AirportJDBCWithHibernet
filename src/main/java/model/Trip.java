package model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    private long trip_no;

    @Column(name = "town_from")
    private String town_from;

    @Column(name = "town_to")
    private String town_to;

    @Column(name = "time_out")
    private LocalTime time_out;

    @Column(name = "time_in")
    private LocalTime time_in;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "company_id")
    private Company company;


    @OneToMany(mappedBy = "trip_")
    List<PassInTrip> passInTrips=new ArrayList<>();

    public Trip() {
    }

    public Trip(long trip_no, Company company, String town_from, String town_to, LocalTime time_out, LocalTime time_in) {
        this.trip_no = trip_no;
        this.company = company;
        this.town_from = town_from;
        this.town_to = town_to;
        this.time_out = time_out;
        this.time_in = time_in;
    }

    public Trip(Company company_id, String town_from, String town_to, LocalTime time_out, LocalTime time_in) {
        this.company = company;
        this.town_from = town_from;
        this.town_to = town_to;
        this.time_out = time_out;
        this.time_in = time_in;
    }

    public void setTrip_no(long trip_no) {
        this.trip_no = trip_no;
    }

    public Company getCompany_id() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTown_from() {
        return town_from;
    }

    public void setTown_from(String town_from) {
        this.town_from = town_from;
    }

    public String getTown_to() {
        return town_to;
    }

    public void setTown_to(String town_to) {
        this.town_to = town_to;
    }

    public LocalTime getTime_out() {
        return time_out;
    }

    public void setTime_out(LocalTime time_out) {
        this.time_out = time_out;
    }

    public LocalTime getTime_in() {
        return time_in;
    }

    public void setTime_in(LocalTime time_in) {
        this.time_in = time_in;
    }

    public long getTrip_no() {
        return trip_no;
    }

    public void setTrip_no(int trip_no) {
        this.trip_no = trip_no;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "trip_no=" + trip_no +
                ", company_id=" + company.toString() +
                ", town_from='" + town_from + '\'' +
                ", town_to='" + town_to + '\'' +
                ", time_out='" + time_out + '\'' +
                ", time_in='" + time_in + '\'' +
                '}';
    }
}
