package com.zuehlke.zrs.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nesp on 23-Sep-16.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<User> users = readUsersCredentials();

        for (User user : users) {
            auth
                    .inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder())
                    .withUser(user.getUsername()).password(user.getPassword()).roles(user.getRole());
        }
    }

    private List<User> readUsersCredentials() throws Exception {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("cryptedCredentials.txt").getFile()));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                String[] values = line.split(" ");
                User user = new User();
                user.setUsername(values[0]);
                user.setPassword(values[1]);
                user.setRole(values[2]);
                users.add(user);
                System.out.println(user.toString());
                line = br.readLine();
            }
        } catch (Exception exception)  {
            throw new Exception(exception);
        } finally {
            br.close();
        }
        return users;
    }
}

