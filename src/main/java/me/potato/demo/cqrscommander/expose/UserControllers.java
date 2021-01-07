package me.potato.demo.cqrscommander.expose;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.potato.demo.cqrscommander.entities.User;
import me.potato.demo.cqrscommander.expose.dtos.UserDto;
import me.potato.demo.cqrscommander.logic.UserService;
import me.potato.demo.cqrscommander.utils.GsonTools;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserControllers {
  private final UserService userService;

  @PostMapping
  public ResponseEntity createUser(@RequestBody @Valid UserDto createUserForm) {
    // 계정 생성
    Optional<User> save = null;
    try {
      save = userService.create(User.fromDto(createUserForm));
      return save.map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user.toDto()))
                 .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    } catch(EntityExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    } catch(DataIntegrityViolationException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDto replace) {
    try {
      return userService.replace(id, replace).map(user -> ResponseEntity.status(HttpStatus.OK).body(user.toDto()))
                        .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    } catch(EntityNotFoundException e) {
      return ResponseEntity.noContent().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity updateUser(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
    try {
      return userService.patch(id, fields)
                        .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                        .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    } catch(EntityNotFoundException e) {
      return ResponseEntity.noContent()
                           .build();
    } catch(GsonTools.JsonObjectExtensionConflictException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity getUser(@PathVariable Long id) {
    Optional<User> byId = userService.retrieve(id);
    return byId.map(user -> ResponseEntity.status(HttpStatus.OK).body(user.toDto()))
               .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
