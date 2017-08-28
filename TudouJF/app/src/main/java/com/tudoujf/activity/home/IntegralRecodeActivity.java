package com.tudoujf.activity.home;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.tudoujf.R;
import com.tudoujf.adapter.IntegralRecodeActLvAdapter;
import com.tudoujf.base.BaseActivity;
import com.tudoujf.base.BaseBean;
import com.tudoujf.bean.databean.IntegralRecodeBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.CalendarDialog;
import com.tudoujf.ui.CalendarDialogScroll;
import com.tudoujf.ui.MTopBarView;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.ScreenSizeUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.TimeUtils;
import com.tudoujf.utils.ToastUtils;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            IntegralRecodeActivity
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/17
 * description：     签到-->我的积分-->积分记录activity
 * history：
 * * ====================================================================
 */

public class IntegralRecodeActivity extends BaseActivity {
    @BindView(R.id.mtb_act_integralrecode)
    MTopBarView mtbIntegralRecode;
    @BindView(R.id.tv_act_integralrecode_totalintegral)
    TextView tvTotalintegral;//总积分
     @BindView(R.id.tv_act_integralrecode_integralrecode)
    TextView intefralRecode;//积分记录
    @BindView(R.id.iv_act_integralrecode_filtrate)
    ImageView ivFiltrate;//筛选按钮
    @BindView(R.id.lv_act_integralrecode)
    ListView lvIntegralRecode;
    @BindView(R.id.srl_fact_integralrecode)
    SmartRefreshLayout srlIntegralRecode;
    private String TAG = "IntegralRecodeActivity";

    /**
     * 返回的请求数据databean
     */
    private IntegralRecodeBean bean;
    /**
     * 总积分
     */
    private String totalIntegral;
    /**
     * 积分记录数据集
     */
    private List<IntegralRecodeBean.ItemsBean> list;
    /**加载进度dialog,选择筛选时间dialog*/
    private AlertDialog dialog,timeSelDialog;
    /**
     * 加载显示积分页数
     */
    private int page = 1;
    /**参数--start_time*/
    private String  paramsStartTime="";
    /**参数---end_time*/
    private String paramsEndTime="";
    /**
     * listview适配器
     */
    private IntegralRecodeActLvAdapter adapter;
    /**
     * 自定义dialog的展示view
     */
    private View view;
    /**
     * 自定义dialog的展示view的控件
     */
    private TextView startTime, endTime, cancel, confirm;
    /**日历dialog*/
//    private CalendarDialog calendarDialog;
    private CalendarDialogScroll calendarDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.act_integralrecode;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_act_integralrecode_filtrate://时间筛选按钮
                if (timeSelDialog==null){
                    timeSelDialog=DialogUtils.showTimeSel(this, "", view);
                }else {
                    timeSelDialog.show();
                }

                break;
            case R.id.tv_dialog_starttime://dialog中选择开始时间
                if (calendarDialog==null){
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog=new CalendarDialogScroll(this);
                }
                calendarDialog.showDialog();
                break;
            case R.id.tv_dialog_endtime://dialog中选择结束时间
                if (calendarDialog==null){
//                    calendarDialog=new CalendarDialog(this);
                    calendarDialog=new CalendarDialogScroll(this);
                }
                calendarDialog.showDialog();
                break;
            case R.id.tv_dialog_cancel://dialog中取消选择
                timeSelDialog.dismiss();
                break;
            case R.id.tv_dialog_confirm://dialog中确认选择
                dialog.show();
                String date1=startTime.getText().toString();
                String date2=endTime.getText().toString();
                if (TimeUtils.compareDate(date1,date2)){
                    paramsStartTime=date2;
                    paramsEndTime=date1;
                }else {
                    paramsStartTime=date1;
                    paramsEndTime=date2;
                }
                page=1;
                initDataFromInternet();
                Log.e(TAG, "onClick:-----------true,第一个日期较大---------------- "+TimeUtils.compareDate(date1,date2) );
                timeSelDialog.dismiss();

                // TODO: 2017/8/21 根据选择的时间进行条件查询展示

