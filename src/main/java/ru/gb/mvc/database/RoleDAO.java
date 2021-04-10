package ru.gb.mvc.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mvc.domain.Role;

@Repository
public interface RoleDAO extends CrudRepository<Role, Long> {
}
