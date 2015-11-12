package logic;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;



public abstract class Block implements Entity{

	protected int z,width,height;
	
	protected Point position;
	
	protected Point[] positionPlayer;
	protected Player posessedBy;
	protected List<Player> inThisBlock;
	protected Block[] nextBlock;
	
	protected Color color;
	
	public Block(int x, int y,int width,int height){
		nextBlock = new Block[4];
		
		position = new Point(x,y);
		this.width = width;
		this.height = height;

		inThisBlock = new ArrayList<Player>();
		
		positionPlayer = new Point[4];
		
		int centerx = x + width/4;
		int centery = y + height/4;
		for(int i = 0 ; i<4 ; i++){
			int positionx = centerx + (width * (i / 2))/2;
			int positiony = centery + (height * (i % 2))/2;
			positionPlayer[i] = new Point(positionx,positiony);
		}
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
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

	
	public Block[] getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(Block[] nextBlock) {
		this.nextBlock = nextBlock;
	}

	public Point getPosition(){
		return positionPlayer[indexOfPosition()];
	}	
	
	private int indexOfPosition(){
		for(int i = 0;i<4;i++){
			if(!checkPoint(i)){
				return i;
			}
		}
		return -1;
	}
	
	private boolean checkPoint(int i){
		for(Player p : inThisBlock){
			if(positionPlayer[i].inPoint(p)){
				return true;
			}
		}
		return false;
	}
	
	public void addPlayer(Player p){
		inThisBlock.add(p);
	}
	public void removePlayer(Player p){
		inThisBlock.remove(p);
	}
}
