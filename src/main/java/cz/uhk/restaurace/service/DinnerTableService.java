package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.DinnerTable;

public interface DinnerTableService {
	public void addTable(DinnerTable dinnerTable);
	public void updateTable(DinnerTable dinnerTable);
	public List<DinnerTable> listTable();
	public DinnerTable getTableById(int id);
	public void removeTable(int id);

}
