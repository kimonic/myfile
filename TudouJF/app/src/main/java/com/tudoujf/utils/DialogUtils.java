package com.tudoujf.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tudoujf.R;
import com.tudoujf.activity.common.WebActivity;
import com.tudoujf.activity.my.RealNameAuthenticationHuiFuActivity;
import com.tudoujf.adapter.DialogLVAdapter;
import com.tudoujf.bean.SystemMessageFragBean;
import com.tudoujf.bean.databean.TypeInfoBean;
import com.tudoujf.config.Constants;
import com.tudoujf.config.UserConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * * ================================================
 * name:            DialogUtils
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2017/7/25
 * description： dialog辅助工具类
 * history：
 * ===================================================
 */

public class DialogUtils {

    /**
     * 含有取消按钮  传输资源id
     *
     * @param activity     contrext
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param listener     确定按钮点击事件
     */
    public static void showDialog(final Activity activity, int message, int positiveText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText, listener)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 含取消按钮  传输文本内容
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param listener     确定按钮点击事件
     */
    public static void showDialog(final Activity activity, String message, int positiveText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText, listener)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }


    /**
     * 含取消按钮  传输文本内容
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param listener     确定按钮点击事件
     */
    public static AlertDialog.Builder showDialogS(final Activity activity, int message, int positiveText, int title, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText, listener)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
        return dialog;
    }

    /**
     * 不含取消按钮   传输资源id
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param title        标题
     * @param listener     确定按钮点击事件
     */
    public static void showDialog(final Activity activity, int message, int positiveText, int title, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText, listener)
                .show();
    }

    /**
     * 含标题,取消按钮  传输资源id
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定文本
     * @param title        标题
     * @param listener     确定按钮点击事件
     * @param flag         多态标识
     */
    public static void showDialog(final Activity activity, int message, int positiveText, int title, DialogInterface.OnClickListener listener, int flag) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText, listener)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 不含取消按钮,不含标题  传输资源id
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param listener     确定按钮点击事件
     */
    public static void showDialogNoTitle(final Activity activity, int message, int positiveText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message)
                .setPositiveButton(positiveText, listener)
                .show();
    }


    /**
     * 不含取消按钮,不含标题  传输文本
     *
     * @param activity     context
     * @param message      消息
     * @param positiveText 确定按钮文本
     * @param listener     确定按钮点击事件
     */
    public static void showDialogNoTitle(final Activity activity, String message, String positiveText, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setMessage(message)
                .setPositiveButton(positiveText, listener)
                .show();
    }


    /**
     * /**
     * 展示土豆进度dialog
     *
     * @param context 上下文
     * @param msg     点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showProgreessDialog(final Context context, final String msg) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_act_login, null);
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            private long beforeTime;

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    if (System.currentTimeMillis() - beforeTime < 2000) {
                        ((Activity) context).finish();
                    } else {
                        ToastUtils.showToast(context, msg);
                        beforeTime = System.currentTimeMillis();
                    }
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        });
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            WindowManager.LayoutParams lp = window.getAttributes();
//            Log.e(TAG, "showProgreessDialog: --ScreenSizeUtils.getDensity(this)-"+ ScreenSizeUtils.getDensity(this));
            int wh = 90 * ScreenSizeUtils.getDensity(context);
            lp.width = wh;
            lp.height = wh;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 时间筛选dialog--integralrecodeactivity中显示
     *
     * @param context 上下文
     * @param msg     点击back键的提示信息
     * @return alertdialog(v7)
     */
    public static AlertDialog showTimeSel(final Context context, final String msg, View view) {

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    dialog.dismiss();
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        });
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            ColorDrawable drawable = new ColorDrawable(Color.WHITE);
            window.setBackgroundDrawable(drawable);
