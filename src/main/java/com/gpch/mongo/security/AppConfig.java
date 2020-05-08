package com.gpch.mongo.security;

import com.auth0.AuthenticationController;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.gpch.mongo.auth.controllers.LogoutController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.UnsupportedEncodingException;
/**
 *
 * @author Jaidon Jaekel
 *
 * This class sets up the the security configurations for the whole application
 *
 */
@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends WebSecurityConfigurerAdapter {
    /**
     * This is the auth0 domain
     */
    @Value(value = "${com.auth0.domain}")
    private String domain;

    /**
     * This is the client id
     */
    @Value(value = "${com.auth0.clientId}")
    private String clientId;

    /**
     * This is the client secret
     */
    @Value(value = "${com.auth0.clientSecret}")
    private String clientSecret;

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutController();
    }

    @Bean
    public AuthenticationController authenticationController() throws UnsupportedEncodingException {
        JwkProvider jwkProvider = new JwkProviderBuilder(domain).build();
        return AuthenticationController.newBuilder(domain, clientId, clientSecret)
                .withJwkProvider(jwkProvider)
                .build();
    }

    /**
     * @param http
     * @throws Exception
     *
     * configures the security for the whole application and permits certain URls to be visited with out login
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
            .authorizeRequests()
            .antMatchers("/callback", "/login", "/", "/*.png", "/css/**", "/js/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout().logoutSuccessHandler(logoutSuccessHandler()).permitAll();
    }

    public String getDomain() {
        return domain;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
