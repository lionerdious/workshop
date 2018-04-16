package zw.co.psmi.workShop.auth.service;

import java.util.List;

import zw.co.psmi.workShop.auth.entity.User;
import zw.co.psmi.workShop.basic.BasicService;

public interface UserService extends BasicService<User>{
public User findByUsername(String username);

}