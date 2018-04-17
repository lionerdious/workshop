package zw.co.psmi.workShop.basic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.Version;
import zw.co.psmi.workShop.UsernameAuditorAware;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    private static final UsernameAuditorAware usernameAuditorAware = new UsernameAuditorAware();
    private static final Gson gson = new Gson();

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @XmlTransient
    public String getAuditDetails() {
        return toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Version
    private long version = 1L;

    @Column(name = "created_by_user", updatable = false)
    @CreatedBy
    private String createdByUser;

    @Column(name = "creation_time", updatable = false)
    private String creationTime;

    @Column(name = "modified_by_user")
    @LastModifiedBy
    private String modifiedByUser;

    @Column(name = "modification_time")
    private String modificationTime;
    protected boolean activeStatus = true;

    @PrePersist
    public void prePersist() {
        this.creationTime = dtf.format(LocalDateTime.now());
        this.modificationTime = dtf.format(LocalDateTime.now());
        this.createdByUser = usernameAuditorAware.getCurrentAuditor();
        this.modifiedByUser = usernameAuditorAware.getCurrentAuditor();
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = dtf.format(LocalDateTime.now());
        this.modifiedByUser = usernameAuditorAware.getCurrentAuditor();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final BaseEntity other = (BaseEntity) obj;
        return this.id == other.id;
    }
}
