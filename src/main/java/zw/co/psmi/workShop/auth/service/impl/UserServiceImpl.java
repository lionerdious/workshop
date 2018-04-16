package zw.co.psmi.workShop.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import zw.co.psmi.workShop.auth.dao.UserDao;
import zw.co.psmi.workShop.auth.entity.User;
import zw.co.psmi.workShop.auth.service.UserService;


public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED)
    public String save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userDao.save(user);
        return "Sucessfully saved User";
    }

    @Override
    public User getByID(Long userID) {
        User user = this.userDao.findOne(userID);
        if (user == null) {
            user = new User();
        }
        return user;
    }


    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return userDao.findByUsername(username);
    }

    @Override
    public JpaRepository<User, Long> getDao() {
        return userDao;
    }
    

	  @Override
	    public List<User> findAll() {
	        return userDao.findAll();
	    }

}
