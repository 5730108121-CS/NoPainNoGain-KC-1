package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Resource {
	
	public static List<BufferedImage> characterSprite;
	
	public static AudioClip walkSound;
	static{
		try {
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			characterSprite = new ArrayList<>();
			characterSprite.add(ImageIO.read(loader.getResource("Actor1.png")));
			characterSprite.add(ImageIO.read(loader.getResource("Actor2.png")));
			characterSprite.add(ImageIO.read(loader.getResource("Actor3.png")));
			characterSprite.add(ImageIO.read(loader.getResource("Actor4.png")));
			walkSound = Applet.newAudioClip((loader.getResource("Buzzer1.wavd")).toURI().toURL());
		} catch (Exception e) {
			walkSound = null;
		}
	}
}
