package org.ekl.backend.ws.api.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ekl.backend.ws.api.user.UserService;
import org.ekl.backend.ws.config.EklSecurityConfiguration;
import org.ekl.backend.ws.model.User;
import org.ekl.backend.ws.token.JWTValidator;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Disabled
@WebMvcTest(controllers = {LoginController.class})
@Import({EklSecurityConfiguration.class})
@ExtendWith(SoftAssertionsExtension.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectSoftAssertions
    SoftAssertions softly;

    @MockBean
    UserService userService;

    private static final JWTValidator jwtValidator = new JWTValidator();

    @Disabled
    @Test
    void user_login_positive() throws Exception {
        //given
        var user = new User();
        user.setUsername("test-user");
        when(userService.getUserByUsername(any())).thenReturn(user);

        var loginRequest = new LoginController.LoginRequest();
        loginRequest.setPassword("Pwd");
        loginRequest.setUsername("test-user");

        //when
        var mvcResult = mockMvc.perform(post("/api/auth/login")
                .content(new ObjectMapper().writeValueAsString(loginRequest)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        //then
        MockHttpServletResponse mockResponse = mvcResult.getResponse();
        softly.assertThat(mockResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        softly.assertThat(mockResponse.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        var userReturned = new ObjectMapper().readValue(mockResponse.getContentAsString(), User.class);
        softly.assertThat(userReturned.getUsername()).isNotEmpty();
        softly.assertThat(userReturned.getToken()).isNotEmpty();
        softly.assertThat(jwtValidator.validateJWT(userReturned.getToken())).isTrue();
        softly.assertThat(userReturned.getPassword()).isNull();

        Throwable throwble = catchThrowable(() -> new NullPointerException("something"));
        var test = assertThat(throwble);
    }
}
