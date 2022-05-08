package dao;

import model.PassInTrip;
import model.Passenger;
import model.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerDAO {
    Passenger getById(Long id);

    List<Passenger> getAll();

    List<Passenger> get(int offset, int perPage);

    void save(Passenger passenger);

    void update(Long id,Passenger passenger);

    void delete(Long passengerId);

    List<Passenger> getPassengersOfTrip(long tripNumber);

    void registerTrip(PassInTrip passInTrip);

    void cancelTrip(long passengerId, long tripNumber);

}
