package com.example.order_management.controllers;

import com.example.order_management.entities.Authentication;
import com.example.order_management.entities.Response;
import com.example.order_management.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @PostMapping("/login/{username}/{password}")
//    public Response login(@PathVariable String username, @PathVariable String password) {
//        Response response = new Response();
//
//        if(authenticationService.isLoggedIn()){
//            response.setStatus(false);
//            response.setMessage("There is a user logged in");
//            return response;
//        }
//
//        if (authenticationService.login(username, password)) {
//            response.setStatus(true);
//            response.setMessage("Login successful");
//        } else {
//            response.setStatus(false);
//            response.setMessage("Invalid username or password");
//        }
//        return response;
//    }

    @PostMapping("/login")
    public Response login(@RequestBody Authentication authentication) {
        Response response = new Response();

        if(authenticationService.isLoggedIn()){
            response.setStatus(false);
            response.setMessage("There is a user logged in");
            return response;
        }

        if (authenticationService.login(authentication.getUsername(), authentication.getPassword())) {
            response.setStatus(true);
            response.setMessage("Login successful");
        } else {
            response.setStatus(false);
            response.setMessage("Invalid username or password");
        }
        return response;
    }



    @PostMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "Logout successful";
    }

    @GetMapping("/status")
    public String status() {
        return authenticationService.isLoggedIn() ? "User is logged in" : "User is not logged in";
    }
}
