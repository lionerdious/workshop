/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.workShop.assets.entity.AssetType;

/**
 *
 * @author lionel
 */
public interface AssetTypeDao  extends JpaRepository<AssetType, Long> {
    List<AssetType> findByActiveStatusTrue();
    //List<AssetType> findByActiveStatusTrueAndNameContaining(String name);
    
}
