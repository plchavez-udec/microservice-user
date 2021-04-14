package co.edu.ierdminayticha.sgd.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.user.entity.UserRoleEntity;

@Repository
public interface IUserRoleRepository extends CrudRepository<UserRoleEntity, Long> {

}
