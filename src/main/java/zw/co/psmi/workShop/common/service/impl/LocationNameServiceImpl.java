/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.workShop.common.dao.LocationNameDao;
import zw.co.psmi.workShop.common.entity.LocationName;
import zw.co.psmi.workShop.common.service.LocationNameService;

/**
 *
 * @author lionel
 */

@Service
public class LocationNameServiceImpl implements LocationNameService{
    private LocationNameDao locationNameDao;
    
    @Autowired
    public LocationNameServiceImpl(LocationNameDao locationNameDao) {
        this.locationNameDao = locationNameDao;
    }
    
    @Override
    public List<LocationName> findAllActive() {
        return locationNameDao.findByActiveStatusTrue();
    }

    @Override
    public JpaRepository<LocationName, Long> getDao() {
        return locationNameDao;
    }

    @Override
    public LocationName getByID(Long id) {
        LocationName locationName = locationNameDao.findOne(id);
        if (locationName == null) {
            locationName = new LocationName();
        }
        return locationName;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(LocationName locationName) {
        locationNameDao.save(locationName);
        return "Sucessfully saved Location Name";
    }

    @Override
    public List<LocationName> findAll() {
        return locationNameDao.findAll(); 
    }
    
}
