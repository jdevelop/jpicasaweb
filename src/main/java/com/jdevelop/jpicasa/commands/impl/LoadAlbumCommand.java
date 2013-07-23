package com.jdevelop.jpicasa.commands.impl;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.PicasaCommandException;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.helper.PicasaUrlBuilder;
import com.jdevelop.jpicasa.comparator.GPhotoMatcher;

/**
 * 
 */
public class LoadAlbumCommand extends AbstractContextAwareCommand implements
        PicasaCommandInterface<List<AlbumEntry>> {

    private final GPhotoMatcher albumMatcher;

    /**
     * @param albumName
     */
    public LoadAlbumCommand(final AuthContext ctx,
            final GPhotoMatcher albumMatcher) {
        super(ctx);
        this.albumMatcher = albumMatcher;
    }

    /**
     * @see com.jdevelop.jpicasa.commands.PicasaCommandInterface#execute()
     */
    public List<AlbumEntry> execute() throws PicasaCommandException {
        List<AlbumEntry> results = null;
        try {
            final String albumFeedUrl = new StringBuilder(PicasaUrlBuilder
                    .getUserURL(ctx)).append("?kind=album").toString();
            final UserFeed userFeed = ctx.getService().getFeed(
                    new URL(albumFeedUrl), UserFeed.class);
            final List<GphotoEntry> entries = userFeed.getEntries();
            if (entries != null && !entries.isEmpty()) {
                results = new LinkedList<AlbumEntry>();
                for (final GphotoEntry<? extends GphotoEntry> entry : entries) {
                    if (albumMatcher.match(entry))
                        results.add(new AlbumEntry(entry));
                }
            }
        } catch (final Exception e) {
            throw new PicasaCommandException(e);
        }
        return results;
    }
}
