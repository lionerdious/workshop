/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.psmi.workShop.auth.entity.User;

/**
 *
 * @author lionel
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	public	User findByUsername(String username);
}
