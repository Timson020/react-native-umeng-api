package com.ram.react_native_umeng.utils;

import android.app.Activity;

import com.ram.react_native_umeng.entities.ShareEntitie;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;

/**
 * <p>com.ram.react_native_umeng.utils
 * <p>Created by GaoXuanJi on 2017/11/28
 * <p>Des          :
 * <p>Modify Author:
 * <p>Modify Date  :
 */
public class ShareUtils {
    public static final int SHARE_TEXT = 1;
    public static final int SHARE_IMAGE = 2;
    public static final int SHARE_NEWS = 3;
    private final Activity mContext;

    public ShareUtils(Activity context) {
        this.mContext = context;
    }

    public ShareAction buildShareTextAction(int shareFlag, ShareEntitie shareRequest
            , SHARE_MEDIA platform) {
        switch (shareFlag) {
            case SHARE_TEXT:
                return new ShareAction(mContext)
                        .setPlatform(platform)//传入平台
                        .withText(shareRequest.getText());//分享内容
            case SHARE_NEWS:
                UMWeb web = new UMWeb(shareRequest.getWebpageUrl());
                web.setTitle(shareRequest.getTitle());//标题
                web.setThumb(null);  //缩略图
                web.setDescription(shareRequest.getDescription());//描述
                return new ShareAction(mContext)
                        .setPlatform(platform)//传入平台
                        .withMedia(web);//分享内容
            case SHARE_IMAGE:
                File file = new File(shareRequest.getImageUrl());
                UMImage image = new UMImage(mContext, file);//本地文件
                return new ShareAction(mContext)
                        .setPlatform(platform)//传入平台
                        .withMedia(image);//分享内容
            default:
                throw new RuntimeException("请检查输入的flag是否正确");
        }

    }


}
