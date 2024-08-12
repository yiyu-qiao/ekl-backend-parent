package org.ekl.backend.ws.api.user;

import lombok.extern.slf4j.Slf4j;
import org.ekl.backend.ws.api.user.repository.UserRepository;
import org.ekl.backend.ws.exception.UserAlreadyExistException;
import org.ekl.backend.ws.exception.UserNotFoundException;
import org.ekl.backend.ws.model.Role;
import org.ekl.backend.ws.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException(String.format("Not found user with Id[%s]",username));
        }
        var roles = user.getRoles();
        for (Role role : roles) {
            log.info("role : {}" ,role.getDescription());
        }
        return user;
    }

    public boolean checkUserExistence(String username){
        User user = userRepository.findByUsername(username);
        if(user != null){
            return true;
        }
        return false;
    }

    public User save(User user) throws UserAlreadyExistException {
        if(checkUserExistence(user.getUsername())){
            throw new UserAlreadyExistException(String.format("Username %s exists already",user.getUsername()));
        }
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
