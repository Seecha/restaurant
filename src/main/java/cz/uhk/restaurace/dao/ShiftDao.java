package cz.uhk.restaurace.dao;

import java.util.List;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.User;

public interface ShiftDao {
	public void addShift(Shift shift);
	public void updateShift(Shift shift);
	public List<Shift> listShift();
	public Shift getShiftById(int id);
	public void removeShift(int id);

	/**
	 * Get employees current shift
	 * @param hour
	 * @param day
	 * @param user
	 * @return
	 */
	public Shift getCurrentEmployeesShift(int hour, Shift.Day day, User user);
}
