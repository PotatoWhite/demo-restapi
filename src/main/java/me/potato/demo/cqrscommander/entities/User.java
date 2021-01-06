package me.potato.demo.cqrscommander.entities;

import lombok.Getter;
import lombok.Setter;
import me.potato.demo.cqrscommander.expose.dtos.UserDto;
import me.potato.demo.cqrscommander.utils.JpaJsonConverter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@SecondaryTables({
                         @SecondaryTable(name = "user_contact"),
                         @SecondaryTable(name = "user_profile")
                 })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Email
  @Column(unique = true)
  private String account;

  @NotNull
  @NotBlank
  private String name;

  // profile
  @Column(table = "user_profile")
  private String        nationality;
  @Column(table = "user_profile")
  private LocalDateTime birthdate;


  // contact
  @Column(table = "user_contact")
  private String mobile;
  @Column(table = "user_contact")
  private String email;
  @Column(table = "user_contact")
  private String work;


  @Convert(converter = JpaJsonConverter.class)
  private List<Address> addresses;

  public static User fromDto(@Valid UserDto dto) {
    var aUser = new User();
    aUser.setAccount(dto.getAccount());
    aUser.setName(dto.getName());
    aUser.setNationality(dto.getNationality());
    aUser.setBirthdate(dto.getBirthdate());
    aUser.setMobile(dto.getMobile());
    aUser.setEmail(dto.getEmail());
    aUser.setWork(dto.getWork());
    aUser.setAddresses(dto.getAddresses());

    return aUser;
  }

  @PrePersist
  private void setExtra() {
    if(email == null) email = account;
  }

  public UserDto toDto() {
    var aDto = new UserDto();
    aDto.setAccount(this.getAccount());
    aDto.setId(this.getId());
    aDto.setName(this.getName());
    aDto.setAddresses(this.getAddresses());

    return aDto;
  }
}
