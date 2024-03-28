package org.ekl.backend.ws.api.monitoring;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.ekl.backend.ws.api.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EklBackendWSMonitoring {

    @Bean
    public MeterBinder userSize(UserService userService){
        return (registry) -> Gauge.builder("ekl_backend_ws_user_size", ()->userService.findAll().size()).register(registry);
    }
}
