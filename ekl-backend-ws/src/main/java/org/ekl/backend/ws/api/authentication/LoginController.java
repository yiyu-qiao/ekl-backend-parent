package org.ekl.backend.ws.api.authentication;

import org.ekl.backend.ws.api.user.repository.GroupRepository;
import org.ekl.backend.ws.api.user.repository.RoleRepository;
import org.ekl.backend.ws.api.user.UserService;
import org.ekl.backend.ws.exception.UserNotFoundException;
import org.ekl.backend.ws.exception.UsernameOrPasswordInvalidException;
import org.ekl.backend.ws.model.User;
import org.ekl.backend.ws.token.JWTProvider;
import org.ekl.backend.ws.api.buildinfo.EklBuildInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@Slf4j
public class LoginController {

   @Autowired
   private UserService userService;

   @Autowired
   private GroupRepository groupRepository;

   @Autowired
   private RoleRepository roleRepository;

    private JWTProvider jwtProvider = new JWTProvider();

    private ApplicationContext ctx;

    @Autowired
    private EklBuildInfo eklBuildInfo;

    @PostMapping(path= "/api/auth/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginRequest loginRequest) throws UsernameOrPasswordInvalidException, UserNotFoundException {
//        EklUtilities.printEnvironment(ctx);
        try{
            //this.createUser("Han", "Qiao", LocalDate.of(1976,8,11),"timhanq@hotmail.com", "Familay Han" , "ADMIN");
            List<User> users = userService.findAll();
            log.info("Find users" + users.size());
            for(User user : users){
                String userTxt = user.toString();
                log.info("Find user {}", userTxt);
                log.info("Find user " + user.toString());
            }
            User user = userService.getUserByUsername(loginRequest.getUsername());            ;
            user.setToken(jwtProvider.createJWT(user));
            return ResponseEntity.ok(user);
        }catch(UserNotFoundException e){
            //TODO REMOVE TEST CODE
//            User user = new User();
//            user.setLastname(loginRequest.getUsername());
//            user.setFirstname(loginRequest.getUsername());
//            user = userService.save(user);
//            log.debug("return from save user %s", user.toString());
//            var saved = userService.getUserByUsername(user.getUsername());
//            log.debug("saved user %s",saved.getUsername());
            //TODO REMOVE END TEST CODE
            throw new UsernameOrPasswordInvalidException(e.getMessage());
        }
    }

//    private void createUser(String lastname, String firstname, LocalDate birthday, String email, String groupName, String roleName){
//        var user = new User();
//        user.setId(UUID.randomUUID());
//        user.setLastname(lastname);
//        user.setFirstname(firstname);
//        user.setBirthday(birthday);
//        user.setUsername(email);
//        user.setPasswd("12345");
//        var savedUser =  userService.save(user);
//
//        var group = new Group();
//        group.setName(groupName);
//        group.setId(UUID.randomUUID());
//        var savedGroup = groupRepository.save(group);
//
//
//        var role = new Role();
//        role.setId(UUID.randomUUID());
//        role.setGroup(savedGroup);
//        role.setName(roleName);
//        role.setUser(savedUser);
//        roleRepository.save(role);
//    }

    @Getter
    @Setter
    static class LoginRequest {

        @NotNull
        private String username;

        @NotNull
        private String password;
    }
}
