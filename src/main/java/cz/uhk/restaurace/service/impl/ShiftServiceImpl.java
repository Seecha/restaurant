package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.ShiftDao;
import cz.uhk.restaurace.model.Shift;
@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	private ShiftDao shiftDao;

	@Override
	@Transactional
	public void addShift(Shift shift) {
		shiftDao.addShift(shift);

	}

	@Override
	@Transactional
	public void updateShift(Shift shift) {
		shiftDao.updateShift(shift);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Shift> listShift() {
		return shiftDao.listShift();
	}

	@Override
	@Transactional(readOnly = true)
	public Shift getShiftById(int id) {
		return shiftDao.getShiftById(id);
	}

	@Override
	@Transactional
	public void removeShift(int id) {
		shiftDao.removeShift(id);

	}

}
