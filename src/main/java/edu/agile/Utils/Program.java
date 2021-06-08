package edu.agile.Utils;

import edu.agile.model.Game;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

public class Program {
    public static void run(Game game) {
        String gamePath = game.getGame().getAbsolutePath();
        String fileType = FilenameUtils.getExtension(gamePath);

        try {
            if ("exe".equals(fileType)) {
                Runtime.getRuntime().exec(gamePath);
            } else if ("jar".equals(fileType)) {
                Runtime.getRuntime().exec("javaw -jar " + gamePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
