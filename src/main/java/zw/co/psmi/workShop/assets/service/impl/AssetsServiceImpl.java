/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.workShop.assets.dao.AssetsDao;
import zw.co.psmi.workShop.assets.entity.Assets;
import zw.co.psmi.workShop.assets.service.AssetsService;

/**
 *
 * @author lionel
 */
public class AssetsServiceImpl implements AssetsService {

    @Autowired
    private AssetsDao assetsDao;

    @Autowired
    public AssetsServiceImpl(AssetsDao assetsDao) {
        this.assetsDao = assetsDao;
    }

    @Override
    public List<Assets> findAllActive() {
        return assetsDao.findByActiveStatusTrue();
    }

    @Override
    public List<Assets> findByAllBYSearchCriteria(Assets assets) {
        return assetsDao.findByRegionAndPurchaseDateBetween(assets.getSite().getRegion(), assets.getStartDate(), assets.getEndDate());
    }

    @Override
    public JpaRepository<Assets, Long> getDao() {
        return assetsDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(Assets assets) {
        assetsDao.save(assets);
        return "Book Stock Successfully Saved";
    }

}
