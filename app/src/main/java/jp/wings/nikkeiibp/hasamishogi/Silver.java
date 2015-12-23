package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 銀を表すクラス
 *
 */
public class Silver extends Piece {
	
	private static final int my_move_rangeX[][] = {{1},{0},{-1},{-1},{1}};
	private static final int my_move_rangeY[][] = {{-1},{-1},{-1},{1},{1}};
	private static final int my_cost = 4;
	private static final int my_resid = R.drawable.silver;
	
	public Silver(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
