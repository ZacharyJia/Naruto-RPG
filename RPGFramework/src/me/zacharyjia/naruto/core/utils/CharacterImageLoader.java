package me.zacharyjia.naruto.core.utils;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import me.zacharyjia.naruto.core.Exception.ResourcesNotFoundException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by jia19 on 2016/3/10.
 */
public class CharacterImageLoader {

    public static Image[][] getImages(String imageFile) {

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(ResourcesLoader.getInputStream(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int width = bufferedImage.getWidth() / 4;
        int height = bufferedImage.getHeight() / 4;
        Image[][] images = new Image[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                BufferedImage image = bufferedImage.getSubimage(width * j, height * i , width, height);
                WritableImage wImg = new WritableImage(width, height);
                SwingFXUtils.toFXImage(image, wImg);
                images[i][j] = wImg;
            }
        }

        return images;
    }
}
