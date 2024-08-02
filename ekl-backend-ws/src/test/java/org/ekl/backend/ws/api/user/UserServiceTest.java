package org.ekl.backend.ws.api.user;

import org.ekl.backend.ws.api.user.repository.UserRepository;
import org.ekl.backend.ws.exception.UserNotFoundException;
import org.ekl.backend.ws.model.Role;
import org.ekl.backend.ws.model.User;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
class UserServiceTest {

    @InjectSoftAssertions
    SoftAssertions softly;

    @Autowired
    UserService underTest;

    @MockBean
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<String> userRepositoryUserIdCaptor;

    @Test
    public void get_user_by_id_positive() throws UserNotFoundException {
        //given
        var user = new User();
        user.setUsername("test-user");
        var role = new Role();
        role.setName("Admin");
        List<Role> roles = Arrays.asList(role);
        user.setRoles(roles);
        when(userRepository.findByUsername(any())).thenReturn(Collections.singletonList(user));

        //when
        var userReturned = underTest.getUserByUsername("test-user");

        //then
        softly.assertThat(userReturned).isNotNull();
        softly.assertThat(userReturned.getUsername()).isEqualTo("test-user");
        verify(userRepository, times(1)).findByUsername(userRepositoryUserIdCaptor.capture());
        softly.assertThat(userRepositoryUserIdCaptor.getValue()).isEqualTo("test-user");
    }

    @Test
    @Disabled
    public void get_user_by_id_throws_exception() {
        //given
        when(userRepository.findByUsername(any())).thenReturn(Collections.emptyList());

        //when
        UserNotFoundException exception = catchThrowableOfType(() -> underTest.getUserByUsername("some-user"), UserNotFoundException.class);

        //then
        softly.assertThat(exception.getMessage()).isEqualTo("Not found user with Id[%s]", "some-user");
        verify(userRepository,times(1)).findByUsername(userRepositoryUserIdCaptor.capture());
        softly.assertThat(userRepositoryUserIdCaptor.getValue()).isEqualTo("some-user");
    }
}
