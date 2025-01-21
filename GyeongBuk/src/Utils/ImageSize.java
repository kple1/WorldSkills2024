package Utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageSize {

	public static ImageIcon changeColor(int color) {
	    BufferedImage image = null;
	    try {
	        image = ImageIO.read(new File("datafiles/좌석.png"));

	        var resizedImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(image, 0, 0, 50, 50, null);
	        g2d.dispose();

	        int newColor = color; //0x : 16진수
	        					  //FF : 투명도 100%
	        				      //c0 c0 c0 : 각각 R,G,B
	        int getBackgroundColor = resizedImage.getRGB(0, 0);
	        
	        for (int x = 0; x < resizedImage.getHeight(); x++) {
	            for (int y = 0; y < resizedImage.getWidth(); y++) {
	                int currentColor = resizedImage.getRGB(y, x);
	                if (currentColor != getBackgroundColor) {
	                    resizedImage.setRGB(y, x, newColor);
	                }
	            }
	        }

	        return new ImageIcon(resizedImage);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public static ImageIcon set(ImageIcon img) {
		var get = img.getImage();
		var r = get.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		return new ImageIcon(r);
	}
	
	public static ImageIcon setSize(ImageIcon img, JLabel label) {
		var get = img.getImage();
		var r = get.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
		return new ImageIcon(r);
	}
}