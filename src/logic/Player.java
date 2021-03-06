package logic;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import input.InputUtility;
import render.Resource;

public class Player implements Entity {
	private int phase;
	//direction
	final static int DOWN = 0;
	final static int LEFT = 1;
	final static int RIGHT = 2;
	final static int UP = 3;
	
	final static int Speed = 3;
	final static int MaxDistance = 6;
	final static int MaxHP = 500;
	
	private int hp;
	private Point position;
	private int z;
	private int direction;
	private Point destination;
	
	private static int size = 30;
	private Color color;
	private BufferedImage image;
	
	private int numStep;
	private int countStep;
	private boolean walking;
	private int indexWalking;
	
	private Block currentBlock;
	
	public Player(Block b,BufferedImage image,Color color){
		position = new Point(0,0);
		this.currentBlock = b;
		b.addPlayer(this);
		
		this.image = image;
		direction = Player.DOWN;
		
		this.color = color;
		
		position.setX(this.currentBlock.getPosition().getX());
		position.setY(this.currentBlock.getPosition().getY());
		setZ(this.currentBlock.getZFromBlock());
		hp = MaxHP;
	}
	
	public int getNumStep(){
		return numStep - countStep;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		int direction = Player.DOWN;
		if(phase == GameLogic.PhaseWalking){
			direction = this.direction;
		}
		g2d.drawImage(image.getSubimage(((indexWalking/5)%3)*32, direction*32, 32, 32), null, getX()-size/2, getY()-size/2);
	}
	
	// update get attribute
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	public void setZ(int value){
		this.z = value;
	}
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return position.getX();
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return position.getY();
	}
	@Override
	public boolean collideWith(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}
	public Color getColor(){
		return color;
	}
	public void decreaseHp(int value){
		hp -= value;
	}	
	public int getHP(){
		return (hp * 100)/ MaxHP;
	}
	
	// update each phase
	@Override
	public void updateStart() {
		// TODO Auto-generated method stub
		this.phase = GameLogic.PhaseStart;
		if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE)){
			this.numStep = (int) (Math.random()*6+1);
			GameLogic.nextPhase();
		}
	}
	@Override
	public void updateWalking() {
		// TODO Auto-generated method stub.
		
		this.phase = GameLogic.PhaseWalking;
		if(walking){
			indexWalking++;
			if(!finishX()){
				stepX();
			} 
			if(!finishY()){
				stepY();
			} 
			if(finishX() && finishY()) {
				endStep();
			}
		} else {
			try{
				if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
					if(direction != RIGHT){
						step(LEFT);
					}
				}
				if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
					if(direction != LEFT){
						step(RIGHT);
					}
				}
				if(InputUtility.getKeyPressed(KeyEvent.VK_W)){
					if(direction != DOWN){
						step(UP);	
					}
				}
				if(InputUtility.getKeyPressed(KeyEvent.VK_S)){
					if(direction != UP){
						step(DOWN);					
					}
				}
			} catch (NullPointerException e){}
		}
		
	}
	@Override
	public void updateBlockAction() {
		// TODO Auto-generated method stub
		this.phase = GameLogic.PhaseBlockAction;
		if(currentBlock.posessedBy == null){
			if(InputUtility.getKeyPressed(KeyEvent.VK_B)){
				currentBlock.posessedBy = this;
				GameLogic.nextPhase();
			}
			if(InputUtility.getKeyPressed(KeyEvent.VK_E)){
				GameLogic.nextPhase();
			}
		} else {
			if(currentBlock.posessedBy.equals(this)){
				
			} else {
				this.decreaseHp(50);
			}
			GameLogic.nextPhase();
		}
	}
	@Override
	public void updateAction() {
		// TODO Auto-generated method stub
		this.phase = GameLogic.PhaseAction;
		try{
			if(currentBlock.posessedBy.equals(this)){
				GameLogic.nextPhase();
			} else {
				if(currentBlock instanceof SimpleBlock){
					if(InputUtility.getKeyPressed(KeyEvent.VK_C)){
						this.decreaseHp(50);
						currentBlock.posessedBy = this;
						GameLogic.nextPhase();
					}
				}
			}
			if(InputUtility.getKeyPressed(KeyEvent.VK_E)){
				GameLogic.nextPhase();
			}
		} catch (NullPointerException e){
			GameLogic.nextPhase(); 
		}
	}
	@Override
	public void updateEnd() {
		// TODO Auto-generated method stub
		this.phase = GameLogic.PhaseEnd;
	}
	
	// for update Walking
	private void step(int direction){
		Field.checkBlock(currentBlock.nextBlock[direction]);
		currentBlock.removePlayer(this);
		currentBlock = currentBlock.nextBlock[direction];
		this.direction = direction;
		destination = currentBlock.getPosition();
		this.setZ(currentBlock.getZFromBlock());
		currentBlock.addPlayer(this);
		walking = true;
		countStep++;
	}
	private void stepX(){
		int sign = destination.comparePositionX(this);
		position.setX(position.getX() + sign*Speed);
		
		boolean check = sign == -1 ? position.getX() > destination.getX() 
				:  position.getX() < destination.getX();
		if(!check){
			position.setX(destination.getX());
		}
	}
	private void stepY(){
		int sign = destination.comparePositionY(this);
		position.setY(position.getY() + sign*Speed);
		boolean check = sign ==  1 ? position.getY() < destination.getY() 
				:  position.getY() > destination.getY();
		if(!check){
			position.setY(destination.getY());
		}
	}
	private boolean finishX(){
		return position.getX() == destination.getX();
	}
	private boolean finishY(){
		return position.getY() == destination.getY();
	}
	private void endStep(){
		walking = false;
		destination = null;
		if(countStep >= numStep){ 
			GameLogic.nextPhase();
			countStep = 0;
			numStep = 0;
			indexWalking = 0; 
		}
	}

}
