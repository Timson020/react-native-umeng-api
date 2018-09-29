package com.ram.react_native_umeng.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * <p>com.ram.react_native_umeng.utils
 * <p>Created by GaoXuanJi on 2017/11/27
 * <p>Des          :
 * <p>Modify Author:
 * <p>Modify Date  :
 */
public class DeviceUtils {
    public static final int WEIXIN = 1;    //微信
    public static final int QQ = 2;        //qq
    public static final int WEIBO = 3;     //微博


    /**
     * 传入不同flag
     * WEIXIN = 1
     * QQ = 2
     * WEIBO = 3
     *
     * @param context
     * @param flag
     * @return
     */
    public static boolean isInstalledAppOrNot(Context context,int flag) {
        switch (flag) {
            case WEIXIN:
                return isInstalledOrNot(context,"com.tencent.mm");
            case QQ:
                return isInstalledOrNot(context,"com.tencent.mobileqq");
            case WEIBO:
                return isInstalledOrNot(context,"com.sina.weibo");
            default:
                throw new RuntimeException("请检查是否传入正确的flag");
        }
    }


    /**
     * 检测是否安装相应的应用
     *
     * @param context
     * @param packageName
     * @return
     */
    private static boolean isInstalledOrNot(Context context, String packageName) {
        boolean hasInstalled = false;
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent();
        intent.setPackage(packageName);
        List apps = pm.queryIntentActivities(intent, 0);
        if (apps.size() > 0) {
            hasInstalled = true;
        }
        return hasInstalled;
    }
}
