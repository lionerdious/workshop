package zw.co.psmi.workShop.auth.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import zw.co.psmi.workShop.auth.entity.Login;
import zw.co.psmi.workShop.auth.entity.User;
import zw.co.psmi.workShop.auth.service.UserService;


@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 log.info("User request:{}",username);
    	try {
            User user = userService.findByUsername(username);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            Login login = new Login(user.getUsername(), user.getPassword(), grantedAuthorities);
            login.setUserID(user.getId());
            return login;
        } catch (Exception e) {
            log.error("Wrong Username", e);
        }
        return null;
    }
}
