package com.tudoujf.activity.discover;

import android.view.View;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.ui.LuckyLotteryLayout;
import com.tudoujf.ui.SudokuView;

import butterknife.BindView;

/**
 * * ===============================================================
 * name:             LuckyLotteryActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：             2017/11/14
 * description：  幸运抽奖activity
 * history：
 * *==================================================================
 */
public class LuckyLotteryActivity extends BaseActivity {
    @BindView(R.id.lll_act_luckylottery)
    LuckyLotteryLayout lllActLuckylottery;
    @BindView(R.id.sv_act_luckylottery)
    SudokuView sudokuView;
    @BindView(R.id.sv_act_luckylottery_shade)
    SudokuView sudokuViewShade;

    @Override
    public int getLayoutResId() {
        return R.layout.act_luckylottery;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initDataFromIntent() {

    }

    @Override
    public void initView() {
        sudokuView.setInitBitmap(true);
    }

    @Override
    public void initListener() {
        sudokuView.setListener(new SudokuView.ClickEvent() {
            @Override
            public void finishSel() {

            }

            @Override
            public void startSel() {
                sudokuViewShade.setShade(true);

            }
        });

        sudokuViewShade.setListener(new SudokuView.ClickEvent() {
            @Override
            public void finishSel() {
                sudokuView.setCanClick(true);
            }

            @Override
            public void startSel() {

            }
        });

    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        lllActLuckylottery.setDotGlint(false);
    }
}
