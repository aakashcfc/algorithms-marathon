package SeamCarving.src;

import javax.swing.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class ImageDisplay extends JFrame {
    JLabel label;

    public ImageDisplay() {
        setVisible(false);

        label = new JLabel();
        add(label);
        setTitle("Image preview");
        setSize(100, 100);
    }

    // display the image
    public void updateImage(BufferedImage img) {
        label.setIcon(new ImageIcon(img));
        setSize(img.getWidth(), img.getHeight());
        setVisible(true);
    }

    /* static methods */
    public static Pixel[][] buffToPixelArray(BufferedImage img) {
        Pixel[][] imgArr = new Pixel[img.getHeight()][img.getWidth()];
        for (int i = 0; i < imgArr.length; i++) {
            for (int j = 0; j < imgArr[0].length; j++) {
                int rgb24 = img.getRGB(j, i),
                    r = (rgb24 >> 16) & 0xFF,
                    g = (rgb24 >> 8) & 0xFF,
                    b = (rgb24) & 0xFF;
                imgArr[i][j] = new Pixel(r, g, b);
            }
        }

        return imgArr;
    }

    public static BufferedImage pixelArrayToBuff(Pixel[][] imgArr) {
        int h = imgArr.length,
            w = imgArr[0].length;
        int[] flat = new int[w*h*3];

        // flatten
        int i = 0;
        for (Pixel[] col : imgArr) {
            for (Pixel pix : col) {
                flat[i++] = pix.red;
                flat[i++] = pix.green;
                flat[i++] = pix.blue;
            }
        }

        // create empty image
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) img.getRaster();
        // write to image
        raster.setPixels(0, 0, w, h, flat);

        return img;
    }
}
