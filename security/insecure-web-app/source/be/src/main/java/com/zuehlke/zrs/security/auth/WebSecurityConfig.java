package com.zuehlke.zrs.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
                    .withUser(user.getUsername()).password(user.getPassword()).roles(user.getRole());
        }
    }

    private List<User> readUsersCredentials() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("credentials.txt").getFile()));
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
        } finally {
            br.close();
        }
        return users;
    }
}

