package com.jdevelop.jpicasa;

import java.util.List;

import junit.framework.TestCase;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.util.AuthenticationException;
import com.jdevelop.jpicasa.commands.CommandFactoryInterface;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.impl.CommandFactoryImpl;

public class TestAlbumManagement extends TestCase {

    private static final String SAMPLE_ALBUM = "samplealbum";
    final CommandFactoryInterface cmdFactory;

    public TestAlbumManagement() throws AuthenticationException {
        cmdFactory = new CommandFactoryImpl(AuthHelper.getTestContext());
    }

    public void testAlbumManagement() throws Exception {
        final PicasaCommandInterface<AlbumEntry> createCommand = cmdFactory
                .createAlbumCmd(SAMPLE_ALBUM);
        final AlbumEntry album = createCommand.execute();
        assertNotNull(album);
        final PicasaCommandInterface<List<AlbumEntry>> albumLoaderCommand = cmdFactory
                .loadAlbum(SAMPLE_ALBUM);
        final List<AlbumEntry> foundAlbums = albumLoaderCommand.execute();
        assertNotNull(foundAlbums);
        assertEquals(1, foundAlbums.size());
        final AlbumEntry albumEntry = foundAlbums.get(0);
        assertEquals(album.getTitle().getPlainText(), albumEntry.getTitle()
                .getPlainText());
        System.out.println(albumEntry.getSelfLink().getHref());
        album.delete();
    }
}
