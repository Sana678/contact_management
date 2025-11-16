package com.example.contactmanagement.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.contactmanagement.DTO.ContactDto;
import com.example.contactmanagement.Entity.ContactEntity;
import com.example.contactmanagement.Entity.EmailEntity;
import com.example.contactmanagement.Entity.PhoneEntity;
import com.example.contactmanagement.Entity.UserEntity;
import com.example.contactmanagement.Repository.ContactRepository;
import com.example.contactmanagement.Repository.UserRepository;




@Service
public class ContactService {

	 private static final Logger log = LoggerFactory.getLogger(ContactService.class);
	 
private final ContactRepository contactRepository;
private final UserRepository userRepository;

public ContactService(ContactRepository contactRepository, UserRepository userRepository) {
    this.contactRepository = contactRepository;
    this.userRepository = userRepository;
}


// CREATE

public ContactEntity createContact(Long ownerId, ContactDto dto) {
    log.info("Creating contact for ownerId={}", ownerId);

    try {
        UserEntity owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ContactEntity contact = new ContactEntity();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setTitle(dto.getTitle());
        contact.setOwner(owner);

        if (dto.getEmails() != null) {
            for (String email : dto.getEmails()) {
                EmailEntity e = new EmailEntity();
                e.setEmail(email);
                e.setContact(contact);
                contact.getEmails().add(e);
            }
        }

        if (dto.getPhones() != null) {
            for (String phone : dto.getPhones()) {
                PhoneEntity p = new PhoneEntity();
                p.setNumber(phone);
                p.setContact(contact);
                contact.getPhones().add(p);
            }
        }

        ContactEntity saved = contactRepository.save(contact);
        log.info("Contact created with id={}", saved.getId());
        return saved;
    } catch (Exception ex) {
        log.error("Error creating contact for ownerId={}", ownerId, ex);
        throw ex;
    }
}

// READ
public ContactEntity getContact(Long id) {
    log.debug("Fetching contact with id={}", id);
    return contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
}

// LIST
public Page<ContactEntity> listContacts(Long ownerId, Pageable pageable) {
    log.debug("Listing contacts for ownerId={} with pageable={}", ownerId, pageable);
    return contactRepository.findByOwnerId(ownerId, pageable);
}

// UPDATE
public ContactEntity updateContact(Long id, ContactDto dto) {
    log.info("Updating contact id={}", id);

    try {
        ContactEntity contact = getContact(id);

        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setTitle(dto.getTitle());

        contact.getEmails().clear();
        contact.getPhones().clear();

        if (dto.getEmails() != null) {
            for (String email : dto.getEmails()) {
                EmailEntity e = new EmailEntity();
                e.setEmail(email);
                e.setContact(contact);
                contact.getEmails().add(e);
            }
        }

        if (dto.getPhones() != null) {
            for (String phone : dto.getPhones()) {
                PhoneEntity p = new PhoneEntity();
                p.setNumber(phone);
                p.setContact(contact);
                contact.getPhones().add(p);
            }
        }

        ContactEntity updated = contactRepository.save(contact);
        log.info("Contact updated successfully id={}", updated.getId());
        return updated;
    } catch (Exception ex) {
        log.error("Error updating contact id={}", id, ex);
        throw ex;
    }
}

// DELETE
public void deleteContact(Long id) {
    log.info("Deleting contact id={}", id);
    try {
        contactRepository.deleteById(id);
        log.info("Contact deleted id={}", id);
    } catch (Exception ex) {
        log.error("Error deleting contact id={}", id, ex);
        throw ex;
    }
}

// SEARCH
public List<ContactEntity> search(Long ownerId, String keyword) {
    log.debug("Searching contacts for ownerId={} with keyword={}", ownerId, keyword);
    return contactRepository.search(ownerId, keyword);
}
}




   