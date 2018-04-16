/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.workShop.basic;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eunice
 * @param <T>
 */
public interface BasicService<T> {

    public JpaRepository<T, Long> getDao();

    default T getByID(Long id) {
        return getDao().findOne(id);
    }
	@Transactional(isolation=Isolation.READ_UNCOMMITTED,propagation=Propagation.REQUIRED)
    public String save(T t);

    default List<T> findAll() {
        return getDao().findAll();
    }
}
