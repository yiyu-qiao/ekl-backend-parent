package org.ekl.backend.ws.api.user.repository;

import org.ekl.backend.ws.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
}
