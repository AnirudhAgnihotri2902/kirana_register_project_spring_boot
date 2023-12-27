package org.example.kirana_register_project.crud.model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {

	private UUID id;
	private String firstName;
	private String lastName;
	private String emailId;
	private boolean isDeleted;
	private Date createdAt;
	private Date updatedAt;

	public Users() {
		
	}
	
	public Users(String firstName, String lastName, String emailId) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.isDeleted = false;
		this.createdAt = new Date();
		this.updatedAt = new Date();

	}

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", updatable = false)
	public Date getCreatedAt(){
		return createdAt;
	}


	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt ;
	}
	public void setUpdatedAt(Date updatedAt){
		this.updatedAt = updatedAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	public Date getUpdatedAt(){
		return updatedAt;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}
	
}
