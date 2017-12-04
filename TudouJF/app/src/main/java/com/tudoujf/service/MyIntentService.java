package com.tudoujf.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * 一个自带开启线程与自动停止的服务
 */
public class MyIntentService extends IntentService {
    //自动生成代码--任务注释
    private static final String ACTION_FOO = "com.tudoujf.service.action.FOO";
    private static final String ACTION_BAZ = "com.tudoujf.service.action.BAZ";

    //自动生成代码--额外参数
    private static final String EXTRA_PARAM1 = "com.tudoujf.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.tudoujf.service.extra.PARAM2";
    //tag
    private String TAG="MyIntentService";
    //无参构造器,必须有,并调用父类的有参构造器
    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *开始这个服务来执行动作Foo用给定的参数。如果服务已经执行一个任务这一行动会排队
     * @see IntentService  自定义辅助方法(传递参数与action启动服务)
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *开始这个服务来执行动作巴兹与给定的参数。如果服务已经执行一个任务这一行动会排队。
     * @see IntentService 自定义辅助方法(传递参数与action启动服务)
     */
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }
    /**在异步线程中执行任务,该方法会自动调用*/
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {//根据intent传递的动作取出相应参数并执行相应任务
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
        Log.e(TAG, "onHandleIntent: "+Thread.currentThread().getName() );
    }

    /**
     在子线程中处理相应action的任务
     */
    private void handleActionFoo(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     在子线程中处理相应action的任务
     */
    private void handleActionBaz(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**任务完成,销毁服务,自动执行*/
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: "+ Thread.currentThread().getName());
        super.onDestroy();
    }
}
