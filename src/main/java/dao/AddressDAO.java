package dao;

import model.Address;
import org.hibernate.SessionFactory;

import java.util.List;

public interface AddressDAO {
    Long save(Address address);
    public Address getAddressFromDB(String city, String country);
    public List<Address> getAll();
}
