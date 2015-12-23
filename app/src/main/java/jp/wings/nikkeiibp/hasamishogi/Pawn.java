package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 歩を表すクラス
 *
 */
public class Pawn extends Piece {
	
	private static final int my_move_rangeX[][] = {{0}};
	private static final int my_move_rangeY[][] = {{-1}};
	private static final int my_cost = 1;
	private static final int my_resid = R.drawable.pawn;

	public Pawn(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
