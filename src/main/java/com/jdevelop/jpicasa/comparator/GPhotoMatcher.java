package com.jdevelop.jpicasa.comparator;

import com.google.gdata.data.photos.GphotoEntry;

/**
 * Defines methods to determine does album matches the given criteria
 */
public interface GPhotoMatcher {

    /**
     * @param album
     *            album entry to examine
     * @return true if the given album matches the criteria
     */
    @SuppressWarnings("unchecked")
    public boolean match(GphotoEntry album);

}
