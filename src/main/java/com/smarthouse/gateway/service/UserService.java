package com.smarthouse.gateway.service;

import com.smarthouse.commonutil.entities.User;
import com.smarthouse.gateway.client.UserDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
public class UserService {

    private UserDiscoveryClient userDiscoveryClient;

    @Autowired
    public UserService(final UserDiscoveryClient userDiscoveryClient) {
        this.userDiscoveryClient = userDiscoveryClient;
    }

    public ResponseEntity<User> getById(final long id) throws RestClientException {
        return userDiscoveryClient.getUser(id);
    }
}
