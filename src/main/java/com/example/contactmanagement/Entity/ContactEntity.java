package com.example.contactmanagement.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contacts")
@Data
public class ContactEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String firstName;
private String lastName;
private String title;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "owner_id")
private UserEntity owner;

@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
private List<EmailEntity> emails = new ArrayList<>();

@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
private List<PhoneEntity> phones = new ArrayList<>();

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public UserEntity getOwner() {
return owner;
}

public void setOwner(UserEntity owner) {
this.owner = owner;
}

public List<EmailEntity> getEmails() {
return emails;
}

public void setEmails(List<EmailEntity> emails) {
this.emails = emails;
}

public List<PhoneEntity> getPhones() {
return phones;
}

public void setPhones(List<PhoneEntity> phones) {
this.phones = phones;
}

}

