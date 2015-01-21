package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Address;

public interface AddressDao {
	public void addAddress(Address address);
	public void updateAddress(Address address);
	public List<Address> listAddress();
	public Address getAddressById(int id);
	public void removeAddress(int id);
	

}
