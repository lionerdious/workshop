/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import zw.co.psmi.workShop.assets.entity.Assets;
import zw.co.psmi.workShop.assets.service.AssetsService;
import zw.co.psmi.workShop.auth.entity.Login;
import zw.co.psmi.workShop.common.dao.PdfOutputDao;
import zw.co.psmi.workShop.common.entity.PdfOutput;
import zw.co.psmi.workShop.common.entity.PdfOutputModel;
import zw.co.psmi.workShop.common.service.PdfOutputService;

/**
 *
 * @author lionel
 */
@Service
@Slf4j
public class PdfOutputServiceImpl implements PdfOutputService {

    @Autowired
    private PdfOutputDao reportDao;
    @Autowired
    private AssetsService assetsService;

    @Autowired
    public PdfOutputServiceImpl(PdfOutputDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public JpaRepository<PdfOutput, Long> getDao() {
        return reportDao;
    }

    @Override
    public String save(PdfOutput report) {
        reportDao.save(report);
        return "Saved successfully";
    }

    @Override
    public byte[] outputReport(PdfOutputModel reportModel) {
        try {
            JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
            jasperReportsContext.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
            jasperReportsContext.setProperty("net.sf.jasperreports.default.font.name", "Sans Serif");

            PdfOutput report = reportDao.findByNameAndActiveStatusTrue(reportModel.getReportName());
            JasperPrint jprint = (JasperPrint) JasperFillManager
                    .fillReport(new ByteArrayInputStream(report
                            .getJasperData()), reportModel
                            .getParameter(), new JRBeanCollectionDataSource(reportModel
                                    .getFileldslist()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jprint, baos);
            return baos.toByteArray();
        } catch (JRException ex) {
            log.info("Error:", ex);
        }
        return new byte[1];
    }

    @Override
    public PdfOutput findById(Long Id) {
        return reportDao.findOne(Id);
    }

    @Override
    public List<PdfOutput> findByAllBYSearchCriteria(PdfOutput report) {
        return reportDao.findByName(report.getName());
    }

    @Override
    public List<PdfOutput> findAll() {
        return reportDao.findAll();
    }

    @Override
    public byte[] outputReport(Long id, String type) {
        log.info("outputReport");
        if ("ASSET_REGISTRATION".contentEquals("ASSET_REGISTRATION")) {
            return outputReport(findReportModelByAssetId(id));
        }
        if ("ASSET_MOVEMENT".contentEquals("ASSET_REGISTRATION")) {
            return outputReport(findAssetMovementByAssetId(id));
        }
        log.error("Output not found:{} id:{}", type, id);
        return "".getBytes();
    }

    private PdfOutputModel findReportModelByAssetId(Long id) {
        Assets asset = assetsService.getByID(id);
        PdfOutputModel reportModel = new PdfOutputModel();
        reportModel.setReportName("ASSET_REGISTRATION");
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("type", "" + asset.getAssetType().getName());
        parameterMap.put("model", "" + asset.getModel());
        parameterMap.put("serial", "" + asset.getSerial());
        parameterMap.put("location", "" + asset.getLocationName().getName());
        parameterMap.put("region", "" + asset.getSite().getRegion().name());
        parameterMap.put("site", "" + asset.getSite().getName());
        parameterMap.put("city", "" + asset.getSite().getCity());
        parameterMap.put("address", "" + asset.getSite().getAddress());
        parameterMap.put("tell", "" + asset.getSite().getTell());
        parameterMap.put("lob", "" + asset.getSite().getLob());
        reportModel.setParameter(parameterMap);
        return reportModel;
    }

    private PdfOutputModel findAssetMovementByAssetId(Long id) {
//        Assets asset = assetsService.getByID(id);
        PdfOutputModel reportModel = new PdfOutputModel();
//        reportModel.setReportName("ASSET_REGISTRATION");
//        Map<String, Object> parameterMap = new HashMap<>();
//        parameterMap.put("type", "");
//        parameterMap.put("model", "");
//        parameterMap.put("serial", "");
//        parameterMap.put("location", "");
//        parameterMap.put("site", "");
//        parameterMap.put("region", "");
//        reportModel.setParameter(parameterMap);
        return reportModel;
    }

    public Login getCurrentAuditor() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                if (authentication.getPrincipal() instanceof Login) {
                    return (Login) authentication.getPrincipal();
                }
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error("Authentication:{}", e.getMessage());
        }
        return null;
    }

}
