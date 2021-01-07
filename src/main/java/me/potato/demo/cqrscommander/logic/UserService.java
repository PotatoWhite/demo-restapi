package me.potato.demo.cqrscommander.logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.demo.cqrscommander.entities.User;
import me.potato.demo.cqrscommander.entities.store.UserRepository;
import me.potato.demo.cqrscommander.expose.dtos.UserDto;
import me.potato.demo.cqrscommander.utils.GsonTools;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;

  // create
  public Optional<User> create(User aUser) throws EntityExistsException {
    return Optional.ofNullable(userRepository.save(aUser));
  }

  // retrieve
  public Optional<User> retrieve(Long id) {
    return userRepository.findById(id);
  }

  // update
  public Optional<User> patch(Long id, Map<String, Object> fields) throws GsonTools.JsonObjectExtensionConflictException {
    Optional<User> byId = userRepository.findById(id);
    var            user = byId.orElseThrow(() -> new EntityNotFoundException("entity not found id="+id));

    return Optional.ofNullable(userRepository.save(GsonTools.merge(user, fields)));
  }

  // delete
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  // replace
  public Optional<User> replace(Long id, UserDto replace) {
    User replaceUser = User.fromDto(replace);
    replaceUser.setId(id);
    return Optional.ofNullable(userRepository.save(replaceUser));
  }
}
