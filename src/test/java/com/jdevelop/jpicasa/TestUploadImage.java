package com.jdevelop.jpicasa;

import java.io.File;

import junit.framework.TestCase;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.PhotoEntry;
import com.jdevelop.jpicasa.commands.CommandFactoryInterface;
import com.jdevelop.jpicasa.commands.PicasaCommandInterface;
import com.jdevelop.jpicasa.commands.impl.CommandFactoryImpl;

/**
 * 
 */
public class TestUploadImage extends TestCase {

    public void testImageUpload() throws Exception {
        final CommandFactoryInterface cmdFactory = new CommandFactoryImpl(
                AuthHelper.getTestContext());
        final PicasaCommandInterface<AlbumEntry> albumCmd = cmdFactory
                .createAlbumCmd("test");
        final AlbumEntry album = albumCmd.execute();
        assertNotNull(album);
        final PicasaCommandInterface<PhotoEntry> imageCmd = cmdFactory
                .uploadPhotoCmd(album, new File("img_4205.jpg"));
        final PhotoEntry photo = imageCmd.execute();
        assertNotNull(photo);
        System.out.println(photo.getSelf().getEditLink().getHref());
    }
}
