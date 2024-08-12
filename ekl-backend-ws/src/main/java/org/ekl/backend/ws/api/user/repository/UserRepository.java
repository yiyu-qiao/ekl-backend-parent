package org.ekl.backend.ws.api.user.repository;

import org.ekl.backend.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User save(User user);

/*    public Optional<User> getUserById(String id){
        if(id.equalsIgnoreCase("Test")){
            return Optional.empty();
        }
        var user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstname(id);
        user.setLastname(id);
        user.setBirthday(LocalDate.now());
        user.setUsername(id);
        return Optional.of(user);
    }*/
}
