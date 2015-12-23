package jp.wings.nikkeiibp.hasamishogi;

import java.util.ArrayList;

public class Battlefield extends Field {

	/** 移動前後の駒が置いてあるマス **/
	private Grid movegrid;

	/**
	 * コンストラクタ
	 * @param nYgrid　Y軸のマスの数
	 * @param nXgrid　X軸のマスの数
	 */
	public Battlefield(int nYgrid, int nXgrid) {
		super(nYgrid, nXgrid);
	}

	public void test(){
		this.grids[8][8].setPiece(new Rook());
		this.grids[8][8].setState(Grid.FIRST);
		this.grids[8][7].setPiece(new Rook());
		this.grids[8][7].setState(Grid.FIRST);
		this.grids[8][6].setPiece(new Rook());
		this.grids[8][6].setState(Grid.FIRST);
		this.grids[8][5].setPiece(new Rook());
		this.grids[8][5].setState(Grid.FIRST);
		this.grids[8][4].setPiece(new Rook());
		this.grids[8][4].setState(Grid.FIRST);
		this.grids[8][3].setPiece(new Rook());
		this.grids[8][3].setState(Grid.FIRST);
		this.grids[8][2].setPiece(new Rook());
		this.grids[8][2].setState(Grid.FIRST);
		this.grids[8][1].setPiece(new Rook());
		this.grids[8][1].setState(Grid.FIRST);
		this.grids[8][0].setPiece(new Rook());
		this.grids[8][0].setState(Grid.FIRST);

		this.grids[0][8].setPiece(new Rook());
		this.grids[0][8].setState(Grid.SECOND);
		this.grids[0][7].setPiece(new Rook());
		this.grids[0][7].setState(Grid.SECOND);
		this.grids[0][6].setPiece(new Rook());
		this.grids[0][6].setState(Grid.SECOND);
		this.grids[0][5].setPiece(new Rook());
		this.grids[0][5].setState(Grid.SECOND);
		this.grids[0][4].setPiece(new Rook());
		this.grids[0][4].setState(Grid.SECOND);
		this.grids[0][3].setPiece(new Rook());
		this.grids[0][3].setState(Grid.SECOND);
		this.grids[0][2].setPiece(new Rook());
		this.grids[0][2].setState(Grid.SECOND);
		this.grids[0][1].setPiece(new Rook());
		this.grids[0][1].setState(Grid.SECOND);
		this.grids[0][0].setPiece(new Rook());
		this.grids[0][0].setState(Grid.SECOND);
	}
	
	
	 /** 移動範囲をマスに適応するメソッド
	 /* @param y 指定マスのy座標
	 /* @param x 指定マスのx座標
	 /* @param state 自分の駒
	 */
	public void moverangeshow(int y,int x,int state){

		if(grids[y][x].getState() != Grid.CANPUT){
			/** 移動範囲リセット **/
			moverangeInit();
		}
		/** 自分の駒の座標の場合、移動範囲適応 **/
		if( grids[y][x].getState() == state ){
			rangeshow(y, x);
		}

	}

