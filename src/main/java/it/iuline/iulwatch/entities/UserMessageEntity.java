package it.iuline.iulwatch.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserMessageEntity {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;
	  private String userName;
	  private String userMessage;

	  protected UserMessageEntity() {}
	  
	  public UserMessageEntity(String username, String usermessage)
	  {
		  this.userName = username;
		  this.userMessage = usermessage;
	  }

	  public Long getId()
	  {
		  return this.id;
	  }

	  public String getUserName()
	  {
		  return this.userName;
	  }
	  
	  public Long setUserName(String username)
	  {
		  this.userName = username;
		  return this.id;
	  }

	  public Long setUserMessage(String usermessage)
	  {
		  this.userMessage = usermessage;
		  return this.id;
	  }
	  
	  public String getUserMessage()
	  {
		  return this.userMessage;
	  }
	  
}
