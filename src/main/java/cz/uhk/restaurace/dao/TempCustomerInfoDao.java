package cz.uhk.restaurace.dao;

import cz.uhk.restaurace.model.TempCustomerInfo;

/**
 * Created by dann on 22.12.2014.
 */
public interface TempCustomerInfoDao {
   public void addTempCustomerInfo(TempCustomerInfo info);
   public TempCustomerInfo getTempCustomerInfoById(int id);
}
