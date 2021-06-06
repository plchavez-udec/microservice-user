package co.edu.ierdminayticha.sgd.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.ierdminayticha.sgd.user.entity.RoleEntity;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {

}
