/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author lionel
 */
@Component
public class UsernameAuditorAware implements AuditorAware<String>  {
    @Override
    public String getCurrentAuditor() {
        String currentUserName="Anonymous User";
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.isAuthenticated()) {
                currentUserName = authentication.getName();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return currentUserName;
    }
}
