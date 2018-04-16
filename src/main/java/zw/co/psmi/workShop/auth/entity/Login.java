package zw.co.psmi.workShop.auth.entity;

import zw.co.psmi.workShop.auth.entity.*;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

@Data
public class Login extends User{
	
	    private Long userID;
	    
	    public Login(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	        super(username, password, authorities);
	        // TODO Auto-generated constructor stub
	    }

	
}
