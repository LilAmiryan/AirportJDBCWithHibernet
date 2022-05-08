package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PassInTripPrimaryKey implements Serializable {

    private Trip trip_;
    private Passenger passenger_;
    private LocalDate date;

    public PassInTripPrimaryKey() {
    }
    public PassInTripPrimaryKey(Trip trip_, Passenger passenger_, LocalDate date) {
        this.trip_ = trip_;
        this.passenger_ = passenger_;
        this.date = date;
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

    public void setPass_id(Passenger passenger_) {
        this.passenger_ = passenger_;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTripPrimaryKey that = (PassInTripPrimaryKey) o;
        return trip_ == that.trip_ && passenger_ == that.passenger_ && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trip_, passenger_, date);
    }
}
