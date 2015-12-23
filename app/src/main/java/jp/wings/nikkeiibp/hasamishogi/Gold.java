package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 金を表すクラス
 *
 */
public class Gold extends Piece {

	private static final int my_move_rangeX[][] = {{1},{1},{0},{-1},{-1},{0}};
	private static final int my_move_rangeY[][] = {{0},{-1},{-1},{-1},{0},{1}};
	private static final int my_cost = 5;
	private static final int my_resid = R.drawable.gold;

	public Gold(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
