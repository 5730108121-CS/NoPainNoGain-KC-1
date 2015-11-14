package logic;

public class Field {
	
	private static final Field currentField = new Field(100);
	public static Field getInstance(){
		return currentField;
	}
	
	private static Block[] blocks;
	public static int[][] field;
			
	public Field(int length){	
		int[][] field = {
				{1,1,1,1,0,0,0,0,0,0},
				{1,0,0,1,0,0,0,0,0,0},
				{1,0,1,1,1,1,1,1,0,0},
				{1,1,1,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,1,0,0},
				{0,0,1,1,1,1,1,1,1,0},
				{0,0,0,0,0,0,1,0,1,0},
				{0,0,0,0,0,0,1,1,1,0}};
		this.field = field;
		createField(length);
		setNextBlock();
	}
	
	public static Block[] getBlocks(){
		return blocks;
	}
	
	public static void createField(int length){
		blocks = new Block[length];
		for(int x=0; x< field[0].length; x++){
			for(int y=0; y< field.length; y++){
				if(getTerrain(x,y) == 1){
					blocks[x+y*10] = new SimpleBlock(75*x+280, 75*y+75, 75, 75);
					
				}
				if(getTerrain(x,y) == 2){
					blocks[x+y*10] = new CannonBlock(75*x+280, 75*y+75, 75, 75);
				}
				try{
					blocks[x+y*10].setZ(y-10);
				} catch (NullPointerException e){
					System.out.println("Blank Block");
				}
			}
		}
	}
	
	public static int getTerrain(int x,int y){
		if(x < 0 || x >= field[0].length || y < 0 || y >= field.length)
			return -3;
		return field[y][x];
	}
	
	public static void setNextBlock(){
		for(int i = 0;i<blocks.length;i++){
			int x = i%10;
			int y = i/10;
			Block[] next = new Block[4];
			if(getTerrain(x-1,y) > 0){
				next[Player.LEFT] = blocks[(x-1)+(y)*10];
			}
			if(getTerrain(x+1,y) > 0){
				next[Player.RIGHT] = blocks[(x+1)+(y)*10];		
			}
			if(getTerrain(x,y-1) > 0){
				next[Player.UP] = blocks[(x)+(y-1)*10];
				
			}
			if(getTerrain(x,y+1) > 0){
				next[Player.DOWN] = blocks[(x)+(y+1)*10];
			}
			try{
				checkBlock(blocks[i]);
				blocks[i].setNextBlock(next);
			} catch(NullPointerException e){
				
			}
		}
	}
	
	public static void checkBlock(Block block) throws NullPointerException {
		if(block == null)
			throw new NullPointerException();
	}
}
