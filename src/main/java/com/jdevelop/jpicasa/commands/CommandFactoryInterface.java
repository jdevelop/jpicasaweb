package com.jdevelop.jpicasa.commands;

import java.io.File;
import java.util.List;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;

/**
 * Declares methods to be used for getting the appropriate command
 * implementation
 */
public interface CommandFactoryInterface {

    /**
     * @param albumName
     * @return instance of the command capable of creating an album on picasa
     */
    public PicasaCommandInterface<AlbumEntry> createAlbumCmd(String albumName);

    /**
     * @param albumName
     * @return instance of the command capable of removal of the album
     */
    public PicasaCommandInterface<AlbumEntry> removeAlbumCmd(String albumName);

    /**
     * @param image
     * @return instance of the command capable of the uploading the image to the
     *         picasa
     */
    public PicasaCommandInterface<PhotoEntry> uploadPhotoCmd(AlbumEntry album,
            File image);

    /**
     * @param albumName
     * @return an entry for given album
     */
    public PicasaCommandInterface<List<AlbumEntry>> loadAlbum(String albumName);

}
