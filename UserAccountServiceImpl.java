package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.UserAccount;
import com.example.repository.UserAccountRepo;

@Service // stereotype annotation used to represent java class as a spring bean class
public class UserAccountServiceImpl implements UserAccountService{
     
	//To build connection between repository and service
	
	@Autowired //Injecting repo into service for communication
	private UserAccountRepo userAccRepo;  //Repo object
	
	
	
	@Override
	//Can perform UPSERT (Insert and Update) operations
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		Integer userId = userAcc.getUserid();
		userAccRepo.save(userAcc);  // save is a predefined method in JPA
		if(userId ==  null) {
			return "User record saved";
		}else {
			return "User record updated";
		}
		
	}

	@Override
	public List<UserAccount> getAllUserAccounts() {
	 //List<UserAccount> accounts = userAccRepo.findAll();
		//return accounts;
		return userAccRepo.findAll(); //above two commented lines are optimized here.
	//findall-> to retreive all the records in the form of objects
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		Optional<UserAccount> findById = userAccRepo.findById(userId);
		if(findById.isPresent()){
			return findById.get();
		}
		
		return null;
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		
		boolean existsById= userAccRepo.existsById(userId);
		if(existsById) {
			userAccRepo.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
          
		try {
			userAccRepo.updateUserAccStatus(userId, status);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
