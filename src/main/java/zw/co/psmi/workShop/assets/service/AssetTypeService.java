/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.service;

import java.util.List;
import zw.co.psmi.workShop.assets.entity.AssetType;
import zw.co.psmi.workShop.basic.BasicService;

/**
 *
 * @author lionel
 */
public interface AssetTypeService extends BasicService<AssetType>{
    public List<AssetType> findAllActive();
}
