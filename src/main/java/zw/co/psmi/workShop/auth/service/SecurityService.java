package zw.co.psmi.workShop.auth.service;

import zw.co.psmi.workShop.auth.service.*;
import javax.servlet.http.HttpServletRequest;

import zw.co.psmi.workShop.auth.entity.Login;

public interface SecurityService {

	String findLoggedInUsername();

	 public   void autologin(String username, String password);
	 public String getIPAddress(HttpServletRequest request);
	 public String getBrowser(HttpServletRequest request);
	
}
