package com.gpch.mongo.auth.controllers;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.gpch.mongo.security.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("unused")
/**
 *
 * @author Jaidon Jaekel
 *
 * This class handles the callback after the user has logged in.
 *
 */
@Controller
public class CallbackController {

    @Autowired
    private AuthenticationController controller;
    private final String redirectOnFail;
    private final String redirectOnSuccess;

    /**
     *
     * @author Jaidon Jaekel
     *
     * CallbackController() is the constructor and sets the call back endpoints
     *
     */
    public void CallbackController() {
        this.redirectOnFail = "/login";
        this.redirectOnSuccess = "/events-ui2";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        handle(req, res);
    }

    /**
     *
     * @param request
     * @param response
     *
     * handle() trys to log user in and retrieve token, if it fails, redirect to a redirectOnFailure html
     */
    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Tokens tokens = controller.handle(request, response);
            TokenAuthentication tokenAuth = new TokenAuthentication(JWT.decode(tokens.getIdToken()));
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            response.sendRedirect(redirectOnSuccess);
            System.out.println(tokenAuth.getClaims().get("sub").asString());
        } catch (AuthenticationException | IdentityVerificationException e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
            response.sendRedirect(redirectOnFail);
        }
    }

}
