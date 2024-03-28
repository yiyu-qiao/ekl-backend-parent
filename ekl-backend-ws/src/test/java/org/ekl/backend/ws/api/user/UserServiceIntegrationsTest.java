package org.ekl.backend.ws.api.user;

import org.ekl.backend.ws.api.user.repository.UserRepository;
import org.ekl.backend.ws.model.User;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@ActiveProfiles("h2-file")
class UserServiceIntegrationsTest {

    @Autowired
    private UserService underTest;

    @Autowired
    private UserRepository userRepository;

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    @Disabled
    void check_user_existence_positive(){
        //given
        String testUserName = "test_user1";
        String passwd = "1234";
        var listUserFound =userRepository.findByUsername(testUserName);
        if(!listUserFound.isEmpty()){
            for(User currentUser: listUserFound){
                userRepository.deleteById(currentUser.getId());
            }
        }
        var testUser = new User();
        testUser.setUsername(testUserName);
        testUser.setPassword(passwd);
        var savedUser=userRepository.save(testUser);
        var userOptional=userRepository.findById(savedUser.getId());
        softly.assertThat(userOptional).isNotEmpty();

        //when
        boolean userExist= underTest.checkUserExistence(testUserName);

        //then
        softly.assertThat(userExist).isTrue();
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    @Disabled
    void get_user_by_username_positive(){

    }
}
