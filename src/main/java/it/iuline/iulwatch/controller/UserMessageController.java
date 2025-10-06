package it.iuline.iulwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.iuline.iulwatch.entities.UserMessageEntity;
import it.iuline.iulwatch.repository.UserMessageRepository;

@RestController
@RequestMapping("jpatest")
public class UserMessageController {

	@Autowired
	private UserMessageRepository userMessageRepository;
	
	private final String message_saved = "Message from: %s\n\nContent: %s";
	
	@GetMapping("/getAll")
	public @ResponseBody Iterable<UserMessageEntity> getMessages()
	{
		return userMessageRepository.findAll();
	}
	
	@GetMapping("/get")
	public @ResponseBody Iterable<UserMessageEntity> getMessagesByUserName(@RequestParam String userName)
	{
		return userMessageRepository.findByUserName(userName);
	}
	
	@PostMapping("/add")
	public @ResponseBody String addNewMessage(
			@RequestParam String userName,
			@RequestParam String userMessage)
	{
		UserMessageEntity userMessageEntity = new UserMessageEntity(userName, userMessage);
		userMessageRepository.save(userMessageEntity);
		return String.format(this.message_saved, userName, userMessage);
	}
	
}
