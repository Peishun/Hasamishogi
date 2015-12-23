package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 香車を表すクラス
 *
 */
public class Lance extends Piece {
	
	private static final int my_move_rangeX[][] = {{0,0,0,0,0,0,0,0}};
	private static final int my_move_rangeY[][] = {{-1,-2,-3,-4,-5,-6,-7,-8}};
	private static final int my_cost = 2;
	private static final int my_resid = R.drawable.lance;
	
	public Lance(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
