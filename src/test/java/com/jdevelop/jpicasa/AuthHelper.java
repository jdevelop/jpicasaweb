package com.jdevelop.jpicasa;

import com.google.gdata.util.AuthenticationException;
import com.jdevelop.jpicasa.commands.AuthContext;

/**
 * 
 */
public class AuthHelper {

    public static AuthContext getTestContext() throws AuthenticationException {
        return new AuthContext("", "");
    }

}
