package com.example.contactmanagement.Controller;

import com.example.contactmanagement.DTO.ContactDto;
import com.example.contactmanagement.Entity.ContactEntity;
import com.example.contactmanagement.Service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

	private final ContactService contactService;
   
   
   public ContactController(ContactService contactService) {
	    this.contactService = contactService;
	    
  }
   
   @GetMapping
   public Page<ContactEntity> list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) Long ownerId) {
       return contactService.listContacts(ownerId, PageRequest.of(page, size));
   }
   
   @PostMapping
   public ContactEntity create(@RequestParam Long ownerId, @RequestBody ContactDto dto) {
   return contactService.createContact(ownerId, dto);
   }
   
   @GetMapping("/{id}")
   public ContactEntity getOne(@PathVariable Long id) {
   return contactService.getContact(id);
   }
   
   @PutMapping("/{id}")
   public ContactEntity update(@PathVariable Long id, @RequestBody ContactDto dto) {
   return contactService.updateContact(id, dto);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
   contactService.deleteContact(id);
   return ResponseEntity.ok(java.util.Map.of("deleted", true));
   }
}
   
  