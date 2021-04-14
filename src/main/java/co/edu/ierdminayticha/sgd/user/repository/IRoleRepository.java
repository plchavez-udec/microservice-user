package co.edu.ierdminayticha.sgd.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.user.entity.RoleEntity;
import co.edu.ierdminayticha.sgd.user.entity.UserEntity;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {

}
