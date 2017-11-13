package com.tudoujf.activity.test;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.test.GuessBean;
import com.tudoujf.test.ScoreBean;
import com.tudoujf.ui.mytest.GameView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * * ================================================
 * name:            GuessHappyActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/11
 * description：猜猜乐activity
 * history：
 * ===================================================
 */

public class GuessHappyActivity extends BaseActivity {
    @BindView(R.id.gv_act_test)
    GameView gvActTest;
    @BindView(R.id.tv_act_test_red_current_frequence)
    TextView tvRedCurrentFrequence;
    @BindView(R.id.tv_act_test_blue_current_frequence)
    TextView tvBlueCurrentFrequence;
    @BindView(R.id.tv_act_test_yellow_current_frequence)
    TextView tvYellowCurrentFrequence;
    @BindView(R.id.tv_act_test_green_current_frequence)
    TextView tvGreenCurrentFrequence;
    @BindView(R.id.tv_act_test_red)
    TextView tvRed;
    @BindView(R.id.tv_act_test_blue)
    TextView tvBlue;
    @BindView(R.id.tv_act_test_yellow)
    TextView tvYellow;
    @BindView(R.id.tv_act_test_green)
    TextView tvGreen;
    @BindView(R.id.tv_act_test_red_amount)
    TextView tvRedAmount;
    @BindView(R.id.tv_act_test_blue_amount)
    TextView tvBlueAmount;
    @BindView(R.id.tv_act_test_yellow_amount)
    TextView tvYellowAmount;
    @BindView(R.id.tv_act_test_green_amount)
    TextView tvGreenAmount;
    @BindView(R.id.tv_act_test_add1)
    TextView tvAdd1;
    @BindView(R.id.tv_act_test_add2)
    TextView tvAdd2;
    @BindView(R.id.tv_act_test_add3)
    TextView tvAdd3;
    @BindView(R.id.tv_act_test_add4)
    TextView tvAdd4;
    @BindView(R.id.tv_act_test_minus1)
    TextView tvMinus1;
    @BindView(R.id.tv_act_test_minus2)
    TextView tvMinus2;
    @BindView(R.id.tv_act_test_minus3)
    TextView tvMinus3;
    @BindView(R.id.tv_act_test_minus4)
    TextView tvMinus4;
    @BindView(R.id.tv_act_test_result)
    TextView tvResult;
    @BindView(R.id.tv_act_test_total)
    TextView tvtotal;

    /**
     * 当前选择的类型
     */
    private int type = 0;
    /**
     * 默认金额
     */
    private int redAmout = 0, yellowAmount = 0, blueAmount = 0, greenAmount = 0, total = 1000000;
    /**
     * 按钮互斥集合
     */
    private List<TextView> list;
    private android.support.v7.app.AlertDialog dialog;

    private TextView confirm;
    private EditText user;
    private String name;
    private GuessBean bean;

