package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.UserAccount;

import jakarta.transaction.Transactional;

public interface UserAccountRepo extends JpaRepository <UserAccount, Integer> {
 // to perform the CRUD operations, JPA will provide the methods.
	
	@Modifying              //because these are non select operations
	@Transactional      //because these are non select operations
	@Query("update UserAccount set activeSw=:status where userId, statuss")  //custom query implementation
	public void updateUserAccStatus(Integer userId, String status);
	
}
