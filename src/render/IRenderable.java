package render;
import java.awt.Graphics2D;

public interface IRenderable {
	
	public void draw (Graphics2D g2) ;
	public boolean  isVisible();
	public int getZ();
	public boolean isDestroyed();
}
