package com.jdevelop.jpicasa.commands;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.util.AuthenticationException;

/**
 * Holds the information about the current user credentials
 */
public class AuthContext {

    private final String username;

    private final String password;

    private final PicasawebService service;

    /**
     * @param username
     * @param password
     * @throws AuthenticationException
     */
    public AuthContext(final String username, final String password)
            throws AuthenticationException {
        this.username = username;
        this.password = password;
        service = new PicasawebService("JPicasa");
        service.setUserCredentials(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @return the service
     */
    public PicasawebService getService() {
        return service;
    }

}
