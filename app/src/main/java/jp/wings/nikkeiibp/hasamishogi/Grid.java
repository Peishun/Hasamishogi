package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 盤のマスを表すクラス
 *
 */

public class Grid {
	
	/** 空であることを表す定数 **/
	public static final int EMPTY = 0;
	
	/** 駒が移動してこれるかを表す定数**/
	public static final int CANPUT = 1;
	
	/** 先手の駒が置いてあることを表す定数 **/
	public static final int FIRST= 2;
	
	/** 後手の駒が置いてあることを表す定数 **/
	public static final int SECOND = 3;

	/** 前回移動した駒が置いてあること表す定数**/
	private boolean lastmove;

	/** マスの状態 **/
	private int state;
	
	/** 置いてある駒 **/
	private Piece piece;
	
	public Grid(){

		state = EMPTY;
		lastmove = false;
	}
	
	/**
	 * 状態を見る
	 * @return 状態
	 */
	public int getState() {
		return this.state;
	}

	/**
	 * 状態を設定する
	 * @param state
	 */
	public void setState(int state) {
		
		if( state >= EMPTY || state <= SECOND ){
			this.state = state;
		}
	}

	/**
	 * 駒をみる
	 * @return 駒
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * 駒をおく
	 * @param piece　駒
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isLastmove() {
		return lastmove;
	}

	public void setLastmove(boolean lastmove) {
		this.lastmove = lastmove;
	}
}
