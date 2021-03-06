package jp.wings.nikkeiibp.hasamishogi;

/**
 * 
 * 盤を表すクラス
 *
 */
public class Field {

	/** X軸のマスの数 **/
	protected int nXgrid;
	
	/** Y軸のマスの数 **/
	protected int nYgrid;
	
	/** 構成されるマスの配列 **/
	protected Grid grids[][];
	
	/**
	 * コンストラクタ
	 * @param nYgrid Y軸のマスの数
	 * @param nXgrid X軸のマスの数
	 */
	public Field(int nYgrid , int nXgrid){
		this.nYgrid = nYgrid;
		this.nXgrid = nXgrid;
		this.grids = new Grid[nYgrid][nXgrid];

		for( int i = 0; i < nYgrid ; i++  ){
			for( int j = 0; j < nXgrid ; j++ ){
				grids[i][j] = new Grid();
			}
		}
	}

	public int getgridstate(int y,int x){
		return grids[y][x].getState();
	}

	public Piece getOnpiece(int y,int x){
		return grids[y][x].getPiece();
	}

	public boolean getisgridsselected(int y,int x){
		return grids[y][x].isSelected();
	}

	/**
	 * 前回移動した駒の状態をリセットするメソッド
	 */
	public void selectedInit(){

		for(int i = 0; i < nYgrid; i++ ){

			for(int j = 0; j < nXgrid; j++ ){
				if( grids[i][j].isSelected() ){
					grids[i][j].setSelected(false);
				}
			}
		}
	}

	public void gridsselected(int y,int x){
		grids[y][x].setSelected(true);
	}

}
