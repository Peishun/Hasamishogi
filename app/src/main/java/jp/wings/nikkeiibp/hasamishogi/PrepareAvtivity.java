package jp.wings.nikkeiibp.hasamishogi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrepareAvtivity extends AppCompatActivity {

    private static final int SAMPLE_ROW = 1;
    private static final int SAMPLE_COLMN = 7;

    private int canputcolor;

    /* マスとなるイメージボタン */
    private ImageButton[][] field_imageButtons;

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

        Preparefield preparefield = new Preparefield(Hasamishogi_Const.FIELD_ROW, Hasamishogi_Const.FIELD_COLUMN, Hasamishogi_Const.MAX_COST);

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
        field_imageButtons = new ImageButton[Hasamishogi_Const.FIELD_ROW][Hasamishogi_Const.FIELD_COLUMN];

        View.OnClickListener field_imageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* imgbtnの名前から座標取得 */
                int point = v.getId();
                int y = point / 10;
                int x = point % 10;

                if (player.putPiece(y, x, havePiece)) {
                    havePiece = null;
                    putPiecefieldupdate();
                }
            }
        };

        /* 生成したViewのレイアウトパラメータの定義 */
        GridLayout.LayoutParams[][] grid_paramses = new GridLayout.LayoutParams[Hasamishogi_Const.FIELD_ROW][Hasamishogi_Const.FIELD_COLUMN];
        LinearLayout.LayoutParams linear_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout[] linearLayouts = new LinearLayout[SAMPLE_COLMN];
        TextView[] piece_nameTexts = new TextView[SAMPLE_COLMN];
        final TextView[] costTexts = new TextView[SAMPLE_COLMN];
        ImageButton[] sample_imageButtons = new ImageButton[SAMPLE_COLMN];

        View.OnClickListener sample_imageButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* imgbtnの名前から座標取得 */
                int point = v.getId();
                int x = point % 10;
                havePiece = samplefield.getOnpiece(0, x);
                introducePieceIv.setImageResource(havePiece.getResid());
                piececostText.setText("コスト" + havePiece.getCost());
                selectPiecefieldupdate();
            }
        };

        for (int k = 0; k < SAMPLE_COLMN; k++) {

            linearLayouts[k] = new LinearLayout(this);

            piece_nameTexts[k] = new TextView(this);
            costTexts[k] = new TextView(this);
            sample_imageButtons[k] = new ImageButton(this);

            LinearLayout.LayoutParams sample_imageButtons_lp = new LinearLayout.LayoutParams(100, 100);
            sample_imageButtons_lp.setMargins(2, 2, 2, 2);
            sample_imageButtons[k].setLayoutParams(sample_imageButtons_lp);
            sample_imageButtons[k].setBackgroundResource(R.drawable.tree);
            sample_imageButtons[k].setScaleType(ImageView.ScaleType.CENTER_CROP);
            sample_imageButtons[k].setImageResource(samplefield.getOnpiece(0, k).getResid());


             /* imgbtnに名前を付ける */
            sample_imageButtons[k].setId(k);
                    /* クリックイベント登録 */
            sample_imageButtons[k].setOnClickListener(sample_imageButtonClickListener);

            //piece_nameTexts[k].setLayoutParams(linear_params);
            //piece_nameTexts[k].setText(samplefield.getOnpiece(0, k).getClass().getSimpleName());

            //costTexts[k].setLayoutParams(linear_params);
            //costTexts[k].setText(String.valueOf(samplefield.getOnpiece(0, k).getCost()));

            linearLayouts[k].setOrientation(LinearLayout.VERTICAL);
            linearLayouts[k].addView(sample_imageButtons[k]);
            //linearLayouts[k].addView(piece_nameTexts[k]);
            //linearLayouts[k].addView(costTexts[k]);
            linearLayout.addView(linearLayouts[k]);
        }

        /* 取得した駒を置くイメージビューを配置する */
        for (int i = 0; i < Hasamishogi_Const.FIELD_ROW ; i++) {

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

        putPiecefieldupdate();

    }

    //表示更新
    private void selectPiecefieldupdate() {

        int useYpoint = Hasamishogi_Const.FIELD_ROW - 1;

        for (int j = 0; j < Hasamishogi_Const.FIELD_COLUMN; j++) {
            field_imageButtons[useYpoint][j].setBackgroundResource(R.color.palette_red_8);
        }
    }

    //表示更新
    private void putPiecefieldupdate() {

        int useYpoint = Hasamishogi_Const.FIELD_ROW - 1;

        for (int j = 0; j < Hasamishogi_Const.FIELD_COLUMN; j++) {

            int checkstate = player.getPreparefield().getgridstate(useYpoint, j);

            field_imageButtons[useYpoint][j].setBackgroundResource(R.color.palette_red_8);

            if (checkstate == Grid.EMPTY) {
                field_imageButtons[useYpoint][j].setImageBitmap(null);
            } else {
                Piece checkpiece = player.getPreparefield().getOnpiece(useYpoint, j);
                field_imageButtons[useYpoint][j].setImageResource(checkpiece.getResid());
            }
        }
        usecostText.setText(player.getPreparefield().getHavecost() + "/" + Hasamishogi_Const.MAX_COST);
    }
}