    private int count = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.act_guesshappy;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_act_test_add1:
                add(1000);
                break;
            case R.id.tv_act_test_add2:
                add(5000);
                break;
            case R.id.tv_act_test_add3:
                add(10000);
                break;
            case R.id.tv_act_test_add4:
                add(100000);
                break;
            case R.id.tv_act_test_minus1:
                minus(1000);
                break;
            case R.id.tv_act_test_minus2:
                minus(5000);
                break;
            case R.id.tv_act_test_minus3:
                minus(10000);
                break;
            case R.id.tv_act_test_minus4:
                minus(100000);
                break;
            case R.id.tv_act_test_red:
                type = 1;
                setButStyle();
                break;
            case R.id.tv_act_test_blue:
                type = 2;
                setButStyle();
                break;
            case R.id.tv_act_test_yellow:
                type = 3;
                setButStyle();
                break;
            case R.id.tv_act_test_green:
                type = 4;
                setButStyle();
                break;
            case R.id.tv_dialog_user_queding://dialog确定
                name = user.getText().toString().trim();
                if (name.contains("-")) {
                    String[] te = name.split("-");
                    if (te.length==2){
                        name = te[0];
                        int sleepTime = StringUtils.string2Integer(te[1]);
                        if (sleepTime != 0) {
                            gvActTest.setSleepTime(sleepTime);
                        }

                        Log.e("TAG", "onClick: name-----" + name);
                    }


                }
                if (name.equals("")) {
                    ToastUtils.showToast(GuessHappyActivity.this, R.string.qingshuruyonghuming);
                } else {
                    List<GuessBean> list = DataSupport.where("userName = ?", name).find(GuessBean.class);
                    if (list.size() > 0) {
                        bean = list.get(0);
                        total = bean.getAmount();
                    } else {
                        bean = new GuessBean();
                        bean.setUserName(name);
                        bean.setAmount(1000000);
                        bean.save();
                        total = 1000000;
                    }
                    tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));
                    gvActTest.setThreadFlag(true);
                    dialog.dismiss();
                    ToastUtils.showToast(GuessHappyActivity.this, R.string.youxikaishi);
                }
                break;
            case R.id.tv_act_test_total:
                if (total == 0 && count == 10) {
                    total = 1000000;
                    tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));
                }
                count++;
                break;

        }

    }

    private void add(int num) {
        if (addBet(num)) {
            showBet(num);
        } else {
            ToastUtils.showToast(GuessHappyActivity.this, R.string.yuebuzu);
        }
    }

    private void minus(int num) {
        if (minusBet(num)) {
            showBetMinus(num);
        } else {
            ToastUtils.showToast(GuessHappyActivity.this, R.string.yuebuzu);
        }
    }

    private boolean addBet(int num) {
        return total - num >= 0;
    }

    private boolean minusBet(int num) {
        switch (type) {
            case 0:
                ToastUtils.showToast(GuessHappyActivity.this, R.string.qingxuanzeyaoxiazhudeleixing);
                break;
            case 1://红
                return redAmout - num >= 0;
            case 2://蓝
                return blueAmount - num >= 0;
            case 3://黄
                return yellowAmount - num >= 0;
            case 4://绿
                return greenAmount - num >= 0;
        }
        return false;
    }

    private void showBetMinus(int num) {
        switch (type) {

            case 1://红
                redAmout = redAmout - num;
                total = total + num;
                tvRedAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + redAmout));
                break;
            case 2://蓝
                blueAmount = blueAmount - num;
                total = total + num;
                tvBlueAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + blueAmount));
                break;
            case 3://黄
                yellowAmount = yellowAmount - num;
                total = total + num;
                tvYellowAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + yellowAmount));
                break;
            case 4://绿
                greenAmount = greenAmount - num;
                total = total + num;
                tvGreenAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + greenAmount));
                break;
        }
        tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));

    }


    private void showBet(int num) {
        switch (type) {
            case 0:
                ToastUtils.showToast(GuessHappyActivity.this, R.string.qingxuanzeyaoxiazhudeleixing);
                break;
            case 1://红
                redAmout = redAmout + num;
                total = total - num;
                tvRedAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + redAmout));
                break;
            case 2://蓝
                blueAmount = blueAmount + num;
                total = total - num;
                tvBlueAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + blueAmount));
                break;
            case 3://黄
                yellowAmount = yellowAmount + num;
                total = total - num;
                tvYellowAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + yellowAmount));
                break;
            case 4://绿
                greenAmount = greenAmount + num;
                total = total - num;
                tvGreenAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + greenAmount));
                break;
        }
        tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));

    }

    private void setButStyle() {
        for (int i = 0; i < list.size(); i++) {
            if (type == i + 1) {
                list.get(i).setText(R.string.yixuanzhong);
            } else {
                list.get(i).setText(R.string.weixuanzhong);
            }
        }

    }

    @Override
    public void initDataFromIntent() {

        showDialog();
        list = new ArrayList<>();
        list.add(tvRed);
        list.add(tvBlue);
        list.add(tvYellow);
        list.add(tvGreen);

    }

    @Override
    public void initView() {

        tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));

        showAmount();

        tvRed.setText(R.string.weixuanzhong);
        tvYellow.setText(R.string.weixuanzhong);
        tvBlue.setText(R.string.weixuanzhong);
        tvGreen.setText(R.string.weixuanzhong);
    }

    @Override
    public void initListener() {

        gvActTest.setListener(new GameView.OnResult() {
            @Override
            public void onResult(int flag) {
                int earings = 0;
                switch (flag) {
                    case 1://50
                        tvResult.setText((getResources().getString(R.string.shangcizhongjiangjieguoshi) +
                                getResources().getString(R.string.hongse)));
                        earings = redAmout * 50;
                        total = total + redAmout * 50;
                        break;
                    case 2://6
                        tvResult.setText((getResources().getString(R.string.shangcizhongjiangjieguoshi) +
                                getResources().getString(R.string.huangse)));
                        earings = yellowAmount * 6;
                        total = total + yellowAmount * 6;
                        break;
                    case 3://3
                        tvResult.setText((getResources().getString(R.string.shangcizhongjiangjieguoshi) +
                                getResources().getString(R.string.lanse)));
                        earings = blueAmount * 3;
                        total = total + blueAmount * 3;
                        break;
                    case 4://2
                        tvResult.setText((getResources().getString(R.string.shangcizhongjiangjieguoshi) +
                                getResources().getString(R.string.lvse)));
                        earings = greenAmount * 2;
                        total = total + greenAmount * 2;
                        break;
                }

                Log.e("TAG", "onResult: -----回调已执行");

                ToastUtils.showToast(GuessHappyActivity.this, getResources().getString(R.string.bencizhongjiangjinewei) + earings);
                ToastUtils.showToast(GuessHappyActivity.this, R.string.youxikaishi);
                tvtotal.setText((getResources().getString(R.string.shengyuzonge) + StringUtils.getCommaDecimalsStrZeroDot("" + total)));
                redAmout = 0;
                yellowAmount = 0;
                greenAmount = 0;
                blueAmount = 0;
                showAmount();


            }

            @Override
            public void oneFinish(int redCount, int blueCount, int yellowCount, int greenCount) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(GuessHappyActivity.this, R.string.jijiangkaiqixiayilun);
                    }
                });

                ScoreBean bean = new ScoreBean();
                bean.setBlue(blueCount);
                bean.setRed(redCount);
                bean.setYellow(yellowCount);
                bean.setGreen(greenCount);
                bean.save();
                Log.e("TAG", "oneFinish: ----shdsjdchsjkdhsjd-" + redCount);
                Log.e("TAG", "oneFinish: ----shdsjdchsjkdhsjd-" + blueCount);
                Log.e("TAG", "oneFinish: ----shdsjdchsjkdhsjd-" + yellowCount);
                Log.e("TAG", "oneFinish: ----shdsjdchsjkdhsjd-" + greenCount);

            }

            @Override
            public void resultCount(int redCount, int blueCount, int yellowCount, int greenCount) {
                showFrequence(redCount, blueCount, yellowCount, greenCount);

            }
        });

        tvAdd1.setOnClickListener(this);
        tvAdd2.setOnClickListener(this);
        tvAdd3.setOnClickListener(this);
        tvAdd4.setOnClickListener(this);

        tvMinus1.setOnClickListener(this);
        tvMinus2.setOnClickListener(this);
        tvMinus3.setOnClickListener(this);
        tvMinus4.setOnClickListener(this);


        tvRed.setOnClickListener(this);
        tvYellow.setOnClickListener(this);
        tvBlue.setOnClickListener(this);
        tvGreen.setOnClickListener(this);

        tvtotal.setOnClickListener(this);

    }

    /**
     * 本期开出的次数
     */
    private void showFrequence(int redCount, int blueCount, int yellowCount, int greenCount) {

        tvRedCurrentFrequence.setText((getResources().getString(R.string.benqikaichucishu) + redCount));
        tvBlueCurrentFrequence.setText((getResources().getString(R.string.benqikaichucishu) + blueCount));
        tvYellowCurrentFrequence.setText((getResources().getString(R.string.benqikaichucishu) + yellowCount));
        tvGreenCurrentFrequence.setText((getResources().getString(R.string.benqikaichucishu) + greenCount));
    }

    /**
     * 展示当前投注金额
     */
    private void showAmount() {
        tvRedAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + redAmout));
        tvYellowAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + yellowAmount));
        tvBlueAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + blueAmount));
        tvGreenAmount.setText((getResources().getString(R.string.dangqiantouzhuedushi) + greenAmount));
    }

    @Override
    public void initDataFromInternet() {

    }

    @Override
    public void LoadInternetDataToUi() {

    }

    @Override
    public void onBackPressed() {
        gvActTest.setThreadFlag(false);
        bean.setAmount(total);
        bean.save();
        finish();
    }


    private void showDialog() {
        if (dialog == null) {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.dialog_act_user, null);
            dialog = DialogUtils.showUserDialog(this, getResources().getString(R.string.zaicidianjijinagtuichugaiyemian), view);
            confirm = view.findViewById(R.id.tv_dialog_user_queding);
            user = view.findViewById(R.id.et_dialog_user_name);
            confirm.setOnClickListener(this);
        } else {
            dialog.show();
        }
    }


    //    @BindView(R.id.tv_dialog_canlendar_starttime)
