/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.workShop.assets.entity.Assets;
import zw.co.psmi.workShop.common.entity.Region;

/**
 *
 * @author lionel
 */
public interface AssetsDao extends JpaRepository<Assets, Long> {
    public List<Assets> findByActiveStatusTrue();
    public List<Assets> findByRegionAndPurchaseDateBetween(Region region,Date getStartDate,Date getEndDate);
}
