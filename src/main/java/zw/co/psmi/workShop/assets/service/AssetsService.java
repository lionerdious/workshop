/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.service;

import java.util.List;
import zw.co.psmi.workShop.assets.entity.Assets;
import zw.co.psmi.workShop.basic.BasicService;

/**
 *
 * @author lionel
 */
public interface AssetsService extends BasicService<Assets> {

    public List<Assets> findAllActive();

    public List<Assets> findByAllBYSearchCriteria(Assets assets);
}
