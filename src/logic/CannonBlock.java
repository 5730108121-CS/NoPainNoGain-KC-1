package logic;

import java.awt.Color;
import java.awt.Graphics2D;

import render.Resource;

public class CannonBlock extends Block {

	public CannonBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
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

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Color color = Color.GRAY;
		if(this.posessedBy != null) {
			color = this.posessedBy.getColor();
		}
		
		g2d.drawImage(Resource.cannonBlock, null, getX(), getY());
		g2d.setColor(color);
		g2d.fill3DRect(getX() + 10, getY() + 10, width - 20, 20, true);
		g2d.drawImage(Resource.cannon, null, getX()+3, getY()-10);
	}

}
