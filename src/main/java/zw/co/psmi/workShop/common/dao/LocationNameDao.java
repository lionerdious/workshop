/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.workShop.common.entity.LocationName;

/**
 *
 * @author lionel
 */
public interface LocationNameDao extends JpaRepository<LocationName, Long> {
    List<LocationName> findByActiveStatusTrue();
    List<LocationName> findByActiveStatusTrueAndNameContaining(String name);
}
