/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.psmi.workShop.common.entity.PdfOutput;

/**
 *
 * @author lionel
 */
@Repository
public interface PdfOutputDao extends JpaRepository<PdfOutput, Long>{
    PdfOutput findByNameAndActiveStatusTrue(String name);
    List<PdfOutput> findByName(String name);
}
