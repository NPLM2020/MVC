package ru.gb.mvc.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.mvc.domain.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
}
