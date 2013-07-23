package com.jdevelop.jpicasa.commands.helper;

import com.jdevelop.jpicasa.commands.AuthContext;

/**
 * Provides methods to get the URL for picasa
 */
public class PicasaUrlBuilder {

    public static String getUserURL(final AuthContext ctx) {
        return new StringBuilder(
                "http://picasaweb.google.com/data/feed/api/user/").append(
                ctx.getUsername()).toString();
    }

    public static String getAlbumUrl(final AuthContext ctx,
            final String albumName) {
        return new StringBuilder(
                "http://picasaweb.google.com/data/feed/api/user/").append(
                ctx.getUsername()).append("/album/").append(albumName).append(
                '/').toString();
    }
}
