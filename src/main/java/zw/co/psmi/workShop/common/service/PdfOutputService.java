/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service;

import java.util.List;
import zw.co.psmi.workShop.basic.BasicService;
import zw.co.psmi.workShop.common.entity.PdfOutput;
import zw.co.psmi.workShop.common.entity.PdfOutputModel;

/**
 *
 * @author lionel
 */
public interface PdfOutputService extends BasicService<PdfOutput> {

    public List<PdfOutput> findByAllBYSearchCriteria(PdfOutput report);

    public PdfOutput findById(Long Id);

    public byte[] outputReport(PdfOutputModel reportModel);

    public List<PdfOutput> findAll();
    
    public byte[] outputReport(Long id, String type);
    
    public PdfOutputModel findReportModelByAssetId(Long id);
}
