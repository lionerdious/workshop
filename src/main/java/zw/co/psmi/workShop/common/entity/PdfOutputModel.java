/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.entity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lionel
 */
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class PdfOutputModel {
    private String reportName;
    private Map<String, Object> parameter;
    private List<Map<String, String>> fileldslist;
}
