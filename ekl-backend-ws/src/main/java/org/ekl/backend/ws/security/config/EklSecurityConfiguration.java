package org.ekl.backend.ws.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.ekl.backend.ws.security.oauth.EklAuthorizationRequestResolver;
import org.ekl.backend.ws.security.oauth.OAuth2SuccessHandler;
import org.ekl.backend.ws.token.JWTProvider;
import org.ekl.backend.ws.token.JWTValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class EklSecurityConfiguration {

    private final OAuth2SuccessHandler successHandler;
    private final EklAuthorizationRequestResolver authorizationRequestResolver;

    public EklSecurityConfiguration(OAuth2SuccessHandler successHandler,
                                    EklAuthorizationRequestResolver authorizationRequestResolver) {
        this.successHandler = successHandler;
        this.authorizationRequestResolver = authorizationRequestResolver;
    }

    @Bean
    public SecurityFilterChain securityConfigure(HttpSecurity http) throws Exception {
        var rst = http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/auth/login").permitAll();
                    request.requestMatchers("/api/**").authenticated();
                    request.anyRequest().permitAll();
                })
//                .formLogin(configurer -> {
//                    configurer.usernameParameter("user");
//                    configurer.passwordParameter("pwd");
//                    configurer.successForwardUrl("/api/user/123456789");
//                    configurer.loginPage("/login");
//                })
//                .httpBasic(Customizer.withDefaults())
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/oauth2/authorization/authcode")
//                        .defaultSuccessUrl("/api/user/profile", true)
//                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorization -> authorization
                                .authorizationRequestResolver(authorizationRequestResolver)
                        )
                        .successHandler(successHandler)
                )
                .oauth2Client(Customizer.withDefaults())
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), // Sendet 401
                                PathPatternRequestMatcher.withDefaults().matcher("/api/**") // Gilt nur für API
                        )
                )
                .addFilterBefore(createTokenAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
        return rst;
    }

    //TODO @Bean muss also works
    public JwtDecoder createJwtDecoder() {
        JwtDecoder decoder = (s) -> {
            JWTValidator jwtValidator = new JWTValidator();
            JWTProvider jwtProvider = new JWTProvider();
            Jws<Claims> jws = jwtValidator.readJWS(s);
            return jwtProvider.createSpringSecurityJwtWith(s, jws);
        };
        return decoder;
    }

    public BearerTokenAuthenticationFilter createTokenAuthenticationFilter() {
        JwtDecoder jwtDecoder = createJwtDecoder();
        AuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProvider);
//        DefaultBearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();
//        bearerTokenResolver.setBearerTokenHeaderName("Authorization");
//        BearerTokenAuthenticationFilter authenticationFilter =  new BearerTokenAuthenticationFilter(authenticationManager);
//        authenticationFilter.setBearerTokenResolver(bearerTokenResolver);
//        return authenticationFilter;
        return new BearerTokenAuthenticationFilter(authenticationManager);
    }

}

