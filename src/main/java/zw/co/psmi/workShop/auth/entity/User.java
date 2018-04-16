package zw.co.psmi.workShop.auth.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.workShop.basic.BaseEntity;

@Entity
@Data
@Table(name="_users")
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String username;
    @javax.persistence.Lob
    private String password;
    

    @Override
    public String toString() {
        return "users{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password=" + password + "}";
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
}
