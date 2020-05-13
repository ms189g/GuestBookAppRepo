package com.assignment.guestbook.repositories;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.guestbook.entities.UserEventsEntity;

public interface UserEventsRepository extends CrudRepository<UserEventsEntity, Integer> {

	
	 @Query("SELECT a FROM UserEventsEntity a WHERE a.userName=:user_id")
	 List<UserEventsEntity> fetchUserEvent(@Param("user_id") String user_id);
	 
	 @Query("SELECT a FROM UserEventsEntity a WHERE a.id=:user_id")
	 UserEventsEntity fetchUserById(@Param("user_id") Integer user_id);
	 
	 @Query("SELECT a.picByte FROM UserEventsEntity a WHERE a.id=:user_id")
	 Blob readImage(@Param("user_id") Integer user_id);
		
		@Transactional
		@Modifying
		@Query("UPDATE UserEventsEntity set userName=:fName, eventName=:lName,"
				+ "eventDate=:uName, notes=:note, fileName=:ag WHERE id=:user_id")
		Integer updateUserById(@Param("user_id") Integer user_id, @Param("fName") String fName, @Param("lName") String lName
				,@Param("uName") Date uName, @Param("note") String note, @Param("ag") String fileName);
		
		@Transactional
		@Modifying
		@Query("UPDATE UserEventsEntity set isApproved=:value WHERE id=:user_id")
		Integer updateApprovedStatus(@Param("value") String value, @Param("user_id") Integer user_id);
}
