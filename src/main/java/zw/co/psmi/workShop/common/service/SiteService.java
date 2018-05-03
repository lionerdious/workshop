/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.psmi.workShop.basic.BasicService;
import zw.co.psmi.workShop.common.entity.Site;

/**
 *
 * @author lionel
 */
public interface SiteService extends BasicService<Site>{
    public List<Site> findAllActive();
    public Page<Site> findByNamePageable(Pageable pageable, String prepairString);
    public List<Site> findByNameSearch(String name);
}
