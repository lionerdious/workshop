package zw.co.psmi.workShop.basic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{
	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	   @XmlTransient
	   public String getAuditDetails(){
	       return toString();
	   }
	   
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    protected long id;	    

	    @Column(name = "creation_time", updatable = false)
	    private String creationTime;
	    
	    @PrePersist
	    public void prePersist() {
	        this.creationTime = dtf.format(LocalDateTime.now());
	        }

}
