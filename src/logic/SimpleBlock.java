package logic;


import java.awt.Color;
import java.awt.Graphics2D;

public class SimpleBlock extends Block{

	public SimpleBlock(int x, int y, int width, int height){
		super(x,y,width,height);
		
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Color color = Color.GRAY;
		if(this.posessedBy != null) {
			color = this.posessedBy.getColor();
		}
		g2d.setColor(Color.BLACK);
		g2d.fillRect(getX()-2, getY()-2, width+4, height+4);
		g2d.setColor(color);
		g2d.fillRect(getX(), getY(), width, height);
	}

	@Override
	public void updateStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWalking() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBlockAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnd() {
		// TODO Auto-generated method stub
		
	}

}
