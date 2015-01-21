package cz.uhk.restaurace.service.impl;

import cz.uhk.restaurace.dao.EmployeeLocDao;
import cz.uhk.restaurace.model.EmployeeLoc;
import cz.uhk.restaurace.service.EmployeeLocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dann on 20.12.2014.
 */

@Service
public class EmployeeLocServiceImpl implements EmployeeLocService {

    @Autowired
    private EmployeeLocDao employeeLocDao;

    @Override
    @Transactional
    public void addEmployeeLoc(EmployeeLoc employee) {
        employeeLocDao.addEmployeeLoc(employee);
    }

    @Override
    @Transactional
    public void updateEmployeeLoc(EmployeeLoc employee) {
        employeeLocDao.updateEmployeeLoc(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeLoc> getAllEmployeesLocs(String language) {
        return employeeLocDao.getAllEmployeesLocs(language);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeLoc getEmployeeLocById(String language, String username) {
        return employeeLocDao.getEmployeeLocById(language, username);
    }

    @Override
    @Transactional
    public void removeEmployeeLoc(String language, String username) {
        employeeLocDao.removeEmployeeLoc(language, username);
    }
}
