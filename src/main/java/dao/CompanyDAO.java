package dao;

import model.Company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CompanyDAO {
    Company getById(Long id);

    List<Company> getAll();

    long save(Company company);






}
