package jp.wings.nikkeiibp.hasamishogi;

import java.util.ArrayList;

public class Player {
	
	private String name;
	
	private int mystate;

	private Battlefield battlefield;

	public Preparefield getPreparefield() {
		return preparefield;
	}

	private Preparefield preparefield;

	private ArrayList<Piece> my_get_pieces = new ArrayList<Piece>();
	
	public Player(String name,int mystate,Battlefield battlefield){
		this.name = name;
		this.mystate = mystate;
		this.battlefield = battlefield;
	}

	public Player(String name,int mystate,Preparefield preparefield) {
		this.name = name;
		this.mystate = mystate;
		this.preparefield = preparefield;
	}
	
	public String toString(){
		return name;
	}
	
	public int getMystate(){
		return mystate;
	}

	public int getNumberOfmy_get_pieces(){
		return my_get_pieces.size();
	}

	public Piece getMy_get_pieces(int index){
		return my_get_pieces.get(index);
	}

	private void setMy_get_pieces(ArrayList<Piece> get_pieces) {
		for(int i = 0; i < get_pieces.size() ; i++){
			my_get_pieces.add(get_pieces.get(i));
		}
	}

	public boolean play(int y,int x){

		battlefield.moverangeshow(y, x, mystate);

		if(battlefield.movepiece(y,x)){
			setMy_get_pieces(battlefield.getpiece(y, x, mystate));
			//battlefield.reverse();
			return true;
		}
		return false;
	}

	public boolean putPiece(int y,int x,Piece piece){
		return preparefield.PutPiece(y, x,piece, mystate);
	}

	public Battlefield getBattlefield() {
		return battlefield;
	}

}
