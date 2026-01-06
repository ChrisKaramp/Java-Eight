package gr.aueb.javaeight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

/**
 * Extracts resources from a .jar file, in order to be written to, not just read-only.
 */
public class JarDataFolderCopier {

    static Path pathOfcreatedOrExistingFolder;
    
    /**
     * Decides if application is ran from IDE (VSC in this case) or .jar file.
     * Creates data folder in proper path.
     * Extracts data files (this case .csv using parameter) from .jar file to data folder.
     * Notify user if files were extracted (.jar case) or created (IDE case).
     * Notify user in case of any failure.
     * @param dataFolderName absolute path to data folder
     * @throws IOException I/O error exception
     */
    public static void createDataFolderAndCopyCSVs(String dataFolderName)
            throws IOException {

        // find jar file location
        File location;
        try {
            location = new File(
                    JarDataFolderCopier.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String jarPathStr = location.getAbsolutePath();

        // define if run from VSC or jar file
        File baseDir;
        if (location.isFile() && jarPathStr.endsWith(".jar")) {
            baseDir = location.getParentFile();
        } else {
            baseDir = new File(System.getProperty("user.dir"));
        }

        // define data directory
        File dataDir = new File(baseDir, dataFolderName);

        // try to create data directory if does not exist
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            System.out.println("mkdirs result: " + created);
            if (!created) {
                JOptionPane.showMessageDialog
                    (null,
                    "Failed to create data folder: " + dataDir);
                throw new IOException("Failed to create data folder: " + dataFolderName);
            } else {
                pathOfcreatedOrExistingFolder = dataDir.toPath().toAbsolutePath();
                JOptionPane.showMessageDialog
                    (null,
                    "Data folder\n" + dataDir + "\ncreated.");
            }
        } else {
            pathOfcreatedOrExistingFolder = dataDir.toPath().toAbsolutePath();
            System.out.println("Data folder\n" + dataDir + "\nalready exists.");
            JOptionPane.showMessageDialog
                (null,
                "Data folder\n" + dataDir + "\nalready exists.");
        }

        // copy CSVs - NOT overwrite
        if (location.isFile() && jarPathStr.endsWith(".jar")) {
            try (JarFile jar = new JarFile(location)) {
                Enumeration<JarEntry> entries = jar.entries();

                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.isDirectory()
                        && entry.getName().startsWith(dataFolderName + "/")
                        && entry.getName().endsWith(".csv")) {

                        try (InputStream is = jar.getInputStream(entry)) {
                            File outFile = new File(
                                    dataDir,
                                    entry.getName().substring((dataFolderName + "/").length())
                            );

                            if (outFile.exists()) {
                                continue;
                            }

                            try (OutputStream os = new FileOutputStream(outFile)) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = is.read(buffer)) != -1) {
                                    os.write(buffer, 0, bytesRead);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Not executing using .jar file, no CSV copying.");
            JOptionPane.showMessageDialog
                (null,
                "Not executing using .jar file, no CSV copying.");
        }
    }
}
