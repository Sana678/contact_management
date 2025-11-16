package com.example.contactmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.contactmanagement.Entity.UserEntity;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
   Optional<UserEntity> findByEmail(String email);
   
   Optional<UserEntity> findByPhone(String phone);

}
