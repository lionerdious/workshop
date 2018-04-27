/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zw.co.psmi.workShop.assets.dao.AssetTypeDao;
import zw.co.psmi.workShop.assets.entity.AssetType;
import zw.co.psmi.workShop.assets.service.AssetTypeService;

/**
 *
 * @author lionel
 */
@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeDao assetTypeDao;

    @Autowired
    public AssetTypeServiceImpl(AssetTypeDao assetTypeDao) {
        this.assetTypeDao = assetTypeDao;
    }

    @Override
    public List<AssetType> findAllActive() {
        return assetTypeDao.findByActiveStatusTrue();
    }

    @Override
    public JpaRepository<AssetType, Long> getDao() {
        return assetTypeDao;
    }

    @Override
    public AssetType getByID(Long id) {
        AssetType assetType = assetTypeDao.findOne(id);
        if (assetType == null) {
            assetType = new AssetType();
        }
        return assetType;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(AssetType assetType) {
        assetTypeDao.save(assetType);
        return "Sucessfully saved Location Name";
    }

    @Override
    public List<AssetType> findAll() {
        return assetTypeDao.findAll();
    }
}
