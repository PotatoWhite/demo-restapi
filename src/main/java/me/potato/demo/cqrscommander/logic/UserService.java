package me.potato.demo.cqrscommander.logic;

import lombok.extern.slf4j.Slf4j;
import me.potato.demo.cqrscommander.entities.User;
import me.potato.demo.cqrscommander.entities.store.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends BaseService<User, Long> {
  public UserService(UserRepository repository) {
    super(repository);
  }
}
