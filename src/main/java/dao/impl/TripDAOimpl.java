package dao.impl;

import dao.TripDAO;
import model.Company;
import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DatabaseConnectionService;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDAOimpl implements TripDAO {

    private SessionFactory sessionFactory;

    public TripDAOimpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void getTripInformationFromFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        String[] words = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("'")) {
                line = line.replace("'", "՛");
            }
            words = line.split(",");
            File companyFile = new File("companies.txt");
            Company company=null;

          //  for (int i = 0; i < words.length; i++) {
             //   System.out.println(words[i] + " ");
            Trip trip = new Trip();

                trip.setTrip_no(Long.parseLong(words[0]));
                trip.setTown_from(words[2]);
                trip.setTown_to(words[3]);
                trip.setTime_out(LocalDateTime.parse(words[4],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toLocalTime());
                trip.setTime_in(LocalDateTime.parse(words[5],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")).toLocalTime());

                company=new CompanyDAOimpl(sessionFactory).getById(Long.parseLong(words[1]));
                CompanyDAOimpl companyDAOimpl=new CompanyDAOimpl(sessionFactory);
                companyDAOimpl.registerTrip(trip,company);
           // }
            //save(trip);
        }
    }

    public Trip getTripById(Long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Trip trip = session.get(Trip.class, id);
        session.getTransaction().commit();
        session.close();
        return trip;
    }

    @Override
    public Trip save(Trip trip) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(trip);
        transaction.commit();
        session.close();

        return trip;
    }



}

