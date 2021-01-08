package me.potato.cqrscommander.logic;

import lombok.extern.slf4j.Slf4j;
import me.potato.cqrscommander.entities.User;
import me.potato.cqrscommander.entities.store.UserRepository;
import me.potato.pramework.BaseService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends BaseService<User, Long> {
  public UserService(UserRepository repository) {
    super(repository);
  }
}
