package com.jdevelop.jpicasa.commands.impl;

import com.jdevelop.jpicasa.commands.AuthContext;

/**
 * 
 */
public class AbstractContextAwareCommand {

    protected final AuthContext ctx;

    /**
     * 
     */
    public AbstractContextAwareCommand(final AuthContext ctx) {
        this.ctx = ctx;
    }

}