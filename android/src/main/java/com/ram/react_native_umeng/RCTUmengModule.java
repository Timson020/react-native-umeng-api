package com.ram.react_native_umeng;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.ram.react_native_umeng.entities.ShareEntitie;
import com.ram.react_native_umeng.utils.DeviceUtils;
import com.ram.react_native_umeng.utils.ShareUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;

/**
 * <p>com.ram.react_native_umeng
 * <p>Created by GaoXuanJi on 2017/11/27
 * <p>Des          :
 * <p>Modify Author:
 * <p>Modify Date  :
 */
public class RCTUmengModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext _reactContext;
    public static final String SHARE_TEXT = "text";
    public static final String SHARE_IMAGE = "image";
    public static final String SHARE_NEWS = "news";
    private ShareUtils mShareUtils;

    public RCTUmengModule(ReactApplicationContext reactContext) {
        super(reactContext);
        _reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RCTUmengModule";
    }

    /**
     * 检查手机是否安装微信返回promise，then 只可以返回 true or false
     *
     * @param promise
     */
    @ReactMethod
    public void isWXAppInstalled(final Promise promise) {
        boolean installed = DeviceUtils.isInstalledAppOrNot(_reactContext, DeviceUtils.WEIXIN);
        if (installed) {
            promise.resolve("1");
        } else {
            promise.resolve("0");
        }
    }

    /**
     * 检查手机是否安装QQ返回promise，then 只可以返回 true or false
     *
     * @param promise
     */
    @ReactMethod
    public void isQQAppInstalled(final Promise promise) {
        boolean installed = DeviceUtils.isInstalledAppOrNot(_reactContext, DeviceUtils.QQ);
        if (installed) {
            promise.resolve("1");
        } else {
            promise.resolve("0");
        }
    }

    /**
     * 检查手机是否安装微博返回promise，then 只可以返回 true or false
     *
     * @param promise
     */
    @ReactMethod
    public void isWBAppInstalled(final Promise promise) {
        boolean installed = DeviceUtils.isInstalledAppOrNot(_reactContext, DeviceUtils.WEIBO);
        if (installed) {
            promise.resolve("1");
        } else {
            promise.resolve("0");
        }
    }

    /**
     * QQ登录, then返回{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }
     * 这里的appid只是举例 isLogin是必须的，如果为false的时候errMsg也是必须的
     *
     * @param promise
     */
    @ReactMethod
    public void QQLogin(final Promise promise) {
        UMShareAPI.get(_reactContext).getPlatformInfo(_reactContext.getCurrentActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //todo
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                map.put("isLogin", "1");
                JSONObject jsonObject = new JSONObject((Map) map);
                promise.resolve(jsonObject.toString());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                promise.reject(null, "{\"isLogin\":\"0\",\"errmsg\":" + throwable.getMessage() + "}");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                promise.resolve("{\"isLogin\":\"0\",\"errmsg\":\"取消了\"}");
            }
        });
    }

    /**
     * 微信登录, then返回{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }
     * 这里的appid只是举例 isLogin是必须的，如果为false的时候errMsg也是必须的
     *
     * @param promise
     */
    @ReactMethod
    public void WXLogin(final Promise promise) {
        UMShareAPI.get(_reactContext).getPlatformInfo(_reactContext.getCurrentActivity(), SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //todo
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                map.put("isLogin", "1");
                JSONObject jsonObject = new JSONObject((Map) map);
                promise.resolve(jsonObject.toString());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                promise.reject(null, "{\"isLogin\":\"0\",\"errmsg\":" + throwable.getMessage() + "}");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                promise.resolve("{\"isLogin\":\"0\",\"errmsg\":\"取消了\"}");
            }
        });
    }

    /**
     * 微博登录, then返回{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }
     * 这里的appid只是举例 isLogin是必须的，如果为false的时候errMsg也是必须的
     *
     * @param promise
     */
    @ReactMethod
    public void WBLogin(final Promise promise) {
        UMShareAPI.get(_reactContext).getPlatformInfo(_reactContext.getCurrentActivity(), SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                //todo
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                map.put("isLogin", "1");
                JSONObject jsonObject = new JSONObject((Map) map);
                promise.resolve(jsonObject.toString());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                promise.reject(null, "{\"isLogin\":\"0\",\"errmsg\":" + throwable.getMessage() + "}");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                promise.resolve("{\"isLogin\":\"0\",\"errmsg\":\"取消了\"}");
            }
        });
    }

    /**
     * 分享到微信朋友圈, 返回promise，then 可以返回 { isShare: boolean, errMsg: '', 第三方返回的信息... }
     * isShare是必须的，如果为false的时候errMsg也是必须的
     */
    @ReactMethod
    public void shareWxTimeline(final String shareStr, final Promise promise) {
        getShareUtil();
        ShareEntitie shareEntitie = JSON.parseObject(shareStr, new TypeReference<ShareEntitie>() {});

        UMShareListener umShareListener = getUMShareListener(promise);


        switch (shareEntitie.getType()) {
            case SHARE_TEXT:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_TEXT, shareEntitie, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(umShareListener).share();
            case SHARE_IMAGE:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_IMAGE, shareEntitie, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(umShareListener).share();
            case SHARE_NEWS:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_NEWS, shareEntitie, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(umShareListener).share();
                break;
            default:
                throw new RuntimeException("请检查传送的json数据结构是否正确");
        }
    }

    /**
     * 分享到微信好友, 返回promise，then 可以返回 { isShare: boolean, errMsg: '', 第三方返回的信息... }
     * isShare是必须的，如果为false的时候errMsg也是必须的
     */
    @ReactMethod
    public void shareWxSession(final String shareStr, final Promise promise) {
        getShareUtil();
        ShareEntitie shareEntitie = JSON.parseObject(shareStr, new TypeReference<ShareEntitie>() {});

        UMShareListener umShareListener = getUMShareListener(promise);


        switch (shareEntitie.getType()) {
            case SHARE_TEXT:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_TEXT, shareEntitie, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).share();
            case SHARE_IMAGE:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_IMAGE, shareEntitie, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).share();
            case SHARE_NEWS:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_NEWS, shareEntitie, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).share();
                break;
            default:
                throw new RuntimeException("请检查传送的json数据结构是否正确");
        }
    }

    /**
     * 分享到QQ空间, 返回promise，then 可以返回 { isShare: boolean, errMsg: '', 第三方返回的信息... }
     * isShare是必须的，如果为false的时候errMsg也是必须的
     */
    @ReactMethod
    public void shareQzone(final String shareStr, final Promise promise) {
        getShareUtil();
        ShareEntitie shareEntitie =  JSON.parseObject(shareStr, new TypeReference<ShareEntitie>() {});

        UMShareListener umShareListener = getUMShareListener(promise);


        switch (shareEntitie.getType()) {
            case SHARE_TEXT:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_TEXT, shareEntitie, SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener).share();
            case SHARE_IMAGE:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_IMAGE, shareEntitie, SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener).share();
            case SHARE_NEWS:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_NEWS, shareEntitie, SHARE_MEDIA.QZONE)
                        .setCallback(umShareListener).share();
                break;
            default:
                throw new RuntimeException("请检查传送的json数据结构是否正确");
        }
    }
    /**
     * 分享到QQ好友, 返回promise，then 可以返回 { isShare: boolean, errMsg: '', 第三方返回的信息... }
     * isShare是必须的，如果为false的时候errMsg也是必须的
     */
    @ReactMethod
    public void shareQQ(final String shareStr, final Promise promise) {
        getShareUtil();
        ShareEntitie shareEntitie =  JSON.parseObject(shareStr, new TypeReference<ShareEntitie>() {});

        UMShareListener umShareListener = getUMShareListener(promise);


        switch (shareEntitie.getType()) {
            case SHARE_TEXT:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_TEXT, shareEntitie, SHARE_MEDIA.QQ)
                        .setCallback(umShareListener).share();
            case SHARE_IMAGE:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_IMAGE, shareEntitie, SHARE_MEDIA.QQ)
                        .setCallback(umShareListener).share();
            case SHARE_NEWS:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_NEWS, shareEntitie, SHARE_MEDIA.QQ)
                        .setCallback(umShareListener).share();
                break;
            default:
                throw new RuntimeException("请检查传送的json数据结构是否正确");
        }
    }
    /**
     * 分享到微博, 返回promise，then 可以返回 { isShare: boolean, errMsg: '', 第三方返回的信息... }
     * isShare是必须的，如果为false的时候errMsg也是必须的
     */
    @ReactMethod
    public void shareWB(final String shareStr, final Promise promise) {
        getShareUtil();
        ShareEntitie shareEntitie =  JSON.parseObject(shareStr, new TypeReference<ShareEntitie>() {});

        UMShareListener umShareListener = getUMShareListener(promise);


        switch (shareEntitie.getType()) {
            case SHARE_TEXT:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_TEXT, shareEntitie, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).share();
            case SHARE_IMAGE:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_IMAGE, shareEntitie, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).share();
            case SHARE_NEWS:
                mShareUtils
                        .buildShareTextAction(ShareUtils.SHARE_NEWS, shareEntitie, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).share();
                break;
            default:
                throw new RuntimeException("请检查传送的json数据结构是否正确");
        }
    }


    @NonNull
    private UMShareListener getUMShareListener(final Promise promise) {
        return new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onResult(SHARE_MEDIA share_media) {
                promise.resolve("{\"isShare\":\"1\",\"errmsg\":\"分享成功\"}");
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                promise.reject(null, "{\"isShare\":\"0\",\"errmsg\":" + throwable.getMessage() + "}");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                promise.resolve("{\"isShare\":\"0\",\"errmsg\":\"取消了\"}");
            }
        };
    }

    private ShareUtils getShareUtil(){
        if(null==mShareUtils){
            mShareUtils = new ShareUtils(_reactContext.getCurrentActivity());
        }
        return mShareUtils;

    }

}
