package dao.impl;

import dao.PassInTripDAO;
import model.PassInTrip;
import model.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PassInTripDAOimpl implements PassInTripDAO {
    private SessionFactory sessionFactory;

    public PassInTripDAOimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void getPassInTripInformationFromFile(File file) throws IOException {
        String line = null;
        String[] words = null;
        PassInTrip passInTrip = new PassInTrip();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("'")) {
                line = line.replace("'", "՛");
            }
            words = line.split(",");

            // System.out.println(words[i]+" ");
            passInTrip.setTrip_id(new TripDAOimpl(sessionFactory).getTripById(Long.parseLong(words[0])));
            passInTrip.setPass_id(new PassengerDAOimpl(sessionFactory).getPassengerById(Long.parseLong(words[1])));
            passInTrip.setDate(LocalDateTime.parse(words[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toLocalDate());
            passInTrip.setPlace(words[3]);

            save(passInTrip);
        }


    }

    @Override
    public void save(PassInTrip passInTrip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(passInTrip);
        transaction.commit();
        session.close();
    }

}
