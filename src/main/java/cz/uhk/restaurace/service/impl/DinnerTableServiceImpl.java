package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.DinnerTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.DinnerTableDao;
import cz.uhk.restaurace.model.DinnerTable;
@Service
public class DinnerTableServiceImpl implements DinnerTableService {

	@Autowired
	private DinnerTableDao dinnerTableDao;

	@Override
	@Transactional
	public void addTable(DinnerTable dinnerTable) {
		dinnerTableDao.addTable(dinnerTable);

	}

	@Override
	@Transactional
	public void updateTable(DinnerTable dinnerTable) {
		dinnerTableDao.updateTable(dinnerTable);

	}

	@Override
	@Transactional(readOnly = true)
	public List<DinnerTable> listTable() {
		return dinnerTableDao.listTable();
	}

	@Override
	@Transactional(readOnly = true)
	public DinnerTable getTableById(int id) {
		return dinnerTableDao.getTableById(id);
	}

	@Override
	@Transactional
	public void removeTable(int id) {
		dinnerTableDao.removeTable(id);

	}

}
