/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.workShop.basic.BaseEntity;

/**
 *
 * @author lionel
 */
@Entity
@Table(name="site")
@Data
public class Site extends BaseEntity{
    @Column(unique = true)
    private String name;
    private String city;
    private String address;
    private String tell;
    @Enumerated(EnumType.STRING)
    private Region region;
    @Enumerated(EnumType.STRING)
    private LOB lob;
}
