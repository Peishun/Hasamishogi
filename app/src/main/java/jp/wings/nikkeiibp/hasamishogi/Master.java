package jp.wings.nikkeiibp.hasamishogi;

import java.util.ArrayList;

/**
 * Created by shunpei on 2015/12/20.
 */
public class Master {

    private ArrayList<Player> players =new ArrayList<Player>();
    private int turnCount = 0;

    public Master(){}

    public Player getCurrentPlayer(){
        int currentPlayerIndex = turnCount % players.size();
        return players.get(currentPlayerIndex);
    }

    public Player getNextPlayer(){
        int nextPlayerIndex = (turnCount + 1) % players.size();
        return players.get(nextPlayerIndex);
    }

    public void registerPlayer(Player player){
        players.add(player);
    }

    public void Nextturn(){
        turnCount++;
    }




}
