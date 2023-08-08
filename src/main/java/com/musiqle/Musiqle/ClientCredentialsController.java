package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ClientCredentialsController {
    @Autowired
    private ClientCredentialsService service;

    /// API Call to Spotify API to get access token
    @PostMapping("/access_token")
    public AccessToken create(@RequestParam AccessToken accessToken) {
        return service.create(accessToken);
    }
}
