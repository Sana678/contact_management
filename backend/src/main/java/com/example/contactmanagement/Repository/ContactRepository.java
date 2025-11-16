package com.example.contactmanagement.Repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.contactmanagement.Entity.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
	
    Page<ContactEntity> findByOwnerId(Long ownerId, Pageable pageable);
    
    Page<ContactEntity> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String s, String t, Pageable pageable);

    @Query("SELECT c FROM ContactEntity c WHERE c.owner.id = :ownerId AND (LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<ContactEntity> search(Long ownerId, String keyword);

 
}