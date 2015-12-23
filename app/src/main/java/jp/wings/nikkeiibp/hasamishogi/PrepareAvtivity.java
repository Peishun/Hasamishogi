package jp.wings.nikkeiibp.hasamishogi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrepareAvtivity extends AppCompatActivity {

    private static final int SAMPLE_ROW = 1;
    private static final int SAMPLE_COLMN = 7;
    private static final int SET_ROW = 1;

    private int canputcolor;

    /* マスとなるイメージボタン */
    private ImageButton[][] field_imageButtons;
    private ImageButton[] sample_imageButtons;

    /* Player名を表示するテキストビュー */
    private TextView player_nameText;

    private TextView piececostText;

    private TextView usecostText;

    private Piece havePiece;

    private Samplefield samplefield;

    private ImageView introducePieceIv;

    /* プレイヤー */
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        /* ツールバーの生成と表示 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        samplefield = new Samplefield(SAMPLE_ROW, SAMPLE_COLMN);
        samplefield.setting();

        Preparefield preparefield = new Preparefield(SET_ROW, Hasamishogi_Const.FIELD_COLUMN, Hasamishogi_Const.MAX_COST);

        player = new Player("Player1",Grid.FIRST ,preparefield);

        /* レイアウトの取得 */
        GridLayout gridLayout = (GridLayout) findViewById(R.id.prapare_gridlayout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sample_linnner_layout);

        player_nameText = (TextView) findViewById(R.id.playerName);
        player_nameText.setText(player.toString());

        piececostText = (TextView) findViewById(R.id.piececost);

        usecostText = (TextView) findViewById(R.id.usecost);

        introducePieceIv = (ImageView) findViewById(R.id.introducepiece);

        /* 色の取得 */
        canputcolor = getResources().getColor(R.color.palette_red_14);

        /* 生成したViewの定義 */
        field_imageButtons = new ImageButton[SET_ROW][Hasamishogi_Const.FIELD_COLUMN];

        View.OnClickListener field_imageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* imgbtnの名前から座標取得 */
                int point = v.getId();
                int y = point / 10;
                int x = point % 10;

                if (player.putPiece(y, x, havePiece)) {
                    fieldupdate();
                }
                else{
                    costOveralert();
                }
            }
        };

        /* 生成したViewのレイアウトパラメータの定義 */
        GridLayout.LayoutParams[][] grid_paramses = new GridLayout.LayoutParams[SET_ROW][Hasamishogi_Const.FIELD_COLUMN];
        LinearLayout.LayoutParams linear_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout[] linearLayouts = new LinearLayout[SAMPLE_COLMN];
        TextView[] piece_nameTexts = new TextView[SAMPLE_COLMN];
        TextView[] costTexts = new TextView[SAMPLE_COLMN];
        sample_imageButtons = new ImageButton[SAMPLE_COLMN];
        Button reset_Button = (Button)findViewById(R.id.resetButton);


        reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.getPreparefield().fieldreset();
                introducePieceIv.setImageResource(0);
                piececostText.setText(null);
                fieldupdate();
                samplefieldupdate();
            }
        });


        View.OnClickListener sample_imageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* imgbtnの名前から座標取得 */
                int point = v.getId();
                int x = point % 10;
                havePiece = samplefield.getOnpiece(SAMPLE_ROW - 1, x);
                introducePieceIv.setImageResource(havePiece.getResid());
                piececostText.setText("コスト" + havePiece.getCost());
                samplefield.selectedInit();
                samplefield.gridsselected(SAMPLE_ROW - 1,x);
                samplefieldupdate();
            }
        };

        for (int i = 0; i < SAMPLE_COLMN; i++) {

            linearLayouts[i] = new LinearLayout(this);

            piece_nameTexts[i] = new TextView(this);
            costTexts[i] = new TextView(this);
            sample_imageButtons[i] = new ImageButton(this);

            LinearLayout.LayoutParams sample_imageButtons_lp = new LinearLayout.LayoutParams(100, 100);
            sample_imageButtons_lp.setMargins(2, 2, 2, 2);
            sample_imageButtons[i].setLayoutParams(sample_imageButtons_lp);
            sample_imageButtons[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            sample_imageButtons[i].setImageResource(samplefield.getOnpiece(0, i).getResid());

             /* imgbtnに名前を付ける */
            sample_imageButtons[i].setId(i);
            /* クリックイベント登録 */
            sample_imageButtons[i].setOnClickListener(sample_imageButtonClickListener);

            linearLayouts[i].setOrientation(LinearLayout.VERTICAL);
            linearLayouts[i].addView(sample_imageButtons[i]);
            linearLayout.addView(linearLayouts[i]);
        }

        /* 取得した駒を置くイメージビューを配置する */
        for (int i = 0; i < SET_ROW ; i++) {

            for (int j = 0; j < Hasamishogi_Const.FIELD_COLUMN; j++) {

                field_imageButtons[i][j] = new ImageButton(this);
                grid_paramses[i][j] = new GridLayout.LayoutParams();

                /* レイアウトパラメータの設定 */
                grid_paramses[i][j].rowSpec = GridLayout.spec(i);
                grid_paramses[i][j].columnSpec = GridLayout.spec(j);
                grid_paramses[i][j].width = 100;
                grid_paramses[i][j].height = 100;
                grid_paramses[i][j].setMargins(2, 2, 2, 2);

                /* レイアウトパラメータを指定 */
                field_imageButtons[i][j].setLayoutParams(grid_paramses[i][j]);

                /* 画像表示形式の設定 */
                field_imageButtons[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);

                field_imageButtons[i][j].setBackgroundResource(R.drawable.tree);

                /* imgbtnに名前を付ける */
                field_imageButtons[i][j].setId(i * 10 + j);
                /* クリックイベント登録 */
                field_imageButtons[i][j].setOnClickListener(field_imageButtonClickListener);

                gridLayout.addView(field_imageButtons[i][j]);

            }
        }

        fieldupdate();
        samplefieldupdate();

    }

    private void costOveralert(){

        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setTitle("コストオーバーです");
        alertDlg.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // OK ボタンクリック処理
                    }
                });
        alertDlg.show();

    }

    //表示更新
    private void fieldupdate() {

        int useYpoint = SET_ROW - 1;

        for (int i = 0; i < Hasamishogi_Const.FIELD_COLUMN; i++) {

            int checkstate = player.getPreparefield().getgridstate(useYpoint, i);

            field_imageButtons[useYpoint][i].setBackgroundResource(R.color.palette_red_8);

            if (checkstate == Grid.EMPTY) {
                field_imageButtons[useYpoint][i].setImageBitmap(null);
            } else {
                Piece checkpiece = player.getPreparefield().getOnpiece(useYpoint, i);
                field_imageButtons[useYpoint][i].setImageResource(checkpiece.getResid());
            }
        }
        usecostText.setText(player.getPreparefield().getHavecost() + "/" + Hasamishogi_Const.MAX_COST);
    }

    private void samplefieldupdate() {

        int useYpoint = SAMPLE_ROW - 1;

        for (int i = 0; i < SAMPLE_COLMN; i++) {
            if(samplefield.getisgridsselected(useYpoint,i)) {
                sample_imageButtons[i].setBackgroundResource(R.color.palette_yellow_7);
            }
            else {
                sample_imageButtons[i].setBackgroundResource(R.drawable.tree);
            }
        }
    }
}