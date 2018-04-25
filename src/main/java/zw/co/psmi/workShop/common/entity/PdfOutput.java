/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.workShop.basic.BaseEntity;

/**
 *
 * @author lionel
 */

@Entity
@Data
@Table(name="pdf_output")
public class PdfOutput extends BaseEntity {
    private String name;
    @Lob
    private byte[] jasperData;
}
