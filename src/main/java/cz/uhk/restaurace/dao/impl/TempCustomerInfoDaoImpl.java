package cz.uhk.restaurace.dao.impl;

import cz.uhk.restaurace.dao.TempCustomerInfoDao;
import cz.uhk.restaurace.model.TempCustomerInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by dann on 22.12.2014.
 */

@Repository
public class TempCustomerInfoDaoImpl implements TempCustomerInfoDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTempCustomerInfo(TempCustomerInfo info) {
        this.sessionFactory.getCurrentSession().persist(info);
    }

	@Override
	public TempCustomerInfo getTempCustomerInfoById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		TempCustomerInfo ci = (TempCustomerInfo)session.get(TempCustomerInfo.class, id);
		return ci;
	}
}
