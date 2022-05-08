package model;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pass_in_trip")
@IdClass(PassInTripPrimaryKey.class)
public class PassInTrip {

    @Id
    private LocalDate date;

    @Column(name = "place")
    private String place;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pass_id")
    private Passenger passenger_;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip_;

    public PassInTrip() {
    }

    public PassInTrip(Trip trip_id, Passenger passenger, LocalDate date, String place) {
        this.trip_ = trip_;
        this.passenger_ = passenger;
        this.date = date;
        this.place = place;
    }

    public Trip getTrip_id() {
        return trip_;
    }

    public void setTrip_id(Trip trip_) {
        this.trip_ = trip_;
    }

    public Passenger getPass_id() {
        return passenger_;
    }

    public void setPass_id(Passenger passenger) {
        this.passenger_ = passenger;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "PassInTrip{" +
                "trip_id=" + trip_ +
                ", pass_id=" + passenger_.toString() +
                ", date=" + date +
                ", place=" + place +
                '}';
    }
}
