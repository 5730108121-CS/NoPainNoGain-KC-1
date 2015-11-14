import java.util.Collections;
import java.util.Comparator;

import javax.swing.JComponent;
import javax.swing.JFrame;

import render.GameScreen;
import render.IRenderable;
import render.RenderableHolder;
import logic.GameLogic;



public class Main {
	
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Catch a friut");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameLogic logic = new GameLogic();
		JComponent gameScreen = new GameScreen();
		
		frame.getContentPane().add(gameScreen);
		frame.setVisible(true);
		frame.pack();
		gameScreen.requestFocus();
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			Collections.sort(RenderableHolder.getInstance().getRenderableList(), new Comparator<IRenderable>() {
				@Override
				public int compare(IRenderable o1, IRenderable o2) {
					if(o1.getZ() > o2.getZ()) return 1;
					return -1;
				}
			});
			gameScreen.repaint();
			logic.logicUpdate();
			//InputUtility.updateInputState();
		}
	}
}
