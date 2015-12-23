package jp.wings.nikkeiibp.hasamishogi;

public class Preparefield extends Field{

	private int maxcost;

	private int havecost;
	
	/**
	 * コンストラクタ
	 * @param nYgrid　Y軸のマスの数
	 * @param nXgrid　X軸のマスの数
	 */
	public Preparefield(int nYgrid, int nXgrid,int maxcost) {
		super(nYgrid, nXgrid);
		this.maxcost = maxcost;
		this.havecost = 0;
	}
	
	/**
	 * 駒をセットする
	 * @param y 指定Y座標
	 * @param x 指定X座標
	 * @param putpiece セットする駒
	 */
	public boolean PutPiece(int y,int x,Piece putpiece,int state){
		
		int usecost = 0;
		Piece alreadyOnpiece;

		if( ( x < nXgrid ) && ( y < nYgrid ) && ( x >= 0 ) && ( y >= 0 ) ) {

			alreadyOnpiece = grids[y][x].getPiece();

			if (alreadyOnpiece == null) {
				usecost = putpiece.cost;
			} else {
				usecost = putpiece.cost - alreadyOnpiece.cost;
			}

			if ((havecost + usecost) <= maxcost) {
				this.grids[y][x].setPiece(putpiece);
				havecost += usecost;
				grids[y][x].setState(state);
			} else {
				return false;
			}
			return true;
		}
		return false;
	}

	public void fieldreset(){

		havecost= 0;

		for(int i = 0; i < nYgrid; i++ ){

			for(int j = 0; j < nXgrid; j++ ){

				grids[i][j].setState(Grid.EMPTY);
				grids[i][j].setPiece(null);
			}
		}

	}

	public int getHavecost() {
		return havecost;
	}

}
