package logic;



import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import render.GameScreen;
import render.RenderableHolder;
import render.Resource;

public class GameLogic {
	private static final int[][] image = {
			{3,0},
			{2,7},
			{1,5},
			{0,3}
	};
	private static Player[] player;
	private static List<Block> blocks;
	
	private PlayerStatus[] playerStatus;
	
	private int ticCouter,z;
	private static int index, phase;
	static int PhaseStart=0,
			PhaseWalking=1,
			PhaseBlockAction=2,
			PhaseAction=3,
			PhaseEnd=4;
	
	private static Color[] playercolor = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};
	
	static int PlaystatusHeight=150;
	static int PlaystatusWidth=200;
	static Point[] position=new Point[]{
		new Point(0,0),
		new Point(0,GameScreen.HEIGHT - PlaystatusHeight),
		new Point(GameScreen.WIDTH-PlaystatusWidth,0),
		new Point(GameScreen.WIDTH-PlaystatusWidth,GameScreen.HEIGHT - PlaystatusHeight)
	};
	
	
	public GameLogic() {
		// TODO Auto-generated constructor stub
		playerStatus=new PlayerStatus[4];
		blocks = new ArrayList<Block>();
			for(Block block : Field.getBlocks()){
				try{
					Field.checkBlock(block);
					RenderableHolder.getInstance().add(block);
					blocks.add(block);
				} catch (NullPointerException e){}
			}
		//dfdfdd
			
		player = new Player[4];
		//makeMap
		Block first = Field.getBlocks()[22];
		for(int i = 0;i<4;i++){
			
			BufferedImage playerImage = Resource.characterSprite.get(image[i][0]).getSubimage
					(96*(image[i][1]%4), 128*(image[i][1]/4), 96, 128);
			
			player[i] = new Player(first,playerImage,playercolor[i]);
			RenderableHolder.getInstance().add(player[i]);
			
			BufferedImage staturPlayerImage = Resource.faceSprite.get(image[i][0]).getSubimage
					(96*(image[i][1]%4), 96*(image[i][1]/4), 96, 96);
			
			playerStatus[i]=new PlayerStatus(position[i],PlaystatusHeight,PlaystatusWidth,playercolor[i],staturPlayerImage,player[i]);
			RenderableHolder.getInstance().add(playerStatus[i]);
		}
		phase=PhaseStart;
		index=0;
		ticCouter=0;
		z=0;
	}
	
	public void logicUpdate() {
		// TODO Auto-generated method stub
		Player now=player[index];
		if(phase==PhaseStart)
		{
			now.updateStart();
//			if(now.endStart())
//			{
//				phase+=1;
//			}
		}
		else if(phase==PhaseWalking)
		{
			now.updateWalking();
//			if(now.endWalking())
//			{
//				phase+=1;
//			}
		}
		
		else if(phase==PhaseBlockAction)
		{
			now.updateBlockAction();
//			if(now.endBlockAction())
//			{
//				phase+=1;
//			}
		}
		else if(phase==PhaseAction)
		{
			now.updateAction();
//			if(now.endAciton())
//			{
//				phase+=1;
//			}
		}
		else if(phase==PhaseEnd)
		{
			index++;
			if(index == 4){
				index = 0;
			}
			nextPhase();
//			if(endEnd())
//			{
//				phase=0;
//			}
		}/*
	}

	private boolean endEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean endWalking() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean endStart() {
		// TODO Auto-generated method stub
		return false;
	}*/
		
	
	}
	public static void nextPhase(){
		if(phase != GameLogic.PhaseEnd)
			phase ++;
		else
			phase = 0;
	}
}
