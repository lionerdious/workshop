/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service;

import java.util.List;
import zw.co.psmi.workShop.basic.BasicService;
import zw.co.psmi.workShop.common.entity.LocationName;

/**
 *
 * @author lionel
 */
public interface LocationNameService extends BasicService<LocationName>{
    public List<LocationName> findAllActive();
}
