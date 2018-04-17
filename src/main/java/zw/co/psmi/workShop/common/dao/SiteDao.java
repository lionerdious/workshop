/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.workShop.common.entity.LocationName;
import zw.co.psmi.workShop.common.entity.Site;

/**
 *
 * @author lionel
 */
public interface SiteDao extends JpaRepository<Site, Long>{
     List<Site> findByActiveStatusTrue();
    List<Site> findByActiveStatusTrueAndNameContaining(String name);
    public Page<Site> findByNameContainingAndActiveStatusTrue(String name, Pageable pageable);
}
