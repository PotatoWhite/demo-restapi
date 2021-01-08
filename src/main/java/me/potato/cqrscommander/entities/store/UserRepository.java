package me.potato.cqrscommander.entities.store;

import me.potato.cqrscommander.entities.User;
import me.potato.pramework.repository.EventableRepository;

public interface UserRepository extends EventableRepository<User, Long> {

}
