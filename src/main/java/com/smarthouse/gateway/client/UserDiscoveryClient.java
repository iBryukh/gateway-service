package com.smarthouse.gateway.client;

import com.smarthouse.commonutil.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class UserDiscoveryClient {

    private static final String USER_SERVICE_ID = "user-service";
    private static final String GET_USER_REQUEST_MAPPING = "user";
    private RestTemplate restTemplate;

    @Autowired
    public UserDiscoveryClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<User> getUser(final long userId) throws RestClientException {
        String path = String.format("http://%s/%s/{userId}", USER_SERVICE_ID, GET_USER_REQUEST_MAPPING);
        return restTemplate.exchange(
                path,
                HttpMethod.GET,
                null, User.class, userId
        );
    }

}
