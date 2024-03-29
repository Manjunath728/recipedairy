package com.myrecipedairy.Usercrud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myrecipedairy.Usercrud.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
