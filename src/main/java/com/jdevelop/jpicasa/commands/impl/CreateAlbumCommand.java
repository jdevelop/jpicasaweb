package com.jdevelop.jpicasa.commands.impl;

import java.net.URL;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.photos.AlbumEntry;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.PicasaCommandException;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.helper.PicasaUrlBuilder;

/**
 * 
 */
public class CreateAlbumCommand extends AbstractContextAwareCommand implements
        PicasaCommandInterface<AlbumEntry> {

    private final String albumName;

    /**
     * @param ctx
     * @param albumName
     */
    public CreateAlbumCommand(final AuthContext ctx, final String albumName) {
        super(ctx);
        this.albumName = albumName;
    }

    /**
     * @throws PicasaCommandException
     * @see com.jdevelop.jpicasa.commands.PicasaCommandInterface#execute()
     */
    public AlbumEntry execute() throws PicasaCommandException {
        try {
            final PicasawebService service = ctx.getService();
            final URL albumUrl = new URL(PicasaUrlBuilder.getUserURL(ctx));
            final AlbumEntry newAlbum = new AlbumEntry();
            newAlbum.setTitle(new PlainTextConstruct(albumName));
            final AlbumEntry insertedAlbum = service.insert(albumUrl, newAlbum);
            return insertedAlbum;
        } catch (final Exception e) {
            throw new PicasaCommandException(e);
        }
    }
}
