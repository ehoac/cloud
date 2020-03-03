package com.eh.cloud.auth.api.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author caopeihe
 * @Type OauthController.java
 * @Desc
 * @date 2020/2/26 13:31
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AuthController {
    @RequestMapping("oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("oauth_approval");
        view.addObject("scopes",authorizationRequest.getScope());
        view.addObject("clientId", authorizationRequest.getClientId());
        return view;
    }

}
