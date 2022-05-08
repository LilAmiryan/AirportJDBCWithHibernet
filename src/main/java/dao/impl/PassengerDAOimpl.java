package dao.impl;

import dao.PassengerDAO;
import model.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.hibernate.loader.custom.sql.SQLCustomQuery;
import service.DatabaseConnectionService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PassengerDAOimpl implements PassengerDAO {

    private SessionFactory sessionFactory;

    public PassengerDAOimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Passenger getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Passenger passenger = session.get(Passenger.class, id);
        transaction.commit();
        session.close();
        return passenger;
    }

    /**
     * @return
     */
    @Override
    public List<Passenger> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Passenger> passengers = new ArrayList<>();
        passengers = session.createQuery("select  a from Passenger a", Passenger.class).getResultList();
        transaction.commit();
        session.close();
        return passengers;
    }


    /**
     * @param filename
     * @throws IOException
     */
    public void getPassengersFromFile(File filename) throws IOException {

        Passenger passenger = new Passenger();
        String line;
        String words[] = null;
        AddressDAOimpl addressDAOimpl = new AddressDAOimpl(sessionFactory);
        ;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("'")) {
                line = line.replace("'", "՛");
            }
            words = line.split(",");

            passenger.setPassengerName(words[0]);
            passenger.setPassengerPhone(words[1]);
            Address address = addressDAOimpl.getAddressFromDB(words[3], words[2]);
            passenger.setAddress(address);
            //addressDAOimpl.registerAddress(new Address(words[2],words[3]),passenger);
            save(passenger);
            System.out.println();
        }

        bufferedReader.close();

        return;

    }

    /**
     * @param passenger
     */
    @Override
    public void save(Passenger passenger) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(passenger);
        transaction.commit();
        session.close();
    }

    /**
     * @param id
     * @param passenger
     */
    @Override
    public void update(Long id, Passenger passenger) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Passenger passenger1 = session.get(Passenger.class, id);
        passenger1.setPassengerName(passenger.getPassengerName());
        passenger1.setPassengerPhone(passenger.getPassengerPhone());

        session.merge(passenger1);
        transaction.commit();
        session.close();

    }

    /**
     * @param passengerId
     */
    @Override
    public void delete(Long passengerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Passenger passenger = new PassengerDAOimpl(sessionFactory).getById(passengerId);
        session.delete(passenger);
        transaction.commit();
        session.close();
    }

    /**
     * @param offset
     * @param perPage
     * @return
     */
    @Override
    public List<Passenger> get(int offset, int perPage) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Passenger> passengers = new ArrayList<>();

        org.hibernate.query.Query query = session
                .createQuery("select p from Passenger p order by p.passengerName desc");
        query.setFirstResult(offset * perPage);
        query.setMaxResults(perPage);
        passengers = query.getResultList();

       /* passengers = session.createQuery("SELECT p FROM Passenger p ORDER BY p.passengerName")
                .setFirstResult(offset * perPage).setMaxResults(perPage).getResultList();*/
        transaction.commit();
        session.close();
        return passengers;
    }


    @Override
    public List<Passenger> getPassengersOfTrip(long tripNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query=session.createQuery("select a from Trip b join  b.passInTrips a where b.id= :id");
        query.setParameter("id",tripNumber);

        List<Passenger> passengers = query.getResultList();
        return passengers;
    }

    public Passenger getPassengerById(Long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Passenger passenger = session.get(Passenger.class, id);
        session.getTransaction().commit();
        session.close();
        return passenger;
    }

    public Address getAddressIdForConnectionWithPassengerAddres_ID(String[] word) {
        Session sess = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        String SQL_QUERY = "from Address";

        Query query = session.createQuery(SQL_QUERY);
        Set<Address> set = (Set<Address>) query.getResultList();
        List list = query.getResultList();


        Address address = new Address();
        address.setId((Long) list.get(0));
        address.setCity((String) list.get(1));
        address.setCountry((String) list.get(2));

        transaction.commit();
        session.close();
        return address;
    }


    @Override
    public void registerTrip(PassInTrip passInTrip) {

        PassInTripDAOimpl passInTripDAOimpl = new PassInTripDAOimpl(sessionFactory);
        passInTripDAOimpl.save(passInTrip);
    }

    @Override
    public void cancelTrip(long passengerId, long tripNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM pass_in_trip where pass_id=:pass_id and  trip_id=:trip_id");
        query.setParameter("pass_id", passengerId);
        query.setParameter("trip_id", tripNumber);
        transaction.commit();
        session.close();
    }
}
