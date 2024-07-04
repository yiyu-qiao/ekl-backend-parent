package org.ekl.backend.ws.api.user;

import jakarta.validation.Valid;
import org.ekl.backend.ws.exception.UserAlreadyExistException;
import org.ekl.backend.ws.exception.UserNotFoundException;
import org.ekl.backend.ws.model.User;
import org.ekl.backend.ws.token.JWTValidator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private JWTValidator jwtValidator = new JWTValidator();

    @GetMapping(path = "/api/user/find/{username}")
//    @PreAuthorize("hasRole(USER)")
    public ResponseEntity<?> getUserDetail(@PathVariable String username) throws UserNotFoundException {
        var user = userService.getUserByUsername(username);
        user.setPassword(""); //remove the password before return user
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(path="/api/user/new", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user) throws UserAlreadyExistException {
            var savedUser=userService.save(user);
            return ResponseEntity.ok().body(savedUser);
    }

    //TODO remove test code
    @GetMapping("/test/user")
    public ResponseEntity<?> getTestUserDetail(@RequestHeader("Authorization") String jwt) {
        var isValid = jwtValidator.validateJWT(jwt);
        if(isValid){
            Claims claims = jwtValidator.readJWT(jwt);
            return ResponseEntity.ok().body(claims);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

//   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping(value = "/api/user/list-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUser(){
        var listUsers = userService.findAll();
        return ResponseEntity.ok().body(listUsers);
    }
}
