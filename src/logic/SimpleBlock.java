package logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import input.InputUtility;
import render.Resource;

public class SimpleBlock extends Block {

	public SimpleBlock(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		Color color = Color.GRAY;
		if (this.posessedBy != null) {
			color = this.posessedBy.getColor();
		}

		g2d.drawImage(Resource.simpleBlock, null, getX(), getY());
		g2d.setColor(color);
		g2d.fill3DRect(getX() + 10, getY() + 10, width - 20, 20, true);
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
//		if(this.posessedBy == null){
//			if(InputUtility.getKeyPressed(KeyEvent.VK_B)){
//				this.posessedBy = GameLogic.i;
//				GameLogic.nextPhase();
//			}
//			if(InputUtility.getKeyPressed(KeyEvent.VK_E)){
//				GameLogic.nextPhase();
//			}
//		} else {
//			if(currentBlock.posessedBy.equals(this)){
//				
//			} else {
//				this.decreaseHp(50);
//			}
//			GameLogic.nextPhase();
//		}
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