//            window.setBackgroundDrawable(new ColorDrawable());
            window.setContentView(view);
            window.setWindowAnimations(R.style.AnimBottom);
        }

        return dialog;
    }

    /**
     * 开通汇付托管dialog
     */
    public static void showHuiFuDialog(final Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_act_home, null);
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(params);
        final PopupWindow pop = new PopupWindow(view, ScreenSizeUtils.getScreenWidth(context) - 180, 500);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点

        pop.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
                params.alpha = 1f;
                ((Activity) context).getWindow().setAttributes(params);
            }
        });
        TextView tvLiJiKaiTong = (TextView) view.findViewById(R.id.tv_dialog_lijikaitong);
        TextView tvZanBuKaiTong = (TextView) view.findViewById(R.id.tv_dialog_zanbukaitong);
        tvZanBuKaiTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
        tvLiJiKaiTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  2017/8/1 开通资金托管逻辑
                Intent intent = new Intent(context, RealNameAuthenticationHuiFuActivity.class);
                context.startActivity(intent);
                pop.dismiss();
            }
        });


        SharedPreferencesUtils.getInstance(context, "popshow").put("show", false);

    }


    /**
     * 简单登录
     *
     * @param context 上下文
     * @param msg     点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showUserDialog(final Context context, final String msg, View view) {
//        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_act_user, null);
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            private long beforeTime;

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                    if (System.currentTimeMillis() - beforeTime < 2000) {
                        ((Activity) context).finish();
                    } else {
                        ToastUtils.showToast(context, msg);
                        beforeTime = System.currentTimeMillis();
                    }
                    return true;
                } else {
                    return false; //默认返回 false
                }
            }
        });
        dialog.show();
        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            window.setContentView(view);
            WindowManager.LayoutParams lp = window.getAttributes();
//            Log.e(TAG, "showProgreessDialog: --ScreenSizeUtils.getDensity(this)-"+ ScreenSizeUtils.getDensity(this));
            int wh = 90 * ScreenSizeUtils.getDensity(context);
            Log.e("TAG", "showUserDialog: ----wh-" + wh);
            lp.width = wh;
            lp.height = wh;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 简单提示dialog
     *
     * @param context 上下文
     * @param content 点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showPromptDialog(final Context context, String title, String content, final DialogUtilsClickListener listener) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvQueDing = view.findViewById(R.id.tv_queding);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick();
                }
            }
        });


        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }

    /**
     * 简单提示dialog
     *
     * @param context 上下文
     * @param content 点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showPromptDialogAll(final Context context, String title, String content,
                                                  final DialogUtilsClickListener agreeListener
            , final DialogUtilsClickListener cancelListener) {

        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvQueDing = view.findViewById(R.id.tv_queding);

        tvTitle.setText(title);
        tvContent.setText(content);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (cancelListener != null) {
                    cancelListener.onClick();
                }
            }
        });

        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (agreeListener != null) {
                    agreeListener.onClick();
                }
            }
        });


        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 只含有确定的简单提示dialog
     *
     * @param context 上下文
     * @param content 点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showPromptDialogConfirm(final Context context, String title, String content,
                                                      final DialogUtilsClickListener agreeListener) {

        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.dialog_prompt_confirm, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContent = view.findViewById(R.id.tv_content);
        TextView tvQueDing = view.findViewById(R.id.tv_queding);

        tvTitle.setText(title);
        tvContent.setText(content);


        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (agreeListener != null) {
                    agreeListener.onClick();
                }
            }
        });


        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 债权撤销提示dialog
     *
     * @param context 上下文
     * @param content 点击back键的提示信息
     * @return alertdialog
     */
    public static AlertDialog showCreditorCancel(final Context context, String content, final DialogUtilsClickListener listener) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_creditorcancel, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        TextView tvContent = view.findViewById(R.id.tv_content);
        TextView tvCancle = view.findViewById(R.id.tv_cancle);
        TextView tvQueDing = view.findViewById(R.id.tv_queding);

        tvContent.setText(content);
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvQueDing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick();
                }
            }
        });


        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 积分商城筛选dialog
     */
    public static AlertDialog showListDialog(Context context, final List<TypeInfoBean.ItemsBean> listType
            , final ListDialogClickListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_listdialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        final ListView listView = view.findViewById(R.id.lv_dialoglist);
        TextView cancel = view.findViewById(R.id.tv_dialoglist_cancel);
        TextView queding = view.findViewById(R.id.tv_dialoglist_confirm);

        final int[] currentPosition = new int[1];

//        -

        final DialogLVAdapter adapter = new DialogLVAdapter(listType, context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                view.setBackgroundColor(Color.parseColor("#E3FBFE"));
                currentPosition[0] = position;
                for (int i = 0; i < listType.size(); i++) {
                    if (i == position) {
                        listType.get(i).setBacFlag(2);
                    } else {
                        listType.get(i).setBacFlag(1);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick(currentPosition[0]);
                }
            }
        });

        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            window.setAttributes(lp);
        }
        return dialog;
    }


    public static AlertDialog showIntegralInsufficient(Context context) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setImageResource(R.drawable.dialog_integralinsufficient);

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        //一定得在show完dialog后来set属性
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(view);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = window.getAttributes();
            int desity = ScreenSizeUtils.getDensity(context);

            lp.width = 155 * desity;
            lp.height = 46 * desity;
            lp.gravity = Gravity.CENTER;
            window.setAttributes(lp);
        }
        return dialog;
    }


    /**
     * 风险测评dialog
     */
    public static void showRiskDialog(final Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_risk, null);
        WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
        params.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(params);
        final PopupWindow pop = new PopupWindow(view);
        pop.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pop.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);//透明背景图片
        pop.setBackgroundDrawable(drawable);//pop必须设置背景,否则可能有各种意外
        pop.setOutsideTouchable(true);//触摸pop外面的部分取消pop
        pop.setFocusable(true);//获取焦点

        pop.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0, 0);//显示位置

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params = ((Activity) context).getWindow().getAttributes();
                params.alpha = 1f;
                ((Activity) context).getWindow().setAttributes(params);
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_dialog_risk_close);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

//        TextView tvLiJiKaiTong = (TextView) view.findViewById(R.id.tv_dialog_lijikaitong);
//        TextView tvZanBuKaiTong = (TextView) view.findViewById(R.id.tv_dialog_zanbukaitong);
//        tvZanBuKaiTong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//            }
//        });
//        tvLiJiKaiTong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 TO DO: 2018/1/17  打开评估页面
//
//                Intent intent = new Intent(context, WebActivity.class);
//                intent.putExtra("url", Constants.RISK_QUESTION+ UserConfig.getInstance().getLoginToken(context));
//                intent.putExtra("title","风险测评");
//                context.startActivity(intent);
//                pop.dismiss();
//            }
//        });


//        SharedPreferencesUtils.getInstance(context, "popshow").put("showrisk", false);

    }


    public interface ListDialogClickListener {
        void onClick(int position);
    }


    public interface DialogUtilsClickListener {
        void onClick();
    }


}
