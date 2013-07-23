package com.jdevelop.jpicasa;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.util.ServiceException;
import com.jdevelop.jpicasa.commands.AuthContext;
import com.jdevelop.jpicasa.commands.CommandFactoryInterface;
import com.jdevelop.jpicasa.commands.PicasaCommandException;
import com.jdevelop.jpicasa.commands.impl.CommandFactoryImpl;

public class Main {

    public static final ResourceBundle messages = ResourceBundle
            .getBundle("messages");

    static final LongOpt[] opts = {
            new LongOpt("album", LongOpt.REQUIRED_ARGUMENT, null, 'a'),
            new LongOpt("username", LongOpt.REQUIRED_ARGUMENT, null, 'u'),
            new LongOpt("password", LongOpt.REQUIRED_ARGUMENT, null, 'p') };

    public static void main(final String[] args) throws Exception {
        final Getopt getOpt = new Getopt("picasa-cli", args, "hu:p:a:", opts);
        getOpt.setOpterr(false);
        String username = null;
        String password = null;
        String album = null;
        for (int option = getOpt.getopt(); option != -1; option = getOpt
                .getopt()) {
            switch (option) {
            case 'a':
                album = getOpt.getOptarg();
                break;
            case 'u':
                username = getOpt.getOptarg();
                break;
            case 'p':
                password = getOpt.getOptarg();
                break;
            case 'h':
                printHelp();
                System.exit(0);
                break;
            }
        }
        final InputStreamReader inputStreamReader = new InputStreamReader(
                System.in, Charset.defaultCharset());
        final BufferedReader reader = new BufferedReader(inputStreamReader);
        username = readPropertyIfMissing("missing_username", username, reader);
        password = readPropertyIfMissing("missing_password", password, reader);
        album = readPropertyIfMissing("missing_album", album, reader);
        final AuthContext ctx = new AuthContext(username, password);
        final CommandFactoryInterface cmdFactory = new CommandFactoryImpl(ctx);
        final List<AlbumEntry> albumEntries = cmdFactory.loadAlbum(album)
                .execute();
        AlbumEntry albumEntry = null;
        if (albumEntries != null && !albumEntries.isEmpty())
            albumEntry = checkAlbums(albumEntries, reader);
        if (albumEntry == null)
            albumEntry = cmdFactory.createAlbumCmd(album).execute();
        for (int i = getOpt.getOptind(); i < args.length; i++) {
            final File argFile = new File(args[i]);
            if (argFile.isDirectory()) {
                System.out.println(MessageFormat.format(messages
                        .getString("processing_directory"),
                        new Object[] { argFile.getAbsolutePath() }));
                for (final File imageFile : argFile.listFiles()) {
                    if (imageFile.isFile())
                        uploadImage(cmdFactory, albumEntry, imageFile);
                }
            } else
                uploadImage(cmdFactory, albumEntry, argFile);
        }
        System.out.println(messages.getString("done"));
    }

    /**
     * Prints the usage message
     */
    private static void printHelp() {
        System.out.println("Available options\n--album \t\t<album name>\n"
                + "--username \t\t<username>\n" + "--password \t\t<password>");
    }

    /**
     * @param albumEntries
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    private static AlbumEntry checkAlbums(final List<AlbumEntry> albumEntries,
            final BufferedReader reader) throws ServiceException, IOException {
        System.out.println(messages.getString("choose_album_entries"));
        int currentAlbum = 0;
        for (final AlbumEntry entry : albumEntries) {
            System.out.format(messages.getString("choose_album_line"),
                    currentAlbum++, entry.getTitle().getPlainText(), new Date(
                            entry.getPublished().getValue()));
        }
        while (true) {
            System.out.format(messages.getString("choose_album_prompt"), 0,
                    albumEntries.size());
            final String input = reader.readLine();
            if (input != null && !"".equals(input.trim())) {
                try {
                    final int idx = Integer.parseInt(input);
                    if (idx >= 0 && idx < albumEntries.size())
                        return albumEntries.get(idx);
                } catch (final NumberFormatException nfe) {
                    System.err.format(messages
                            .getString("choose_album_wrong_number"), 0,
                            albumEntries.size() - 1);
                }
                continue;
            } else
                return null;
        }
    }

    /**
     * @param string
     * @param username
     * @throws IOException
     */
    private static String readPropertyIfMissing(final String key,
            String property, final BufferedReader reader) throws IOException {
        while (property == null || "".equals(property.trim())) {
            System.out.println(messages.getString(key));
            property = reader.readLine();
        }
        System.out.println("Using " + property);
        return property;
    }

    /**
     * @param cmdFactory
     * @param albumEntry
     * @param argFile
     * @throws PicasaCommandException
     */
    private static void uploadImage(final CommandFactoryInterface cmdFactory,
            final AlbumEntry albumEntry, final File argFile)
            throws PicasaCommandException {
        System.out.println(MessageFormat.format(messages
                .getString("uploading_image"), new Object[] { argFile
                .getAbsolutePath() }));
        cmdFactory.uploadPhotoCmd(albumEntry, argFile).execute();
    }

}
