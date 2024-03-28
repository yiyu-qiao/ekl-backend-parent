package org.ekl.backend.ws.api.authentication;

import org.ekl.backend.ws.model.User;
import org.ekl.backend.ws.token.JWTValidator;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SoftAssertionsExtension.class)
@ActiveProfiles({"h2-file"})
public class LoginControllerIntegrationTest {

    @Autowired
    WebTestClient webClient;

    @InjectSoftAssertions
    SoftAssertions softly;

    private static final JWTValidator jwtValidator = new JWTValidator();
    @Disabled
    @Test
    public void user_login_positive(){
        //given
        var loginRequest = new LoginController.LoginRequest();
        loginRequest.setPassword("Pwd");
        loginRequest.setUsername("test-user");

        //when

        //then
        EntityExchangeResult<User> responseBody =  webClient.post().uri("/api/auth/login").contentType(MediaType.APPLICATION_JSON).bodyValue(loginRequest)
                .exchange()
                .expectAll(
                        spec -> spec.expectStatus().is2xxSuccessful(),
                        spec -> spec.expectHeader().contentType(MediaType.APPLICATION_JSON))
                .expectBody(User.class)
                .returnResult();
        softly.assertThat(responseBody.getResponseBody()).isExactlyInstanceOf(User.class);
        var userReturned = responseBody.getResponseBody();
        softly.assertThat(userReturned.getToken()).isNotEmpty();
        softly.assertThat(jwtValidator.validateJWT(userReturned.getToken())).isTrue();
        softly.assertThat(userReturned.getUsername()).isNotEmpty();
        softly.assertThat(userReturned.getPassword()).isNull();
    }
}
