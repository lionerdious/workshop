/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.workShop.common.dao.SiteDao;
import zw.co.psmi.workShop.common.entity.LocationName;
import zw.co.psmi.workShop.common.entity.Site;
import zw.co.psmi.workShop.common.service.SiteService;

/**
 *
 * @author lionel
 */
@Service
public class SiteServiceImpl implements SiteService {

    private SiteDao siteDao;

    @Autowired
    public SiteServiceImpl(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

    @Override
    public List<Site> findAllActive() {
        return siteDao.findByActiveStatusTrue();
    }

    @Override
    public JpaRepository<Site, Long> getDao() {
        return siteDao;
    }

    @Override
    public Site getByID(Long id) {
        Site site = siteDao.findOne(id);
        if (site == null) {
            site = new Site();
        }
        return site;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(Site site) {
        siteDao.save(site);
        return "Sucessfully saved Site Name";
    }

    @Override
    public List<Site> findAll() {
        return siteDao.findAll();
    }

    @Override
    public Page<Site> findByNamePageable(Pageable pageable, String name) {
        return siteDao.findByNameContainingAndActiveStatusTrue(name, pageable);
    }

    @Override
    public List<Site> findByNameSearch(String name) {
         return siteDao.findByName(name);
    }

}
