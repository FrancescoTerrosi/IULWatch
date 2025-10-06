package it.iuline.iulwatch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.iuline.iulwatch.entities.UserMessageEntity;

public interface UserMessageRepository extends CrudRepository<UserMessageEntity, Long>{

	List<UserMessageEntity> findByUserName(String userName);
	
	Optional<UserMessageEntity> findById(Long id);
	
}
