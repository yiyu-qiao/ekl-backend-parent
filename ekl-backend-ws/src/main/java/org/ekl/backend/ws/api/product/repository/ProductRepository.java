package org.ekl.backend.ws.api.product.repository;

import org.ekl.backend.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<User, UUID> {

}
