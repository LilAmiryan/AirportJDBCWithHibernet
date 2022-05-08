package dao.impl;

import dao.CompanyDAO;
import model.Company;
import model.Passenger;
import model.Trip;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.DatabaseConnectionService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyDAOimpl implements CompanyDAO {

    private SessionFactory sessionFactory;

    public CompanyDAOimpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Company getById(Long id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Company company = session.get(Company.class, id);
        session.getTransaction().commit();
        session.close();
        return company;
    }



    @Override
    public List<Company> getAll(){

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Company> companies= new ArrayList<>();
        companies=  session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return companies;
    }

    public void registerTrip(Trip trip, Company company){
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        trip.setCompany(company);
        session.merge(trip);
        transaction.commit();
    }
    @Override
    public long save(Company company) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long companygId;
        companygId = (long) session.save(company);
        transaction.commit();
        session.close();

        return companygId;
    }

    public void getCompaniesFieldsFromFile(File filename) throws IOException {

        String line;
        String words[] = null;
        Company company = new Company();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("'")) {
                line = line.replace("'", "՛");
            }
            words = line.split(",");
            System.out.println();
            for (int i = 0; i < words.length; i++) {
                System.out.println(words[i] + " ");
                company.setCompanyName(words[0]);
                company.setFoundingDate(words[1]);

            }
            save(company);
        }
        return;

    }

}
