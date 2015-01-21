package cz.uhk.restaurace.service;

import java.util.List;
import cz.uhk.restaurace.model.Shift;

public interface ShiftService {
	public void addShift(Shift shift);
	public void updateShift(Shift shift);
	public List<Shift> listShift();
	public Shift getShiftById(int id);
	public void removeShift(int id);


}
