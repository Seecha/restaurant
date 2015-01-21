package cz.uhk.restaurace.service.impl;

import cz.uhk.restaurace.dao.TempCustomerInfoDao;
import cz.uhk.restaurace.model.TempCustomerInfo;
import cz.uhk.restaurace.service.TempCustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dann on 22.12.2014.
 */

@Service
public class TempCustomerInfoServiceImpl implements TempCustomerInfoService {

    @Autowired
    private TempCustomerInfoDao tempCustomerInfoDao;

    @Override
    @Transactional
    public void addTempCustomerInfo(TempCustomerInfo info) {
        tempCustomerInfoDao.addTempCustomerInfo(info);
    }

	@Override
	@Transactional
	public TempCustomerInfo getTempCustomerInfoById(int id) {
		return tempCustomerInfoDao.getTempCustomerInfoById(id);
	}
}
