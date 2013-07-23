package com.jdevelop.jpicasa.comparator;

import com.google.gdata.data.photos.GphotoEntry;

/**
 * the imeplentation which matches on strict name match
 */
public class StrictGPhotoMatcher implements GPhotoMatcher {

    private final String albumName;

    /**
     * @param albumName
     */
    public StrictGPhotoMatcher(final String albumName) {
        this.albumName = albumName;
    }

    /**
     * @see com.jdevelop.jpicasa.comparator.GPhotoMatcher#match(com.google.gdata.data.photos.AlbumEntry)
     */
    @SuppressWarnings("unchecked")
    public boolean match(final GphotoEntry album) {
        return album != null
                && albumName.equals(album.getTitle().getPlainText());
    }

}
