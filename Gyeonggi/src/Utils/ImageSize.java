package Utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageSize {
	public static ImageIcon set(ImageIcon img, JLabel label) {
		Image get = img.getImage();
		Image re = get.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
		return new ImageIcon(re);
	}
}
