package edu.agile.service;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;

/**
 * Singleton ImageService that handles handles CRUD in local MongoDB
 */
public class ImageService {
    private static ImageService instance;
    private final String COLLECTION_NAME = "game-collection";
    private final String HOME_DIRECTORY = System.getProperty("user.home");
    private final String IMAGE_FOLDER;

    /**
     * Constructor.
     * If no root folder for images exists, one is created in the user's home directory:
     * "/username/collectionName" (COLLECTION_NAME is a class variable)
     * <p>
     * ImageService uses the Singleton pattern, so the constructor is private.
     * To create an instance, use ImageService.getInstance() instead.
     */
    private ImageService() {
        Path imageFolder = Path.of(HOME_DIRECTORY, COLLECTION_NAME);
        try {
            Files.createDirectories(imageFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IMAGE_FOLDER = imageFolder.toString();
    }

    /**
     * Getter for Singleton instance of ImageService. Creates the instance the first time the method is called.
     *
     * @return always the same ImageService instance.
     */
    public static ImageService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ImageService();
        }
        return instance;
    }

    /**
     * Saves image file in user's home directory: "/%USER%/collectionName", where COLLECTION_NAME is a class variable
     * Image will be saved with the name: "gameName_imageType.fileExtension", where FILE_EXTENSION is same as @param file
     * <p>
     * Note: In future versions, imageType could be an Enum instead of String.
     *
     * @param file      file that points to the current image location
     * @param gameName  which game the image is related to
     * @param imageType type of image, like "banner", "logo"
     * @return fileName
     */
    public String saveImageFile(File file, String gameName, String imageType) {
        String fileType = FilenameUtils.getExtension(file.getAbsolutePath());
        String fileName = getFileName(gameName, imageType, fileType);
        Path location = file.toPath();
        Path destination = getDestination(fileName);
        try {
            Files.copy(location, destination, COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination.toString();
    }

    /**
     * Concatenates the parts of a filename
     *
     * @param gameName  name of the game
     * @param imageType type of image, such as "banner" or "logo"
     * @param fileType  filetype, such as "png" or "jpg"
     * @return filename with extension, for example "minesweeper_logo.jpg"
     */
    private String getFileName(String gameName, String imageType, String fileType) {
        return gameName +
                "_" +
                imageType +
                "." +
                fileType;
    }

    /**
     * Get destination file path for a file name. At the moment, all image files are stored in the same folder.
     *
     * @param fileName name of the image file
     * @return Path to the file
     */
    private Path getDestination(String fileName) {
        return Path.of(HOME_DIRECTORY, COLLECTION_NAME, fileName);
    }
}