package SeamCarving.src; /**
 * SeamCarvingDemo.java
 *  Prebuilt code to display the effects of seam carving on an image
 * USE: 
 *   in the main function, assign to `filePath` the path
 *    of the image you want to resize
 *  OR
 *   pass it as a command line argument:
 *     `java SeamCarvingDemo my_file_path`
 *
 * This relies on there being a working implementation 
 *  of SeamCarving.java in the same folder
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SeamCarvingDemo {
    public static void main(String[] argv) throws IOException {
        // replace with your file path
        String filePath = "C:\\Users\\Aakash S\\Desktop\\demo.jpg";

        // alternatively, file path from command line
        if (argv.length > 0) filePath = argv[0];

        BufferedImage img = ImageIO.read(new File(filePath));

        // display image before resizing
        ImageDisplay frame = new ImageDisplay();
        frame.updateImage(img);
        frame.setTitle("Before resizing");

        int k = 100;
        BufferedImage resized = SeamCarvingDemo.seamCarveImage(img, k, frame);

        // display image after resizing
        frame.updateImage(resized);
        frame.setTitle("After resizing");

        File output = new File("demo-resized.jpg");
        ImageIO.write(resized, "JPG", output);
    }

    public static BufferedImage seamCarveImage(BufferedImage img, int numCol, ImageDisplay display) {
        Pixel[][] imgArr = ImageDisplay.buffToPixelArray(img);

        for (int i = 0; i < numCol; i++) {
            System.out.println("carving seam " + (i+1) + "...");
            display.setTitle("Removing seam #"+(i+1));

            int[][] disruption = Stencil.disruption(imgArr);
            // your implementation is called here
            int[] seam = SeamCarving.carve_seam(disruption);
            imgArr = removeSeam(imgArr, seam, display);
        }

        return ImageDisplay.pixelArrayToBuff(imgArr);
    }

    public static Pixel[][] removeSeam(Pixel[][] imgArr, int[] seam, ImageDisplay display) {
        Pixel[][] newImg = new Pixel[imgArr.length][imgArr[0].length-1];

        // add all except the removed column
        for (int i = 0; i < imgArr.length; i++) {
            int col = 0;
            for (int j = 0; j < imgArr[0].length; j++) {
                if (j != seam[i]) {
                    newImg[i][col++] = imgArr[i][j];
                } else {
                    imgArr[i][j] = new Pixel(255, 0, 0);
                }
            }
        }

        // show seam and shrunk image
        display.updateImage(ImageDisplay.pixelArrayToBuff(imgArr));

        return newImg;
    }
}