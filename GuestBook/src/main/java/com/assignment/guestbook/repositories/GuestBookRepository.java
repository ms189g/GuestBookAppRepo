package com.assignment.guestbook.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.guestbook.entities.GuestBookEntity;

/**
 * GuestBookRepository, Repository class for GuestBookEntity
 * @author Manish
 *
 */
public interface GuestBookRepository extends CrudRepository<GuestBookEntity, Integer> {

	/**
	 * @param user_id
	 * @return
	 */
	@Query("SELECT a FROM GuestBookEntity a WHERE a.userName=:user_id")
	GuestBookEntity fetchUser(@Param("user_id") String user_id);
	
	/**
	 * @param user_id
	 * @return
	 */
	@Query("SELECT a FROM GuestBookEntity a WHERE a.id=:user_id")
	GuestBookEntity fetchUserById(@Param("user_id") Integer user_id);
	
	/**
	 * @param user_id
	 * @param fName
	 * @param lName
	 * @param uName
	 * @param addr
	 * @param ag
	 * @return
	 */
	@Transactional
	@Modifying
	@Query("UPDATE GuestBookEntity set firstName=:fName, lastName=:lName,"
			+ "userName=:uName, address=:addr, age=:ag WHERE id=:user_id")
	Integer updateUserById(@Param("user_id") Integer user_id, @Param("fName") String fName, @Param("lName") String lName
			,@Param("uName") String uName, @Param("addr") String addr, @Param("ag") Integer ag);
}
