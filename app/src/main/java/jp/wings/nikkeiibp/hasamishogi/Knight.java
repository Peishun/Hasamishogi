package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 桂馬を表すクラス
 *
 */
public class Knight extends Piece {
	
	private static final int my_move_rangeX[][] = {{1},{-1}};
	private static final int my_move_rangeY[][] = {{-2},{-2}};
	private static final int my_cost = 3;
	private static final int my_resid = R.drawable.knight;

	public Knight(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
