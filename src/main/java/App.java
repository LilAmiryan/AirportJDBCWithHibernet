import dao.impl.*;
import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class App {
    public static void main(String[] args) throws ParseException, IOException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Trip.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Passenger.class);
        configuration.addAnnotatedClass(PassInTrip.class);
        SessionFactory sessionFactory;
        sessionFactory = configuration.buildSessionFactory();


        File passenger = new File("passengers.txt");
        File passInTripFile = new File("pass_in_trip.txt");
        File tripFile = new File("trip.txt");
        File companyFile = new File("companies.txt");


        //-------Company---------
        CompanyDAOimpl companyDAOimpl = new CompanyDAOimpl(sessionFactory);
        //companyDAOimpl.getCompaniesFieldsFromFile(companyFile);
        //System.out.println(companyDAOimpl.getAll());
        //System.out.println(companyDAOimpl.getById(12));


        //--------------Address--------------
        AddressDAOimpl addressDAOimpl = new AddressDAOimpl(sessionFactory);
        //addressDAOimpl.getAddressesFromFile(passenger);
        //System.out.println("Test for me in App "+ addressDAOimpl.getAddressById(2L));


        //-----------Passenger----------
        PassengerDAOimpl passengerDAOimpl = new PassengerDAOimpl(sessionFactory);
        // passengerDAOimpl.getPassengersFromFile(passenger);

        //System.out.println(passengerDAOimpl.get(2,50,"passenger_name").toString());
        //passengerDAOimpl.delete(303L);
        // passengerDAOimpl.update( 2L, new Passenger("Lilit", "123456789",new Address()));
        // System.out.println( passengerDAOimpl.getAll().toString());
        //  System.out.println(passengerDAOimpl.getById(400).toString());
       // System.out.println( passengerDAOimpl.get(4,10));
        // System.out.println(passengerDAOimpl.getPassengersOfTrip(6));
       // passengerDAOimpl.cancelTrip(1,1181);

        //---------Trip---------
        TripDAOimpl tripDAOimpl = new TripDAOimpl(sessionFactory);
        //tripDAOimpl.getTripInformationFromFile(tripFile);

        //System.out.println("AAAAAAAAA" + tripDAOimpl.getById(2));
        //System.out.println(tripDAOimpl.getTripsFrom("Paris"));
        // System.out.println(tripDAOimpl.getAll());
        //System.out.println(tripDAOimpl.getById(1100));


        //------------Pass_In_Trip---------
        PassInTripDAOimpl passInTripDAOimpl = new PassInTripDAOimpl(sessionFactory);
        // passInTripDAOimpl.getPassInTripInformationFromFile(passInTripFile);

    }
}
