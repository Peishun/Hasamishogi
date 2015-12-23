package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 角を表すクラス
 *
 */
public class Bishop extends Piece {

	/** X軸方向の移動範囲の定数 **/
	private static final int my_move_rangeX[][] = 
				{{1,2,3,4,5,6,7,8},   
				{-1,-2,-3,-4,-5,-6,-7,-8},
				{-1,-2,-3,-4,-5,-6,-7,-8},
				{1,2,3,4,5,6,7,8}};

	/** Y軸方向の移動範囲の定数 **/
	private static final int my_move_rangeY[][] =
				{{1,2,3,4,5,6,7,8},
				{1,2,3,4,5,6,7,8},
				{-1,-2,-3,-4,-5,-6,-7,-8},
				{-1,-2,-3,-4,-5,-6,-7,-8}};
	
	/** 使用コストの定数**/
	private static final int my_cost = 6;

	/** 画像リソースID **/
	private static final int my_resid = R.drawable.bishop;
	
	/**
	 * コンストラクタ
	 */
	public Bishop(){
		move_rangeX = my_move_rangeX;
		move_rangeY = my_move_rangeY;
		cost = my_cost;
		resid = my_resid;
	}
}
