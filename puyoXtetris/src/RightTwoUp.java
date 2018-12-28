
import java.awt.Color;



public class RightTwoUp extends TetrisBlock {

	public RightTwoUp(int x, int y) {
		super(x, y, new Color(47,157,39), new Color(255,255,255));
		type = TYPE_RIGHTTWOUP;
	}

	@Override
	/**
	 * ROTATION_0 : 
	 * 		  2 3
	 * 		1 0 
	 * ROTATION_90 : 
	 * 		3 
	 * 		2 0
	 * 		  1 
	 * ROTATION_180 : 
	 * 		  2 3
	 * 		1 0 
	 * ROTATION_270 : 
	 * 		3 
	 * 		2 0
	 * 		  1 
	 * 
	 */
	public void rotation(int rotation_index) {
		this.rotation_index = rotation_index;
		switch(rotation_index){
		case ROTATION_0 : 
			colBlock[2].setFixGridXY(0,-1);
			colBlock[3].setFixGridXY(1,-1);
			colBlock[1].setFixGridXY(-1,0);
			colBlock[0].setFixGridXY(0,0);
			break;
		case ROTATION_90 : 
			colBlock[3].setFixGridXY(-1,-1);
			colBlock[2].setFixGridXY(-1,0);
			colBlock[0].setFixGridXY(0,0);
			colBlock[1].setFixGridXY(0,1);
			break;
		case ROTATION_180 : 
			colBlock[2].setFixGridXY(0,-1);
			colBlock[3].setFixGridXY(1,-1);
			colBlock[1].setFixGridXY(-1,0);
			colBlock[0].setFixGridXY(0,0);
			break;
		case ROTATION_270 : 
			colBlock[3].setFixGridXY(-1,-1);
			colBlock[2].setFixGridXY(-1,0);
			colBlock[0].setFixGridXY(0,0);
			colBlock[1].setFixGridXY(0,1);
			break;
		}//switch
	}//rotation

}