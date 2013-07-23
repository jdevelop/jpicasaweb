package com.jdevelop.jpicasa.commands;

/**
 * Defines the wrapper for any exception
 */
public class PicasaCommandException extends Exception {

    public PicasaCommandException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public PicasaCommandException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public PicasaCommandException(final String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PicasaCommandException(final Throwable cause) {
        super(cause);
    }

}
