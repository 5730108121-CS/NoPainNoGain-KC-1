package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Resource {
	
	public static List<BufferedImage> characterSprite;
	public static List<BufferedImage> faceSprite;
	public static BufferedImage background;
	
	public static BufferedImage simpleBlock;
	public static BufferedImage cannon;
	public static BufferedImage cannonBlock;
	
	public static BufferedImage[] numPic=new BufferedImage[11];
	
	public static AudioClip walkSound;
	static{
		try {
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			characterSprite = new ArrayList<>();
			faceSprite = new ArrayList<>();
			for(int i = 1;i<=5;i++){
				characterSprite.add(ImageIO.read(loader.getResource("Actor"+ i +".png")));
				faceSprite.add(ImageIO.read(loader.getResource("FActor"+ i +".png")));
			}
			background = ImageIO.read(loader.getResource("background.jpg"));
			simpleBlock= ImageIO.read(loader.getResource("SimpleBlock.jpg"));
			cannonBlock= ImageIO.read(loader.getResource("CannonBlock.jpg"));
			cannon = ImageIO.read(loader.getResource("Cannon.png"));
//			walkSound = Applet.newAudioClip((loader.getResource("Buzzer1.wavd")).toURI().toURL());
			for(int i=0;i<11;i++)
			{
				try
				{
					numPic[i]= ImageIO.read(loader.getResource("num"+i+".png"));
				}catch(Exception e)
				{
					numPic[i]=null;
					System.out.println("num"+i+".png"+" not found");
				}
			}
		} catch (Exception e) {
			walkSound = null;
		}
	}
}
