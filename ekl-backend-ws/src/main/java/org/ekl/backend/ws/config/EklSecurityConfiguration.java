package org.ekl.backend.ws.config;

import org.ekl.backend.ws.token.JWTProvider;
import org.ekl.backend.ws.token.JWTValidator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class EklSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityConfigure(HttpSecurity http) throws Exception {
        var rst = http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/auth/login").permitAll();
//                    request.requestMatchers("/api/**").authenticated();
                    request.anyRequest().permitAll();
                })
//                .formLogin(configurer -> {
//                    configurer.usernameParameter("user");
//                    configurer.passwordParameter("pwd");
////                    configurer.successForwardUrl("/api/user/123456789");
//                    configurer.loginPage("/login");
//                })
//                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(createTokenAuthenticationFilter(), AnonymousAuthenticationFilter.class)
                .csrf().disable()
                .build();
        return rst;
    }

    //TODO @Bean muss also works
    public JwtDecoder createJwtDecoder(){
        JwtDecoder decoder = (s) -> {
            JWTValidator jwtValidator = new JWTValidator();
            JWTProvider jwtProvider = new JWTProvider();
            Jws<Claims> jws = jwtValidator.readJWS(s);
            return jwtProvider.createSpringSecurityJwtWith(s,jws);
        };
        return decoder;
    }

    public BearerTokenAuthenticationFilter createTokenAuthenticationFilter(){
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
