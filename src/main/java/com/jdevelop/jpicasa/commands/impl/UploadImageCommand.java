package com.jdevelop.jpicasa.commands.impl;

import java.io.File;
import java.net.URL;
import java.util.Date;

import javax.activation.MimetypesFileTypeMap;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.PicasaCommandException;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.helper.PicasaUrlBuilder;

/**
 * Uploads a photo to the picasa
 */
public class UploadImageCommand extends AbstractContextAwareCommand implements
        PicasaCommandInterface<PhotoEntry> {

    private final File image;

    private final AlbumEntry album;

    /**
     * @param image
     */
    public UploadImageCommand(final AuthContext ctx, final File image,
            final AlbumEntry album) {
        super(ctx);
        this.image = image;
        this.album = album;
    }

    /**
     * @see com.jdevelop.jpicasa.commands.PicasaCommandInterface#execute()
     */
    public PhotoEntry execute() throws PicasaCommandException {
        final PhotoEntry photo = new PhotoEntry();
        photo.setTitle(new PlainTextConstruct(image.getName()));
        photo.setTimestamp(new Date(image.lastModified()));
        try {
            final MediaFileSource media = new MediaFileSource(image,
                    MimetypesFileTypeMap.getDefaultFileTypeMap()
                            .getContentType(image));
            photo.setMediaSource(media);
            final PicasawebService service = ctx.getService();
            return service.insert(new URL(PicasaUrlBuilder.getAlbumUrl(ctx,
                    album.getTitle().getPlainText())), photo);
        } catch (final Exception e) {
            throw new PicasaCommandException(e);
        }
    }
}
