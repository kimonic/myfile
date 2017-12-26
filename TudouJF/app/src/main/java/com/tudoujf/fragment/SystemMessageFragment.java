package com.tudoujf.fragment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tudoujf.R;
import com.tudoujf.activity.home.MessageDetailsActivity;
import com.tudoujf.activity.home.MyMessageActivity;
import com.tudoujf.adapter.SystemMessageFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.CommonBean;
import com.tudoujf.bean.databean.MyMessageBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.ui.TuDouHeader;
import com.tudoujf.utils.DialogUtils;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.StringUtils;
import com.tudoujf.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;

/**
 * * ====================================================================
 * name:            SystemMessageFragment
 * guide:          MyMessageActivity-->
 * author：          kimonik
 * version：          1.0
 * date：            2017/8/7
 * description：     我的消息activity中的系统消息fragment
 * history：
 * * ====================================================================
 */
public class SystemMessageFragment extends BaseFragment {
    @BindView(R.id.lv_frag_systemmessage)
    ListView lvFragSystemMessage;
    //    @BindView(R.id.tv_frag_systemmessage_previous)
//    TextView tvPrevious;
//    @BindView(R.id.tv_frag_systemmessage_next)
//    TextView tvNext;
    @BindView(R.id.srl_frag_systemmessage)
    SmartRefreshLayout srlFragSystemMessage;

    private List<MyMessageBean.PageObjBean.ItemsBean> list = new ArrayList<>();

    private int type = 0;
    private int page = 1;
    private MyMessageBean bean;
    private int refreshFlag = 0;
    private static final int REFRESH = 1;
    private static final int LOADMORE = 2;
    private SystemMessageFragLvAdapter adapter;
    private Gson gson = new Gson();
    private int positionInner;
    private int systemMsgCount;
    private int myMsgCount;
    private boolean firstIn = true;


    @Override
    public int layoutRes() {
        return R.layout.frag_systemmessage;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_frag_systemmessage_previous:
//                if (page > 1) {
//                    page--;
//                    dialog.show();
//                    initDataFromInternet();
//                    tvPrevious.setTextColor(getResources().getColor(R.color.global_theme_background_color));
//                    tvNext.setTextColor(getResources().getColor(R.color.global_theme_background_color));
//                } else {
//                    tvPrevious.setTextColor(getResources().getColor(R.color.color_gray));
//
//                }
//                break;
//            case R.id.tv_frag_systemmessage_next:
//                if (bean != null && page < StringUtils.string2Integer(bean.getTotal_pages())) {
//                    dialog.show();
//                    page++;
//                    tvNext.setTextColor(getResources().getColor(R.color.global_theme_background_color));
//                    tvPrevious.setTextColor(getResources().getColor(R.color.global_theme_background_color));
//                    initDataFromInternet();
//                } else {
//                    tvNext.setTextColor(getResources().getColor(R.color.color_gray));
//                }
//                break;
//        }

    }

    @Override
    public void initDataFromIntent() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 0);//2--我的消息,1---系统消息
        }
    }

    @Override
    public void initView() {

        //设置全区背景色
        srlFragSystemMessage.setPrimaryColorsId(R.color.global_theme_background_color);
        //设置 Header 为 Material风格
        srlFragSystemMessage.setRefreshHeader(new TuDouHeader(getActivity()));
//        srlFragSystemMessage.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srlFragSystemMessage.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
//        dialog = DialogUtils.showProgreessDialog(getActivity(), "再点击一次将退出该页面!");

        if (type != 0) {
            initDataFromInternet();
        }

    }

    @Override
    public void initListener() {
        lvFragSystemMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionInner = position;
                if (!list.get(position).getStatus().equals("2")) {
//                    dialog.show();
                    showPDialog();
                    list.get(position).setStatus("2");
                    TreeMap<String, String> map = new TreeMap<>();

                    map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
                    map.put("message_id", list.get(position).getId());
                    adapter.notifyDataSetChanged();
                    HttpMethods.getInstance().POST(getContext(), Constants.MESSAGE_READ, map, "", new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
//                            dialog.dismiss();
                            dismissPDialog();
                            String result = StringUtils.getDecodeString(response.body());
                            CommonBean bean = gson.fromJson(result, CommonBean.class);
                            if (bean != null && bean.getCode().equals("200")) {
                                try {
                                    JSONObject json = new JSONObject(bean.getData().toString());
                                    ((MyMessageActivity) getActivity()).unreadMessageCount(json.getInt("message_count"));

                                    //-------------------------------消息已读变化后------------------------------------------------
                                    //-------------------------------消息已读变化后------------------------------------------------
                                    //-------------------------------消息已读变化后------------------------------------------------
                                    if (type == 1) {
                                        myMsgCount--;
                                        if (myMsgCount < 1) {
                                            ((MyMessageActivity) getActivity()).setTvMyShengYu("", 1);
                                        } else if (myMsgCount < 100) {
                                            ((MyMessageActivity) getActivity()).setTvMyShengYu("" + myMsgCount, 0);
                                        } else {
                                            ((MyMessageActivity) getActivity()).setTvMyShengYu("99", 0);
                                        }
                                    } else {
                                        systemMsgCount--;
                                        if (systemMsgCount < 1) {
                                            ((MyMessageActivity) getActivity()).setTvSystemShengYu("", 1);
                                        } else if (systemMsgCount < 100) {
                                            ((MyMessageActivity) getActivity()).setTvSystemShengYu("" + systemMsgCount, 0);
                                        } else {
                                            ((MyMessageActivity) getActivity()).setTvSystemShengYu("99", 0);
                                        }
                                    }
                                    //-------------------------------消息已读变化后------------------------------------------------
                                    //-------------------------------消息已读变化后------------------------------------------------
                                    //-------------------------------消息已读变化后------------------------------------------------

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                openDetailsActivity();
                            } else {
                                ToastUtils.showToast(getActivity(), "网络不太给力哟!");
                            }
                            Log.e("TAG", "onSuccess: ----------标记消息是否已读接口请求返回数据" + type + "-----------------" + result);
                        }
                    });
                } else {
                    openDetailsActivity();
                }


            }
        });
