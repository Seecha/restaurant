package cz.uhk.restaurace.dao;

import cz.uhk.restaurace.model.EmployeeLoc;

import java.util.List;

/**
 * Created by dann on 20.12.2014.
 */
public interface EmployeeLocDao {

    public void addEmployeeLoc(EmployeeLoc employee);
    public void updateEmployeeLoc(EmployeeLoc employee);
    public List<EmployeeLoc> getAllEmployeesLocs(String language);
    public EmployeeLoc getEmployeeLocById(String language, String username);
    public void removeEmployeeLoc(String language, String username);
}
