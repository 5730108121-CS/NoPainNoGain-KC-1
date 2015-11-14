package logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.IRenderable;
import render.Resource;

public class PlayerStatus implements IRenderable {

	private Player player;
	int height, width;
	Point position;
	int x, y;
	Color color;
	int numStep;
	private BufferedImage image;
	int hp;// persent

	private Font font = new Font("Tahoma", Font.BOLD, 10);

	public PlayerStatus(Point position, int high, int width, Color color, BufferedImage image, Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		this.position = position;
		x = position.getX();
		y = position.getY();
		this.height = high;
		this.width = width;
		this.color = color;
		this.image = image;
		hp = player.getHP();
		numStep = 0;
	}

	public void update() {
		if(this.hp != player.getHP()){
			this.hp --;
		}
		this.numStep = player.getNumStep();
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.setColor(Color.BLACK);
		g2.fillRect(x, y, width,height);
		g2.setColor(color);
		g2.fillRect(x+15, y+5, width-30,10);
		if (image != null) {
			g2.drawImage(image, null, x + 50, y + 20);
		}

		g2.setColor(Color.darkGray);
		g2.drawRect(x + 35, y + 125, 150, 15);
		g2.setColor(Color.RED);
		g2.fillRect(x + 35, y + 125, 150 * hp / 100, 15);
		g2.drawString("HP", x + 15, y + 137);
		if (Resource.numPic[numStep] != null) {
			if (numStep != 0) {
				g2.drawImage(Resource.numPic[numStep], null, x + 50, y + 200);
			}
		}
		// 55
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
