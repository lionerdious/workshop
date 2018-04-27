/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.assets.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import zw.co.psmi.workShop.basic.BaseEntity;
import zw.co.psmi.workShop.common.entity.LocationName;
import zw.co.psmi.workShop.common.entity.Site;

/**
 *
 * @author lionel
 */
@Entity
@Table(name="site")
@Data
public class Assets extends BaseEntity{
    @Column(unique = true)
    private String serial;
    private String model;
    private Site site;
    private LocationName locationName;    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "purchase_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    @Transient
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate = new Date();
    @Transient
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate = new Date();
}
