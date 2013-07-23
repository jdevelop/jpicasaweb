package com.jdevelop.jpicasa.commands.impl;

import java.io.File;
import java.util.List;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.CommandFactoryInterface;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.comparator.StrictGPhotoMatcher;

/**
 * Implements required methods
 */
public class CommandFactoryImpl implements CommandFactoryInterface {

    private final AuthContext ctx;

    /**
     * @param ctx
     */
    public CommandFactoryImpl(final AuthContext ctx) {
        this.ctx = ctx;
    }

    /**
     * @see com.jdevelop.jpicasa.commands.CommandFactoryInterface#createalbumCommand(java.lang.String)
     */
    public PicasaCommandInterface<AlbumEntry> createAlbumCmd(
            final String albumName) {
        return new CreateAlbumCommand(ctx, albumName);
    }

    /**
     * @see com.jdevelop.jpicasa.commands.CommandFactoryInterface#removeAlnum(java.lang.String)
     */
    public PicasaCommandInterface<AlbumEntry> removeAlbumCmd(
            final String albumName) {
        return new RemoveAlbumCommand(ctx, albumName);
    }

    public PicasaCommandInterface<PhotoEntry> uploadPhotoCmd(
            final AlbumEntry album, final File image) {
        return new UploadImageCommand(ctx, image, album);
    }

    /**
     * @see com.jdevelop.jpicasa.commands.CommandFactoryInterface#loadAlbum(java.lang.String)
     */
    public PicasaCommandInterface<List<AlbumEntry>> loadAlbum(
            final String albumName) {
        return new LoadAlbumCommand(ctx, new StrictGPhotoMatcher(albumName));
    }

}
