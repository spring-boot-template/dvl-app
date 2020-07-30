package com.dvlcube.app.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dvlcube.app.repository.DvlRepository;
import com.dvlcube.app.manager.data.UserBean;

/**
 * @since 23 de mai de 2019
 * @author Ulisses Lima
 */
@Repository
public interface UserRepository extends DvlRepository<UserBean, Long> {
	/**
	 * @param email
	 * @return user
	 * @since 23 de mai de 2019
	 * @author Ulisses Lima
	 */
	Optional<UserBean> findByEmail(String email);
}
