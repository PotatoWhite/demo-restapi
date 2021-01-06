package me.potato.demo.cqrscommander.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Address {
  @NotNull
  private Type   type;
  private String zipcode;
  private String address;

  enum Type {
    Home, Office
  }
}
