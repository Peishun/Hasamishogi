package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 駒を表すクラス
 *
 */
public class Piece {
	
	/** 移動範囲X **/
	protected int move_rangeX[][];
	
	/** 移動範囲Y **/
	protected int move_rangeY[][];
	
	/** 使用コスト **/
	protected int cost;

	/** 画像id **/
	protected int resid;
	
	/**
	 * 移動範囲(X座標)をみる
	 * @return 移動範囲(X座標)
	 */
	public int[][] getMove_rangeX(){
		return move_rangeX;
	};
	
	/**
	 * 移動範囲(Y座標)をみる
	 * @return 移動範囲(Y座標)
	 */
	public int[][] getMove_rangeY(){
		return move_rangeY;
	};
	
	/**
	 * 使用コストをみる
	 * @return 使用コスト
	 */
	public int getCost(){
		return cost;
	};

	public int getResid(){
		return resid;
	}

}
