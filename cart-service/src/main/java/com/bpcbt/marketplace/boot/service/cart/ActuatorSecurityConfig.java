package com.bpcbt.marketplace.boot.service.cart;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Order(1)
@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationClientsProperties applicationClientsProperties;

    public ActuatorSecurityConfig(ApplicationClientsProperties applicationClientsProperties) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.applicationClientsProperties = applicationClientsProperties;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/actuator/**")
                    .authorizeRequests().anyRequest().hasAuthority("SYSTEM");

        http
                .httpBasic()
                .and()
                .logout().disable()
                .headers().disable()
                .requestCache().disable()
                .anonymous().disable()
                .formLogin().disable()
                .headers().disable()
                .csrf().disable()
                .removeConfigurer(DefaultLoginPageConfigurer.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemory = auth.inMemoryAuthentication();
        for (ApplicationClientsProperties.ApplicationClient client : applicationClientsProperties.getClients()) {
            inMemory.withUser(client.getUsername())
                    .password(passwordEncoder.encode(client.getPassword()))
                    .authorities(client.getAuthorities());
        }
    }
}