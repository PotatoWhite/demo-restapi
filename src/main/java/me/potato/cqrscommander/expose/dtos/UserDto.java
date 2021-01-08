package me.potato.cqrscommander.expose.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.potato.cqrscommander.entities.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
  private Long   id;
  @Email
  @NotNull
  private String account;

  @NotNull
  private String name;


  // profile
  private String        nationality;
  private LocalDateTime birthdate;


  // contact
  private String mobile;
  private String email;
  private String work;

  private List<Address> addresses;
}
