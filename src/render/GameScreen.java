package render;
import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;




public class GameScreen extends JComponent{
	
	public GameScreen() {
		// TODO Auto-generated constructor stub
		super();
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(640,480));
		setVisible(true);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(arg0.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				InputUtility.setKeyPressed(arg0.getKeyCode(), true);
			}
		});
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, 640, 420);
		
		//Preventing thread interference
		synchronized(RenderableHolder.getInstance()){
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				
				if(entity.isVisible()){
					entity.draw(g2d);
				}
			}
			
		}
	}
}
