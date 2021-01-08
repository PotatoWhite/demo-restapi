package me.potato.pramework.entities;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public interface Eventable<ID> {
  ID getId();
}
