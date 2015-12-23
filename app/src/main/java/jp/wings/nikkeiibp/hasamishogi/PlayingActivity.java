package jp.wings.nikkeiibp.hasamishogi;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayingActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int WIN_NUMBER_OF_GETPECES = 6;

    private int getfieldcolor;
    private int canputcolor;
    private int lastmovecolor;

    /* マスとなるイメージボタン */
    private ImageButton[][] imageButtons;

    /* 取得した駒を置くイメージビュー */
    private ImageView[] opponent_imageViews;
    private ImageView[] my_imageViews;

    /* Player名を表示するテキストビュー */
    private TextView opponent_player_name;
    private TextView my_player_name;

    /* プレイヤーおよびゲームマスター */
    private Player player1;
    private Player player2;
    private Master master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        Battlefield battlefield = new Battlefield(Hasamishogi_Const.FIELD_ROW,Hasamishogi_Const.FIELD_COLUMN);

        //テスト用データ入力
        battlefield.test();

        master = new Master();
        player1 = new Player("Player1",Grid.FIRST,battlefield);
        player2 = new Player("Player2",Grid.SECOND,battlefield);
        master.registerPlayer(player1);
        master.registerPlayer(player2);

        /* ツールバーの生成と表示 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* レイアウトの取得 */
        GridLayout gridLayout = (GridLayout) findViewById(R.id.playing_gridlayout);
        LinearLayout opponent_linearlayout = (LinearLayout)findViewById(R.id.opponent_linearlayout);
        LinearLayout my_linearlayout = (LinearLayout)findViewById(R.id.my_linearlayout);

        /* テキストビューを取得 */
        opponent_player_name = (TextView)findViewById(R.id.opponent_playerName);
        my_player_name = (TextView)findViewById(R.id.my_playerName);

        opponent_player_name.setText(player2.toString());
        my_player_name.setText(player1.toString());

        /* 色の取得 */
        canputcolor = getResources().getColor(R.color.palette_red_14);
        lastmovecolor = getResources().getColor(R.color.colorAccent);

        /* 生成したViewの定義 */
        imageButtons = new ImageButton[Hasamishogi_Const.FIELD_ROW][Hasamishogi_Const.FIELD_COLUMN];
        opponent_imageViews = new ImageView[Hasamishogi_Const.FIELD_COLUMN];
        my_imageViews = new ImageView[Hasamishogi_Const.FIELD_COLUMN];

        /* 生成したViewのレイアウトパラメータの定義 */
        GridLayout.LayoutParams[][] grid_paramses = new GridLayout.LayoutParams[Hasamishogi_Const.FIELD_ROW][Hasamishogi_Const.FIELD_COLUMN];
        LinearLayout.LayoutParams linner_params = new LinearLayout.LayoutParams(100, 100);
        linner_params.setMargins(2, 2, 2, 2);

        /* 取得した駒を置くイメージビューを配置する */
        for( int k = 0 ; k < Hasamishogi_Const.FIELD_COLUMN; k++ ){

            opponent_imageViews[k] = new ImageView(this);
            my_imageViews[k] = new ImageView(this);

            opponent_imageViews[k].setLayoutParams(linner_params);
            my_imageViews[k].setLayoutParams(linner_params);

            /* 背景色を設定 */
            opponent_imageViews[k].setBackgroundResource(R.drawable.tree);
            my_imageViews[k].setBackgroundResource(R.drawable.tree);

            opponent_imageViews[k].setScaleType(ImageView.ScaleType.CENTER_CROP);
            my_imageViews[k].setScaleType(ImageView.ScaleType.CENTER_CROP);

            /*  レイアウトに追加 */
            opponent_linearlayout.addView(opponent_imageViews[k]);
            my_linearlayout.addView(my_imageViews[k]);

        }
        for(int  i = 0 ; i < Hasamishogi_Const.FIELD_ROW ; i++ ){
            for(int j = 0; j < Hasamishogi_Const.FIELD_COLUMN ; j++ ){

                imageButtons[i][j] = new ImageButton(this);
                grid_paramses[i][j] = new GridLayout.LayoutParams();

                /* レイアウトパラメータの設定 */
                grid_paramses[i][j].rowSpec = GridLayout.spec(i);
                grid_paramses[i][j].columnSpec = GridLayout.spec(j);
                grid_paramses[i][j].width = 100;
                grid_paramses[i][j].height = 100;
                grid_paramses[i][j].setMargins(2, 2, 2, 2);

                /* レイアウトパラメータを指定 */
                imageButtons[i][j].setLayoutParams(grid_paramses[i][j]);

                /* imgbtnに名前を付ける */
                imageButtons[i][j].setId( i * 10 + j );

                /* 画像表示形式の設定 */
                imageButtons[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);

                /* クリックイベント登録 */
                imageButtons[i][j].setOnClickListener(this);

                gridLayout.addView(imageButtons[i][j]);
            }
        }
        fieldupdate();
    }
    public void onClick(View v) {

        /* imgbtnの名前から座標取得 */
        int point = v.getId();
        int y = point / 10;
        int x = point % 10;

        Player currentPlayer = master.getCurrentPlayer();

        if(currentPlayer.play(y,x)){
            if( currentPlayer.getNumberOfmy_get_pieces() >= WIN_NUMBER_OF_GETPECES ) {
                gameEnd(currentPlayer);
            }
            else{
                master.Nextturn();
            }
            fieldupdate();

        }
        else {
            fieldupdate(currentPlayer.getBattlefield());
        }
    }

    private void gameEnd(Player winner){

        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("ゲーム終了");
        alertDlg.setMessage(winner.toString() + "さんの勝利です\n" + "もう一度対戦しますか？");
        alertDlg.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK ボタンクリック処理
                    }
                });
        alertDlg.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Cancel ボタンクリック処理
                    }
                });

        alertDlg.show();

    }

    //表示更新
    private void fieldupdate() {

        Player currentplayer = master.getCurrentPlayer();
        Player nextplayer = master.getNextPlayer();

        Battlefield battlefield =currentplayer.getBattlefield();

        for (int i = 0; i < Hasamishogi_Const.FIELD_ROW; i++) {
            for (int j = 0; j < Hasamishogi_Const.FIELD_COLUMN; j++) {

                int checkstate = battlefield.getgridstate(i, j);

                imageButtons[i][j].setBackgroundResource(R.drawable.tree);

                if (checkstate == Grid.EMPTY) {
                    imageButtons[i][j].setImageBitmap(null);
                } else if (checkstate == Grid.CANPUT) {
                    imageButtons[i][j].setBackgroundColor(canputcolor);
                    imageButtons[i][j].setImageBitmap(null);
                } else {
                    Piece checkpiece = battlefield.getOnpiece(i, j);

                    if (battlefield.isgridslastmove(i, j)) {
                        imageButtons[i][j].setBackgroundColor(lastmovecolor);
                    }

                    imageButtons[i][j].setImageResource(checkpiece.getResid());

                    /* 敵の駒をMatrixを用いて画像を上下反転 */
                    if (checkstate == Grid.SECOND) { //反転処理がないとき
                    //if( checkstate == nextplayer.getMystate() ){
                        Bitmap flippedBmp = getFlipverticalBmp(imageButtons[i][j]);
                        imageButtons[i][j].setImageBitmap(flippedBmp);
                    }
                }
            }
        }
//        opponent_player_name.setText(nextplayer.toString());
//        my_player_name.setText(currentplayer.toString());

        setImageViews();
    }

    private void setImageViews(){

//        for (int i = 0; i < my_imageViews.length; i++) {
//            my_imageViews[i].setImageResource(0);
//        }
//        for (int i = 0; i < opponent_imageViews.length; i++) {
//            opponent_imageViews[i].setImageResource(0);
//        }
//        for (int i = 0; i < master.getCurrentPlayer().getNumberOfmy_get_pieces(); i++) {
//            my_imageViews[i].setImageResource(getImageresid(master.getCurrentPlayer().getMy_get_pieces(i)));
//        }
//        for (int i = 0; i < master.getNextPlayer().getNumberOfmy_get_pieces(); i++) {
//            opponent_imageViews[i].setImageResource(getImageresid(master.getNextPlayer().getMy_get_pieces(i)));
//            Bitmap flippedBmp = getFlipverticalBmp(opponent_imageViews[i]);
//            opponent_imageViews[i].setImageBitmap(flippedBmp);
//        }

        for (int i = 0; i < player1.getNumberOfmy_get_pieces(); i++) {
            my_imageViews[i].setImageResource(player1.getMy_get_pieces(i).getResid());
        }
        for (int i = 0; i < player2.getNumberOfmy_get_pieces(); i++) {
            opponent_imageViews[i].setImageResource(player2.getMy_get_pieces(i).getResid());
            Bitmap flippedBmp = getFlipverticalBmp(opponent_imageViews[i]);
            opponent_imageViews[i].setImageBitmap(flippedBmp);
        }

    }

    /**
     * 渡した画像を反転したBitmapを返す
     * @param imageView 引き渡す画像を持つビュー
     * @return 反転画像のBitmap
     */
    private Bitmap getFlipverticalBmp(ImageView imageView){
        BitmapDrawable bd = (BitmapDrawable) imageView.getDrawable();
        Bitmap bmp = bd.getBitmap();
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        Bitmap flippedBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
        return flippedBmp;
    }

    /**
     * フィールド表示を更新する
     * @param battlefield 表示更新するフィールド
     */
    private void fieldupdate(Battlefield battlefield){

        for(int  i = 0 ; i < Hasamishogi_Const.FIELD_ROW ; i++ ){
            for(int j = 0; j < Hasamishogi_Const.FIELD_COLUMN ; j++ ){

                if( !battlefield.isgridslastmove(i,j) ) {
                    if (battlefield.getgridstate(i, j) == Grid.CANPUT) {
                        imageButtons[i][j].setBackgroundColor(canputcolor);
                    } else {
                        imageButtons[i][j].setBackgroundResource(R.drawable.tree);
                    }
                }
            }
        }
    }
}