	public boolean isgridslastmove(int y,int x){
		return grids[y][x].isLastmove();
	}

	
	/**
	 * 駒の移動範囲表示メソッド
	 * @param y　指定マスのｙ座標
	 * @param x 指定マスのx座標
	 */
	private void rangeshow(int y, int x) {
		
		int userangeX[][] = grids[y][x].getPiece().getMove_rangeX();
		int userangeY[][] = grids[y][x].getPiece().getMove_rangeY();

		for( int i = 0; i < userangeX.length ; i++ ){
			for( int j = 0; j < userangeX[i].length ; j++ ){

				int targetX = x + userangeX[i][j];
				int targetY = y + userangeY[i][j];

				if( ( targetX >= nXgrid ) || ( targetY >= nYgrid ) || ( targetX < 0 ) || ( targetY < 0 ) ){
					break;
				}

				int checkstate = grids[targetY][targetX].getState();

				if( checkstate >= Grid.FIRST ){
					break;
				}

				grids[targetY][targetX].setState(Grid.CANPUT);
				movegrid = grids[y][x];
			}
		}
		
	}
	
	
	/**
	 * 取れる駒がある場合、その駒を返すメソッド
	 * @param y　指定するｙ座標
	 * @param x 指定するx座標
	 */
	public ArrayList<Piece> getpiece(int y, int x,int mystate){

		ArrayList<Piece> getpieces = new ArrayList<Piece>();

		int[][] userangeX ={{1,2,3,4,5,6,7,8},
							{0,0,0,0,0,0,0,0},
							{-1,-2,-3,-4,-5,-6,-7,-8},
							{0,0,0,0,0,0,0,0}};
		
		int[][] userangeY ={{0,0,0,0,0,0,0,0},
							{1,2,3,4,5,6,7,8},
							{0,0,0,0,0,0,0,0},
							{-1,-2,-3,-4,-5,-6,-7,-8}};

		for( int i = 0; i < userangeX.length; i++ ){
			for( int j = 0; j < userangeX[i].length; j++ ){
				
				int targetX = x + userangeX[i][j];
				int targetY = y + userangeY[i][j];
				
				if( ( targetX >= nXgrid ) || ( targetY >= nYgrid ) || ( targetX < 0 ) || ( targetY < 0 )  ){
					break;
				}
				
				if( grids[targetY][targetX].getState() == Grid.EMPTY || grids[targetY][targetX].getState() == Grid.CANPUT   ){
					break;
				}
				
				if( grids[targetY][targetX].getState() == mystate ){

					for( int k = j - 1; k >= 0; k-- ){
						
						int getX = x + userangeX[i][k];
						int getY = y + userangeY[i][k];

						getpieces.add(grids[getY][getX].getPiece());

						grids[getY][getX].setState(Grid.EMPTY);
						grids[getY][getX].setPiece(null);
					}

					break;
				}
			}
		}

		return getpieces;
	}

	
	/**
	 * 移動可能範囲をリセットするメソッド
	 */
	protected void moverangeInit(){
		
		for(int i = 0; i < nYgrid; i++ ){
			
			for(int j = 0; j < nXgrid; j++ ){
				
				if( grids[i][j].getState() == Grid.CANPUT ){
					grids[i][j].setState(Grid.EMPTY);
				}
				
			}
		}
	}

	/**
	 * 前回移動した駒の状態をリセットするメソッド
	 */
	protected void lastmoveInit(){

		for(int i = 0; i < nYgrid; i++ ){

			for(int j = 0; j < nXgrid; j++ ){

				if( grids[i][j].isLastmove() ){
					grids[i][j].setLastmove(false);
				}

			}
		}
	}

	/**
	 * 駒を移動するメソッド
	 * @param y　指定マスのy座標
	 * @param x 指定マスのx座標
	 */
	public boolean movepiece(int y,int x){

		/** 移動可能範囲のマスの場合、移動 **/
		if( grids[y][x].getState() == Grid.CANPUT ){

			grids[y][x].setState(movegrid.getState());

			grids[y][x].setPiece(movegrid.getPiece());

			lastmoveInit();

			grids[y][x].setLastmove(true);

			movegrid.setState(Grid.EMPTY);

			movegrid.setPiece(null);

			/** 移動範囲リセット **/
			moverangeInit();

			return true;
		}

		return false;
	}

	public void reverse(){
		for(int  i = 0 ; i < ( nYgrid / 2 + 1 ) ; i++ ) {
			for (int j = 0; j < nXgrid ; j++) {

				Grid tmpgrid;

				tmpgrid = grids[i][j];
				grids[i][j] = grids[nYgrid - i - 1][nXgrid - j - 1];
				grids[nYgrid - i - 1][nXgrid - j - 1] = tmpgrid;

				if( ( i ==  (nYgrid / 2) )  && ( j ==  (nXgrid /2) ) ){
					break;
				}

			}
		}
	}




}