                break;
            case R.id.tv_act_integralrecode_integralrecode:
                paramsStartTime="";
                paramsEndTime="";
                page=1;
                dialog.show();
                initDataFromInternet();
                break;
        }

    }

    @Override
    public void initDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalIntegral = bundle.getString("totalIntegral");
        }
    }

    @Override
    public void initView() {
        /**设置沉浸式状态栏*/
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mtbIntegralRecode.getLayoutParams();
        params.setMargins(0, ScreenSizeUtils.getStatusHeight(this), 0, 0);
        mtbIntegralRecode.setLayoutParams(params);
        tvTotalintegral.setText(totalIntegral);


        //设置全区背景色
        srlIntegralRecode.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
        srlIntegralRecode.setEnableRefresh(false);
        //设置 Footer 为 球脉冲
        srlIntegralRecode.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        dialog = DialogUtils.showProgreessDialog(this, "再次点击将退出该页面!");

        view = LayoutInflater.from(this).inflate(R.layout.dialog_act_integralrecode_timesel, null);

        startTime = (TextView) view.findViewById(R.id.tv_dialog_starttime);
        endTime = (TextView) view.findViewById(R.id.tv_dialog_endtime);
        cancel = (TextView) view.findViewById(R.id.tv_dialog_cancel);
        confirm = (TextView) view.findViewById(R.id.tv_dialog_confirm);

        startTime.setText(TimeUtils.getCurrentFirstOfTheMonteh());
        endTime.setText(TimeUtils.getNowDateShort());

//        calendarDialog=new CalendarDialog(this);
        calendarDialog=new CalendarDialogScroll(this);

    }

    @Override
    public void initListener() {
        mtbIntegralRecode.getLeftTV().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        srlIntegralRecode.setOnLoadmoreListener(new OnLoadmoreListener() {//加载更多
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (bean != null && page < bean.getTotal_pages() + 1) {
                    page = page + 1;
                    initDataFromInternet();//再次请求数据
                } else {
                    srlIntegralRecode.finishLoadmore();
                    ToastUtils.showToast(IntegralRecodeActivity.this, R.string.meiyougengduojilula);
                }

            }
        });
        //非滑动日历
