package com.smarthouse.gateway.controller;

import com.smarthouse.commonutil.entities.User;
import com.smarthouse.commonutil.exceptions.ResourceNotFound;
import com.smarthouse.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import static com.smarthouse.commonutil.exceptions.ResourceNotFound.getNoResourceMessage;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestHeader("id") long id) {
        try {
            ResponseEntity<User> userServiceResponse = userService.getById(id);
            switch (userServiceResponse.getStatusCode()) {
                case OK:
                    return userServiceResponse;
                case NO_CONTENT:
                    throw new ResourceNotFound(getNoResourceMessage("User", id));
                default:
                    return userServiceResponse;
            }
        } catch (RestClientException restClientException) {
            throw new RuntimeException();
        }
    }

}
