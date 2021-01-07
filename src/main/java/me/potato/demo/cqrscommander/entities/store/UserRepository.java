package me.potato.demo.cqrscommander.entities.store;

import me.potato.demo.cqrscommander.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
