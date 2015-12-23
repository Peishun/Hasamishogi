package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 飛車を表すクラス
 *
 */
public class Rook extends Piece {
	
	private static final int my_move_rangeX[][] = 
				{{1,2,3,4,5,6,7,8},   
				{0,0,0,0,0,0,0,0},
				{-1,-2,-3,-4,-5,-6,-7,-8},
				{0,0,0,0,0,0,0,0}};

	private static final int my_move_rangeY[][] =
				{{0,0,0,0,0,0,0,0},
				{1,2,3,4,5,6,7,8},
				{0,0,0,0,0,0,0,0},
				{-1,-2,-3,-4,-5,-6,-7,-8}};

	private static final int my_cost = 7;

	private static final int my_resid = R.drawable.rook;
	
	public Rook(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
