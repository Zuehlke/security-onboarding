package com.zuehlke.zrs.security.controllers;

import com.zuehlke.zrs.security.auth.UserSignedInViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nesp on 21-Sep-16.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/login")
public class LoginController {

    private final Authentication authentication;

    @Autowired LoginController(Authentication authentication) {
        this.authentication = authentication;
    }

    @RequestMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody()
    UserSignedInViewModel index() {
       return new UserSignedInViewModel(authentication.getAuthorities());
    }

}
