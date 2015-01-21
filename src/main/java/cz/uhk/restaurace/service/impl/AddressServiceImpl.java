package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.AddressDao;
import cz.uhk.restaurace.model.Address;
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	@Transactional
	public void addAddress(Address address) {
		addressDao.addAddress(address);

	}

	@Override
	@Transactional
	public void updateAddress(Address address) {
		addressDao.updateAddress(address);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Address> listAddress() {
		return addressDao.listAddress();
	}

	@Override
	@Transactional(readOnly = true)
	public Address getAddressById(int id) {
		return addressDao.getAddressById(id);
	}

	@Override
	@Transactional
	public void removeAddress(int id) {
		addressDao.removeAddress(id);

	}

}