//        tvPrevious.setOnClickListener(this);
//        tvNext.setOnClickListener(this);


//        /**上拉加载与下拉刷新监听*/
        srlFragSystemMessage.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                dialog.show();
                page = 1;
                list.clear();
                initDataFromInternet();
                refreshFlag = 1;
            }
        });
        srlFragSystemMessage.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
//                dialog.show();
                if (bean != null && page < StringUtils.string2Integer(bean.getPageObj().getTotal_pages())) {
                    page++;
                    initDataFromInternet();
                    refreshFlag = 2;
                } else {
                    stopRefresh(2);
//                    dialog.dismiss();
                }
            }
        });

    }

    @Override
    public void initDataFromInternet() {
        showPDialog();
        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", UserConfig.getInstance().getLoginToken(getActivity()));
        map.put("type", "" + type);
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(getContext(), Constants.MESSAGE_LIST, map, "info", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissPDialog();
                if (refreshFlag != 0) {
                    stopRefresh(refreshFlag);
                    refreshFlag = 0;
                }
//                dialog.dismiss();
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------消息接口请求返回数据" + type + "-----------------" + result);
//                FileUtils.saveJsonToSDCard(getActivity(), "infomessage", result);
                BaseBean baseBean = ParseJson.getJsonResult(response.body(), new TypeToken<MyMessageBean>() {
                        }.getType()
                        , MyMessageBean.class, getActivity());
                if (baseBean != null) {
                    bean = (MyMessageBean) baseBean;
                    LoadInternetDataToUi();
                } else {
                    ToastUtils.showToast(getActivity(), R.string.shujujiazaichucuo);
                }
            }

            @Override
            public void onError(Response<String> response) {
                dismissPDialog();
//                dialog.dismiss();
                ToastUtils.showToast(getActivity(), R.string.wuwangluotishi);
                Log.e("TAG", "onSuccess: ----------消息接口请求返回错误信息" + type + "-----------------" + response.message());
                super.onError(response);
            }
        });
    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {

            list.addAll(bean.getPageObj().getItems());
            if (list.size() == 0) {
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                if (type == 1) {
                    ((MyMessageActivity) getActivity()).setTvMyShengYu("0", 1);
                } else {
                    ((MyMessageActivity) getActivity()).setTvSystemShengYu("0", 1);
                }
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                if (type == 1 && firstIn) {
                    firstIn = false;
                    ToastUtils.showToast(getActivity(), R.string.zanshimeiyouxiaoxi);
                } else {
                    ToastUtils.showToast(getActivity(), R.string.zanshimeiyouxiaoxi);
                }
            } else {


                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                if (type == 1) {
                    myMsgCount = StringUtils.string2Integer(bean.getMail());
                    if (myMsgCount < 1) {
                        ((MyMessageActivity) getActivity()).setTvMyShengYu("", 1);
                    } else if (myMsgCount < 100) {
                        ((MyMessageActivity) getActivity()).setTvMyShengYu(bean.getMail(), 0);
                    } else {
                        ((MyMessageActivity) getActivity()).setTvMyShengYu("99", 0);
                    }
                } else {
                    systemMsgCount = StringUtils.string2Integer(bean.getMessage());

                    if (systemMsgCount < 1) {
                        ((MyMessageActivity) getActivity()).setTvSystemShengYu("", 1);
                    } else if (systemMsgCount < 100) {
                        ((MyMessageActivity) getActivity()).setTvSystemShengYu(bean.getMessage(), 0);
                    } else {
                        ((MyMessageActivity) getActivity()).setTvSystemShengYu("99", 0);
                    }
                }
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------
                //-----------------------------剩余未读消息显示--------------------------------------


                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter = new SystemMessageFragLvAdapter(list, getActivity());
                    lvFragSystemMessage.setAdapter(adapter);
                }
            }
        }
    }

    /**
     * 终止刷新加载
     */
    private void stopRefresh(int flag) {
        switch (flag) {
            case REFRESH:
                srlFragSystemMessage.finishRefresh();
                break;
            case LOADMORE:
                srlFragSystemMessage.finishLoadmore();
                break;
        }
    }

    private void openDetailsActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("title", list.get(positionInner).getTitle());
        bundle.putString("content", list.get(positionInner).getContents());
        bundle.putString("time", StringUtils.getStrTime(list.get(positionInner).getSend_time()));
        openActivity(MessageDetailsActivity.class, bundle);
    }


}
