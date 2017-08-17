package com.tudoujf.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tudoujf.R;
import com.tudoujf.activity.home.MessageDetailsActivity;
import com.tudoujf.adapter.SystemMessageFragLvAdapter;
import com.tudoujf.base.BaseBean;
import com.tudoujf.base.BaseFragment;
import com.tudoujf.bean.SystemMessageFragBean;
import com.tudoujf.bean.databean.MyMessageBean;
import com.tudoujf.config.Constants;
import com.tudoujf.http.HttpMethods;
import com.tudoujf.http.ParseJson;
import com.tudoujf.utils.FileUtils;
import com.tudoujf.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.tv_frag_systemmessage_previous)
    TextView tvPrevious;
    @BindView(R.id.tv_frag_systemmessage_next)
    TextView tvNext;

    private List<MyMessageBean.ItemsBean> list;

    private int type = 0;
    private int page = 1;
    private MyMessageBean bean;

    @Override
    public int layoutRes() {
        return R.layout.frag_systemmessage;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_frag_systemmessage_previous:
                if (page > 1) {
                    page--;
                    initDataFromInternet();
                }
                break;
            case R.id.tv_frag_systemmessage_next:
                if (bean!=null&&page<StringUtils.string2Integer(bean.getEpage())){
                    page++;
                    initDataFromInternet();
                }

                break;
        }

    }

    @Override
    public void initDataFromIntent() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type", 0);
        }
        if (type != 0) {
            initDataFromInternet();
        }

//        list = new ArrayList<>();
//        for (int i = 0; i < 13; i++) {
//            SystemMessageFragBean bean = new SystemMessageFragBean();
//            bean.setTitle("XXXXXXXXXXXXXXXXXXXXXX");
//            bean.setTime("201X-XX-XX");
//            list.add(bean);
//        }

    }

    @Override
    public void initView() {


    }

    @Override
    public void initListener() {
        lvFragSystemMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2017/8/17 传入打开详情时需要传入的参数
                openActivity(MessageDetailsActivity.class);
            }
        });
        tvPrevious.setOnClickListener(this);
        tvNext.setOnClickListener(this);

    }

    @Override
    public void initDataFromInternet() {


        TreeMap<String, String> map = new TreeMap<>();
        map.put("login_token", "12267");
        map.put("type", "" + type);
        map.put("page", "" + page);
        HttpMethods.getInstance().POST(getContext(), Constants.MESSAGE_LIST, map, "info", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = StringUtils.getDecodeString(response.body());
                Log.e("TAG", "onSuccess: ----------消息接口请求返回数据" + type + "-----------------" + result);
                FileUtils.saveJsonToSDCard(getActivity(), "infomessage", result);
                BaseBean baseBean = ParseJson.getJsonResult(response.body(), new TypeToken<MyMessageBean>() {
                        }.getType()
                        , MyMessageBean.class, getActivity());
                if (baseBean != null) {
                    bean = (MyMessageBean) baseBean;
                    LoadInternetDataToUi();
                }
            }

            @Override
            public void onError(Response<String> response) {
                Log.e("TAG", "onSuccess: ----------消息接口请求返回错误信息" + type + "-----------------" + response.message());
                super.onError(response);
            }
        });


    }

    @Override
    public void LoadInternetDataToUi() {
        if (bean != null) {

            list=bean.getItems();
            SystemMessageFragLvAdapter adapter = new SystemMessageFragLvAdapter(list, getActivity());
            lvFragSystemMessage.setAdapter(adapter);
        }

    }


}
