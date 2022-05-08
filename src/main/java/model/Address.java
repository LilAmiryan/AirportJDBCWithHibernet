package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addrress")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country", nullable=false, length = 50)
    private String country;

    @Column(name = "city", nullable=false,length = 50)
    private String city;

    @OneToMany(mappedBy = "address")
    private Set<Passenger> passengers = new HashSet<>();

    public void addAddress(Passenger passenger){
        passenger.setAddress(this);
        passengers.add(passenger);
    }

    public Address() {
    }

    public Address(String city,String country) {
        this.country = country;
        this.city = city;
    }

    public Address(Long id,String country, String city) {
        this.country = country;
        this.city = city;
        this.id=id;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
