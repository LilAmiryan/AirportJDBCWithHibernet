package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "passenger_phone")
    private String passengerPhone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "passenger_")
    private List<PassInTrip> passInTrips = new ArrayList<>();

    public Passenger() {
    }

    public Passenger(Long id, String passengerName, String passengerPhone, Address address) {
        this.id = id;
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.address = address;
    }

    public Passenger(String passengerName, String passengerPhone, Address address) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.address = address;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", passengerName='" + passengerName + '\'' +
                ", passengerPhone='" + passengerPhone + '\'' +
                ", address_id=" + address.toString() +
                '}';
    }
}
