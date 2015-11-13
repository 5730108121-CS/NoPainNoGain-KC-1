package logic;



import java.util.ArrayList;
import java.util.List;

import render.RenderableHolder;
import render.Resource;

public class GameLogic {
	
	//private PlayStatus[] playStatus;
	private Player[] player;
	private List<Block> blocks;
	
	private int ticCouter,z,index;
	private static int phase;
	static int PhaseStart=0,
			PhaseWalking=1,
			PhaseBlockAction=2,
			PhaseAction=3,
			PhaseEnd=4;
	public GameLogic() {
		// TODO Auto-generated constructor stub
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
		blocks =new ArrayList<>();
		//makeMap
		Block first = Field.getBlocks()[22];
		for(int i = 0;i<4;i++){
			//playStatus[i]=new PlayStatus();
			//RenderableHolder.getInstance().add(playStatus[i]);
			player[i] = new Player(first,Resource.characterSprite.get(i));
			RenderableHolder.getInstance().add(player[i]);
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
			GameLogic.nextPhase();
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
