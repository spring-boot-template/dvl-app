package com.dvlcube.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dvlcube.app.model.User;

/**
 * @since 23 de mai de 2019
 * @author Ulisses Lima
 */
@Repository
public interface UserRepository extends DvlRepository<User, Long>, JpaRepository<User,Long> {
	/**
	 * @param email
	 * @return user
	 * @since 23 de mai de 2019
	 * @author Ulisses Lima
	 */
	Optional<User> findByEmail(String email);
}
