package org.example.githubauthservice.contreller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            System.out.println("OAuth2User attributes: " + oAuth2User.getAttributes());

        }
        return "hello";
    }
    @GetMapping("/home1")
    public String home1(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            System.out.println("OAuth2User attributes: " + oAuth2User.getAttributes());

        }
        return "hello";
    }


    @GetMapping("/login")
    public String login(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (oAuth2User != null) {
            System.out.println("Authenticated user: " + authentication.toString());
            return "not null";
        }
        return "null";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            SecurityContextHolder.clearContext();
        }
        request.getSession().invalidate();
        return "logout";
    }


    @GetMapping("/error")
    public String error(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("errorMessage", error != null ? error : "Unknown error occurred.");
        return "error";
    }
}