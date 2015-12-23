package jp.wings.nikkeiibp.hasamishogi;

/**
 * Created by shunpei on 2015/12/22.
 */
public class Samplefield extends Field {

    Samplefield(int nYgrid , int nXgrid){
        super(nYgrid,nXgrid);
    }

    public void setting(){
        grids[0][0].setPiece(new Pawn());
        grids[0][1].setPiece(new Lance());
        grids[0][2].setPiece(new Knight());
        grids[0][3].setPiece(new Silver());
        grids[0][4].setPiece(new Gold());
        grids[0][5].setPiece(new Bishop());
        grids[0][6].setPiece(new Rook());
    }
}