//    TextView tvStartTime;
//    @BindView(R.id.tv_dialog_canlendar_endtime)
//    TextView tvEndTime;
//    @BindView(R.id.tv_dialog_canlendar_previous)
//    TextView tvPrevious;
//    @BindView(R.id.tv_dialog_canlendar_currenttime)
//    TextView tvCurrentTime;
//    @BindView(R.id.tv_dialog_canlendar_next)
//    TextView tvNext;
//    @BindView(R.id.ll_dialog_canlendar_current)
//    LinearLayout llDialogCanlendarCurrent;
//    @BindView(R.id.rv_dialog_canlendar)
//    ViewPager viewPager;
//    private List<CalendarView>  listData;
//    private List<DayBean> monthList;
//
//
//
//    /**
//     * 当前点击按钮标识
//     */
//    private int buttonFlag = 1;
//    private String TAG="TestActivity";
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.act_test;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_dialog_canlendar_starttime://开始日期
//                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_left);
//                buttonFlag = 1;
//                break;
//            case R.id.tv_dialog_canlendar_endtime://结束日期
//                tvCurrentTime.setBackgroundResource(R.drawable.dialog_canlendarsel_tableheader_right);
//                buttonFlag = 2;
//                break;
//            case R.id.tv_dialog_canlendar_previous://上一个月
//                if (viewPager.getCurrentItem()>0){
//                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
//                }else {
//                    ToastUtils.showToast(this,"没有更多了!!");
//                }
//                break;
//            case R.id.tv_dialog_canlendar_next://下一个月
//                if (viewPager.getCurrentItem()<listData.size()-1){
//                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
//                }else{
//                    ToastUtils.showToast(this,"没有更多了!!");
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void initDataFromIntent() {
//        initDataList();
//        listData=new ArrayList<>();
//        for (int i = 0; i < monthList.size(); i++) {
//            CalendarView view=new CalendarView(this,monthList.get(i).getYear(),monthList.get(i).getMonth());
//            view.setListener(new CalendarView.SelDayListener() {
//                @Override
//                public void onClick(String dayStr, String yearMonthStr) {
//                    if (buttonFlag==1){
//                        tvStartTime.setText(dayStr);
//                    }else {
//                        tvEndTime.setText(dayStr);
//                    }
//                }
//            });
//            listData.add(view);
//        }
//
//        //测试aes加密
//
//        String  mingwen="12331";
//        String  miwen= AESUtils.encrypt("123456",mingwen);
//        String  jiemi=AESUtils.decrypt("123456",miwen);
//
//        Log.e(TAG, "initDataFromIntent: ----------密文---------"+miwen );
//        Log.e(TAG, "initDataFromIntent: ----------明文---------"+jiemi );
//
//
//    }
//
//    @Override
//    public void initView() {
//        CalendarVPAdapter adapter=new CalendarVPAdapter(listData);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(listData.size()-1);
//        tvCurrentTime.setText(listData.get(listData.size()-1).getTitle());
//
//        tvStartTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
//        tvEndTime.setText(TimeUtils.getNowDateShort());
//
//    }
//
//    @Override
//    public void initListener() {
//
//
//
//        tvStartTime.setOnClickListener(this);
//        tvEndTime.setOnClickListener(this);
//        tvPrevious.setOnClickListener(this);
//        tvNext.setOnClickListener(this);
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tvCurrentTime.setText(listData.get(position).getTitle());
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public void initDataFromInternet() {
//
//    }
//
//    @Override
//    public void LoadInternetDataToUi() {
//
//    }
//
//    private void initDataList(){
//         monthList=new ArrayList<>();
//        int  currentYear=TimeUtils.getCurrentYear();
//        int  currentMonth=TimeUtils.getCurrentMonthInt();
//        int  monthCount=(currentYear-2016)*12+currentMonth;
//        for (int i = 0; i < currentYear-2016; i++) {
//            for (int j = 0; j < 12; j++) {
//                DayBean bean=new DayBean();
//                bean.setYear(2016+i);
//                bean.setMonth(j+1);
//                monthList.add(bean);
//            }
//        }
//        for (int i = 0; i < currentMonth; i++) {
//            DayBean bean=new DayBean();
//            bean.setYear(currentYear);
//            bean.setMonth(i+1);
//            monthList.add(bean);
//        }
//
//
//    }
//
//    private class DayBean{
//        private int  year;
//        private int  month;
//
//        public int getYear() {
//            return year;
//        }
//
//        public void setYear(int year) {
//            this.year = year;
//        }
//
//        public int getMonth() {
//            return month;
//        }
//
//        public void setMonth(int month) {
//            this.month = month;
//        }
//    }
//
//
//    private class CalendarVPAdapter extends PagerAdapter {
//
//        private List<CalendarView> list;
//
//        public CalendarVPAdapter(List<CalendarView> list) {
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        /**
//         * 实例化 一个 页卡
//         */
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            // 添加一个 页卡
//
//            container.addView(list.get(position));
//
//            return list.get(position);
//        }
//
//        /**
//         * 销毁 一个 页卡
//         */
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            // 删除
//            container.removeView(list.get(position));
//        }
//
//
//
//    }

}
