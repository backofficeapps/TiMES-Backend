package com.gpch.mongo.auth.controllers;

import com.auth0.AuthenticationController;
import com.gpch.mongo.security.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Jaidon Jaekel
 *
 * This class handles login requests and performs the login
 *
 */
@SuppressWarnings("unused")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationController controller;

    @Autowired
    private AppConfig appConfig;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param request
     * @param response
     * @return the redirect request
     *
     * login() handles the login requests and redirects the the callback page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String login(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Performing login");
        String redirectUri = request.getScheme() + "://" + request.getServerName();
        if ((request.getScheme().equals("http") && request.getServerPort() != 80) ||
                (request.getScheme().equals("https") && request.getServerPort() != 443)) {
            redirectUri += ":" + request.getServerPort();
        }
        redirectUri += "/callback";
        String authorizeUrl = controller.buildAuthorizeUrl(request, response, redirectUri)
                .withScope("openid profile email")
                .build();

        return "redirect:" + authorizeUrl;
    }

}
