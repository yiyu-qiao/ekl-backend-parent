package org.ekl.backend.ws.api.user.repository;

import org.ekl.backend.ws.model.Group;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupRepository extends CrudRepository<Group, UUID> {
}
