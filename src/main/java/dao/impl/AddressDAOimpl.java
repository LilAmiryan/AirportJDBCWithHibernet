package dao.impl;

import dao.AddressDAO;
import model.Address;
import model.Company;
import model.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOimpl implements AddressDAO {
    private SessionFactory sessionFactory;

    public AddressDAOimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(Address address) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Long address_id;
        address_id = (Long) session.save(address);
        transaction.commit();
        session.close();

        return address_id;
    }


    public void getAddressesFromFile(File filename) throws IOException {

        Address address = new Address();
        String line;
        String words[] = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        while ((line = bufferedReader.readLine()) != null) {
            words = line.split(",");

            address.setCountry(words[2]);
            address.setCity(words[3]);

            save(address);
            System.out.println();

        }
        return;
    }

    public void registerAddress(Address address, Passenger passenger) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        passenger.setAddress(address);
        session.merge(passenger);
        transaction.commit();
        session.close();
    }

    public Address getAddressById(Long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Address address = session.get(Address.class, id);
        session.getTransaction().commit();
        session.close();
        return address;
    }

    @Override
    public Address getAddressFromDB(String cty, String cntry) {

        Address address = null;
        Session session = sessionFactory.openSession();

        AddressDAOimpl addressDAOimpl=new AddressDAOimpl(sessionFactory);

        try {
            System.out.println(address = (Address) session.createQuery(
                    "SELECT a FROM Address  a where a.city=:cty and " +
                            "a.country=:cntry").setParameter("cty", cty).setParameter("cntry", cntry).uniqueResult());
        } catch (Exception e) {
            address = null;
        }
        session.close();
        return address;
    }

    @Override
    public List<Address> getAll(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Address> addresses= new ArrayList<>();
        addresses=  session.createQuery("SELECT a FROM Address a", Address.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return addresses;
    }

}