//        calendarDialog.setListener(new CalendarDialog.OnCalendarDialogDismissListener() {
//
//            @Override
//            public void onDismiss(String sTime, String eTime) {
//                startTime.setText(sTime);
//                endTime.setText(eTime);
//            }
//        });
        //滑动日历
        calendarDialog.setListener(new CalendarDialogScroll.OnCalendarDialogDismissListener() {
            @Override
            public void onDismiss(String sTime, String eTime) {
                startTime.setText(sTime);
                endTime.setText(eTime);
            }
        });

        ivFiltrate.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        intefralRecode.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {
        TreeMap<String, String> map = new TreeMap<>();

        map.put("login_token", UserConfig.getInstance().getLoginToken(this));
        Log.e(TAG, "initDataFromInternet: ------获取的logintoken-----------"+UserConfig.getInstance().getLoginToken(this) );
//        map.put("login_token", "12267");
        map.put("start_time", paramsStartTime);
        map.put("end_time", paramsEndTime);
        map.put("page_count", "");//待删除字段
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(this, Constants.INTEGRAL_LIST, map, "IntegralRecodeActivity", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = StringUtils.getDecodeString(response.body());
                Log.e(TAG, "onSuccess: ----------消息接口请求返回数据-----------------" + result);
                BaseBean bean1 = ParseJson.getJsonResult(response.body(), new TypeToken<IntegralRecodeBean>() {
                        }.getType(),IntegralRecodeBean.class, IntegralRecodeActivity.this);

                if (page == 1 && bean1 != null) {
                    dialog.dismiss();
                    bean = (IntegralRecodeBean) bean1;
                    LoadInternetDataToUi();
                } else if (page > 1 && bean1 != null) {
                    srlIntegralRecode.finishLoadmore();
                    bean = (IntegralRecodeBean) bean1;
                    LoadInternetDataToUi();
                }
            }

            @Override
            public void onError(Response<String> response) {
                Log.e("TAG", "onSuccess: ----------消息接口请求返回错误信息-----------------" + response.message());
                dialog.dismiss();
                super.onError(response);
            }
        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {
            if (page == 1 && bean.getItems() != null && bean.getItems().size() != 0) {
                list = bean.getItems();
                adapter = new IntegralRecodeActLvAdapter(list, this);
                lvIntegralRecode.setAdapter(adapter);
            } else if (page > 1 && bean.getItems() != null) {
                list.addAll(bean.getItems());
                adapter.notifyDataSetChanged();
            } else {
                ToastUtils.showToast(this, "当前没有要显示的积分记录");
            }
        }
    }

    @Override
    protected int setStatusBarColor() {
        return getResources().getColor(R.color.global_theme_background_color);
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }


}
/**
 *

 每当，聊起，程序员的中年危机时，任何人都是有感觉的。就算你一月3w的收入，在一线城市，活的很累。出去开支，
 几乎，没有什么剩了，别人看起来羡慕的样子，内心活在这种无形压力中。再加上工作强度大，自然每天都是紧绷的状态。
 周六，还得抓紧学习新东西。羡慕身边年轻小伙，刚毕业的，多幸福。

 这就是围城，程序员把自己围住在家庭，房子，工作，年龄的空间里，在没有去城里时，羡慕城里人的爽。进入城里后，
 定下来后，开始想念城外的日子。和学生时候，想长大，长大可以赚钱一样，长大后，想还是小孩子多好，不用辛苦工作。可以好好耍。

 天下只有两种人。比如一串葡萄到手，一种人挑最好的先吃，另一种人把最好的留到最后吃。照例第一种人应该乐观，
 因为他每吃一颗都是吃剩的葡萄里最好的；第二种人应该悲观，因为他每吃一颗都是吃剩的葡萄里最坏的。
 不过事实却适得其反，缘故是第二种人还有希望，第一种人只有回忆。

 3
 是不是，可以做点什么？
 无论什么境况，每个人都有自己的压力，倒是与职业无关。人之所以迷茫：在于你缺乏方向；更在于你坚信的东西有一天你不信了。
 人之所以固执：在于视野狭隘，世界很大你见识很少。所以人生一世，无论什么境遇都不重要，重要是的是你的思想，
 你的精神。人是能动性的。『自助者天助，自弃者天弃』。当你坚定一心，至死不渝，你所想所思，时间过后，
 你一定会成为你想成为的人。当然不是努力就有收获，不是你一想就能成为你想成为的人。这个世界还有很多的规律-『道』，
 世界是唯物的。你遵循他们找到趋势，你就能称心如意；违背他们，就会事倍功半，甚至被老天爷收回去。公司不会等你成长。培养人的耐心也很有限。后面看见太多公司招人 ，开除人，理由不一，但是归根接地道理是一样的，你的事情可以被量化替代，看不到你未来的贡献。我们只有找准自己的定位，才能持续发展下去。例如，我们可以这样：

 1、不断提高自己的开发能力与水平程序员最强的核心竞争力自然就在于技术能力，相比其他方面这也是最容易提高的。技术不再局限于什么语言开发。而是什么样的开发思想，什么样的合理性架构。

 2、不断提高自己的管理能力对于大部分程序员来说，做管理是比做技术更难过的一道槛。在中国人的悠久文化中，做经理、带团队能做久做稳的，必然是“人精”才行。『贤人君子，明于盛衰之道，通乎成败之数，审乎治乱之势，达乎去就之理。故潜居抱道，以待其时。若时至而行，则能极人臣之位；得机而动，则能成绝代之功。如其不遇，没身而已。是以其道足高，而名重于后代。』这是素书里面说著述。表示我们要看清这个行业未来几年的趋势，有人说Android未来怎样？不敢说Android生态不行，但是人工智能、AI、机器学习、大数据，这些定然是未来时代发展的特点。怎么看，看看大厂都在投入大量研发和资金在干什么就知道了？机器学习，现在研究生出来，真的是香饽饽，毫不夸张。

 3、建立具有护城河优势的东西，如写书，写经验，写文。专研某一领域，并有一定知名度。『当你做的事情可以被量化和替代这是最大的危险』并不是鼓励大家什么都会点，当万能钥匙。还是要专业，要专一。但是要把握一点：思考你最独特的价值。这个独特价值的核心就是你能为他人、群体和产业上下游带来怎样的价值。找到这个，结合自己的基因你也找到了乐趣，你会乐此不疲。

 4、做自己擅长的事情，如果年轻时，觉得编程仅仅是为了挣快钱的话，30岁后，该做自己擅长的事，如口才比较好，去当培训老师，或技术销售。
 */