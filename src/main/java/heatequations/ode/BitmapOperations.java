package heatequations.ode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapOperations {

    public int[][] readBitmap(String bitmapPath) throws IOException {
        BufferedImage image = ImageIO.read(new FileInputStream(bitmapPath));
        System.out.println(image.getType());
        int[][] array2D = new int[image.getWidth()][image.getHeight()];
        for (int w = 0; w < image.getWidth(); w++) {
            for (int h = 0; h < image.getHeight(); h++) {
                array2D[w][h] = image.getRGB(w, h);
            }
        }
        return array2D;

    }

    public void saveBitmap(int[][] image, String bitmapPath) throws IOException {
        BufferedImage redImage = new BufferedImage(image.length, image.length, BufferedImage.TYPE_BYTE_INDEXED);
        for (int w = 0; w < image.length; w++) {
            for (int h = 0; h < image[0].length; h++) {
                redImage.setRGB(w, h, image[w][h]);
            }
        }
        ImageIO.write(redImage, "bmp", new FileOutputStream(bitmapPath));
    }
}
