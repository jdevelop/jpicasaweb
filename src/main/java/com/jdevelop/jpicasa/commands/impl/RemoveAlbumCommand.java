package com.jdevelop.jpicasa.commands.impl;

import java.net.URL;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.PicasaCommandException;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.helper.PicasaUrlBuilder;

/**
 * 
 */
public class RemoveAlbumCommand extends AbstractContextAwareCommand implements
        PicasaCommandInterface<AlbumEntry> {

    private final String albumName;

    public RemoveAlbumCommand(final AuthContext ctx, final String albumName) {
        super(ctx);
        this.albumName = albumName;
    }

    /**
     * @see com.jdevelop.jpicasa.commands.PicasaCommandInterface#execute()
     */
    public AlbumEntry execute() throws PicasaCommandException {
        try {
            final PicasawebService service = ctx.getService();
            final AlbumEntry entry = service.getEntry(new URL(PicasaUrlBuilder
                    .getAlbumUrl(ctx, albumName)), AlbumEntry.class);
            entry.delete();
            return null;
        } catch (final Exception e) {
            throw new PicasaCommandException(e);
        }
    }

}
