package co.edu.ierdminayticha.sgd.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.user.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
	
	List<UserEntity> findAllByEnabled(Boolean enabled);
	
	UserEntity findByUsernameAndEnabled(String username, boolean enabled);
	
	@Transactional
	@Modifying
	@Query(value = "update UserEntity us set us.enabled = false where us.id = ?1")
	void disableUser(Long id);

}
