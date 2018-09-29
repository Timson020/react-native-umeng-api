[TOC]

![npm](https://img.shields.io/npm/v/react-native-umeng.svg)
![npm](https://img.shields.io/npm/dw/react-native-umeng.svg)
![GitHub issues](https://img.shields.io/github/issues/Timson020/react-native-umeng.svg)

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/Timson020/react-native-umeng.git/pulls)
![npm](https://img.shields.io/npm/l/react-native-umeng.svg)


# react-native-umeng
友盟的第三方登录 分享集成

## 安装

```react-native link react-native-umeng```

除此以外还需要额外的配置

>切记必须要把第三方平台的资料都提交了。获得id 和 secret 才可以正确调用第三方sdk，否则调用都获取不了正确的信息

## Android安装
### Step1

在app主体里面MainApplication.java添加

```java
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

...
@Override
public void onCreate() {
  super.onCreate();
  SoLoader.init(this, /* native exopackage */ false);
  
  // android配置id
  PlatformConfig.setWeixin("wxid", "wx secret Key");
  PlatformConfig.setQQZone("qqid", "qq secret Key");
  PlatformConfig.setSinaWeibo("weiboid", "weibo secret Key", "URL");
  UMShareAPI.get(this);
}
```

### Step2

打开react-native-umeng/android/....xml

把```<data android:scheme="qqID"/>``` 里面的qqID修改成相应的qqID

把```<android:name="UMENG_APPKEY" android:value="<$UMENG_APPKEY>"> ``` 里面的 <$UMENG_APPKEY> 修改成相应的UMENG_APPKEY

### Step3

在项目的android/../../java/com/ProjectName/ 目录下新建文件夹 ```wxapi```

新建```WXEntryActivity.java```文件

复制源码

```java
package com.hyapp.wxapi;
import com.umeng.weixin.callback.WXCallbackActivity;
public class WXEntryActivity extends WXCallbackActivity {

}
```

### Step4

修改android/../../java/../```MainActivity.java```文件

```java
// 导入库
import android.content.Intent;
import com.umeng.socialize.UMShareAPI;

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);//完成回调
}
```

### Step5

修改android/app/src/main/```AndroidManifest.xml```文件

```xml
	···
	<activity android:name="com.facebook.react.devsupport.DevSettingsActivity" />
	<!-- 新增开始 -->
	<!-- 友盟分享 Activity   start -->
	  <activity
	    android:name=".wxapi.WXEntryActivity"
	    android:configChanges="keyboardHidden|orientation|screenSize"
	    android:exported="true"
	    android:screenOrientation="portrait">
	  </activity>
	<!-- 友盟分享 Activity   end -->
	<!-- 新增结束 -->
	</application>
```

### Step6

修改android/app/build.gradle

```
	...
	signingConfigs {
		release {
      storeFile file(MYAPP_RELEASE_STORE_FILE)
      storePassword MYAPP_RELEASE_STORE_PASSWORD
      keyAlias MYAPP_RELEASE_KEY_ALIAS
      keyPassword MYAPP_RELEASE_KEY_PASSWORD
    }
    <!-- 新增开始 -->
    <!-- 就是把release的配置复制下来 修改成debug, 否则不能使用微信的sdk -->
    debug {
      storeFile file(MYAPP_RELEASE_STORE_FILE)
      storePassword MYAPP_RELEASE_STORE_PASSWORD
      keyAlias MYAPP_RELEASE_KEY_ALIAS
      keyPassword MYAPP_RELEASE_KEY_PASSWORD
    }
    <!-- 新增结束 -->
	}
	...
```


## IOS安装

### Step1

通过U-Share iOS[下载页面](http://dev.umeng.com/social/ios/sdk-download)选择所需的社交平台后进行下载(qq，微信，微博 都选精简版即可),将U-Share SDK拖拽到工程里面（根目录还是 app文件夹里面都可以）。注意: 必须勾选弹框里的`Copy items if needed`和`Create groups`这2选项，点击Finish确定。此时UMSocial文件夹应该是黄色的，否则将无法读取到SDK里面的文件！

### Step2

在```Libraries``` -> ```RCTUmengModule.xcodeproj``` -> ```Build Setting``` -> ```Other Linker Flags``` -> 加入```-ObjC```

注意不要写为-Objc。-ObjC属于链接库必备参数，如果不加此项，会导致库文件无法被正确链接，SDK无法正常运行

### Step3

在```Libraries``` -> ```RCTUmengModule.xcodeproj``` -> ```Build Setting``` -> ```Framework Search Paths``` ->加入```"$(SRCROOT)/../../../ios/UMSocial"``` ,并将```non-recursive``` 改成 ```recursive```

### Step4

在工程target的```General``` -> ```Link Frameworks and Libraries``` -> 加入```libsqlite3.tbd, CoreGraphics.framework```

### Step5

在项目中的info.plist中加入应用白名单

```xml
<key>LSApplicationQueriesSchemes</key>
<array>
	<!-- 微信 URL Scheme 白名单-->
	<string>wechat</string>
	<string>weixin</string>

	<!-- 新浪微博 URL Scheme 白名单-->
	<string>sinaweibohd</string>
	<string>sinaweibo</string>
	<string>sinaweibosso</string>
	<string>weibosdk</string>
	<string>weibosdk2.5</string>

	<!-- QQ、Qzone、TIM URL Scheme 白名单-->
	<string>mqqapi</string>
	<string>mqq</string>
	<string>mqqOpensdkSSoLogin</string>
	<string>mqqconnect</string>
	<string>mqqopensdkdataline</string>
	<string>mqqopensdkgrouptribeshare</string>
	<string>mqqopensdkfriend</string>
	<string>mqqopensdkapi</string>
	<string>mqqopensdkapiV2</string>
	<string>mqqopensdkapiV3</string>
	<string>mqqopensdkapiV4</string>
	<string>mqzoneopensdk</string>
	<string>wtloginmqq</string>
	<string>wtloginmqq2</string>
	<string>mqqwpa</string>
	<string>mqzone</string>
	<string>mqzonev2</string>
	<string>mqzoneshare</string>
	<string>wtloginqzone</string>
	<string>mqzonewx</string>
	<string>mqzoneopensdkapiV2</string>
	<string>mqzoneopensdkapi19</string>
	<string>mqzoneopensdkapi</string>
	<string>mqqbrowser</string>
	<string>mttbrowser</string>
	<string>tim</string>
	<string>timapi</string>
	<string>timopensdkfriend</string>
	<string>timwpa</string>
	<string>timgamebindinggroup</string>
	<string>timapiwallet</string>
	<string>timOpensdkSSoLogin</string>
	<string>wtlogintim</string>
	<string>timopensdkgrouptribeshare</string>
	<string>timopensdkapiV4</string>
	<string>timgamebindinggroup</string>
	<string>timopensdkdataline</string>
	<string>wtlogintimV1</string>
	<string>timapiV1</string>
</array>
```

### Step6

在Info->URL Types 中增加你所需要的第三方平台的scheme：

- 例如使用QQ时 ```Identifier``` 设置为```qq```, URL Schemes 设置为你注册的QQ开发者账号中的```APPID```，需要加前缀tencent，例如```tencent1104903684```;

- 例如使用QQ空间时```Identifier``` 设置为```Qzone```, URL Schemes 设置为你注册的QQ开发者账号中的```APPID```转换成十六进制，需要加前缀QQ，例如```QQ41db7e04```;

- 例如使用微信时```Identifier``` 设置为```weixin```, URL Schemes 设置为你注册的微信开发者账号中的```appkey```，例如```wxdc1e388c3822c80b```;

- 例如使用微博时```Identifier``` 设置为```weibo```, URL Schemes 设置为你注册的微博开发者账号中的```appkey```，需要加前缀wb，例如```wb1104903684```;

(注意：appkey等信息需要在第三方平台申请，具体请参考[第三方账号申请及绑定](http://dev.umeng.com/social/ios/operation),如果还需要其它第三方平台具体配置URL Scheme操作请参考[友盟分享文档](http://dev.umeng.com/social/ios/quick-integration)的1.3.2步骤的图文)

### Step7

在你工程的AppDelegate.m文件中添加如下代码:

```c
#import <UMSocialCore/UMSocialCore.h>

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {

  /* 设置友盟appkey */
  [[UMSocialManager defaultManager] setUmSocialAppkey:@"你自己友盟的appkey"];

  // 设置微信的appKey和appSecret
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_WechatSession appKey:@"wxc838755f42580832" appSecret:@"2c85a1b0557be4094361ed536f8b2522" redirectURL:nil];

  // 设置分享到QQ互联的appID
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_QQ appKey:@"1106388009" appSecret:nil redirectURL:@"http://mobile.umeng.com/social"];

  // 设置新浪的appKey和appSecret
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_Sina appKey:@"76430328"  appSecret:@"579e9142a235ae6f3cb99c36b0588177" redirectURL:@"https://sns.whalecloud.com/sina2/callback"];               

     // 如果还需要使用其他第三方平台,请参考[友盟分享文档](http://dev.umeng.com/social/ios/quick-integration)的2.1初始化U-Share及第三方平台
    return YES;
}

// 支持所有iOS系统
- (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
{
    //6.3的新的API调用，是为了兼容国外平台(例如:新版facebookSDK,VK等)的调用[如果用6.2的api调用会没有回调],对国内平台没有影响
    BOOL result = [[UMSocialManager defaultManager] handleOpenURL:url sourceApplication:sourceApplication annotation:annotation];
    if (!result) {
         // 其他如支付等SDK的回调
    }
    return result;
}
```

## Method

### ```isWXAppInstalled```

检查手机是否安装微信返回```promise```，```then``` 只可以返回 ```true or false```

### ```isQQAppInstalled```

检查手机是否安装QQ返回```promise```，```then``` 只可以返回 ```true or false```

### ```isWBAppInstalled```

检查手机是否安装微博返回```promise```，```then``` 只可以返回 ```true or false```

### ```WXLogin```

微信登录, then返回```{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }```
*这里的appid只是举例*
**isLogin是必须的，如果为false的时候errMsg也是必须的**

### ```QQLogin```

QQ登录, then返回```{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }```
*这里的appid只是举例*
**isLogin是必须的，如果为false的时候errMsg也是必须的**

### ```WBLogin```

微博登录, then返回```{ appid: string, isLogin: boolean, errMsg: '', 第三方返回的信息... }```
*这里的appid只是举例*
**isLogin是必须的，如果为false的时候errMsg也是必须的**

### ```shareWxTimeline```

分享到微信朋友圈, 返回```promise```，```then``` 可以返回 ```{ isShare: boolean, errMsg: '', 第三方返回的信息... }```
**isShare是必须的，如果为false的时候errMsg也是必须的**

### ```shareWxSession```

分享到微信好友, 返回```promise```，```then``` 可以返回 ```{ isShare: boolean, errMsg: '', 第三方返回的信息... }```
**isShare是必须的，如果为false的时候errMsg也是必须的**

### ```shareQzone```

分享到QQ空间, 返回```promise```，```then``` 可以返回 ```{ isShare: boolean, errMsg: '', 第三方返回的信息... }```
**isShare是必须的，如果为false的时候errMsg也是必须的**

### ```shareQQ```

分享到QQ好友, 返回```promise```，```then``` 可以返回 ```{ isShare: boolean, errMsg: '', 第三方返回的信息... }```
**isShare是必须的，如果为false的时候errMsg也是必须的**

### ```shareWB```

分享到微博, 返回```promise```，```then``` 可以返回 ```{ isShare: boolean, errMsg: '', 第三方返回的信息... }```
**isShare是必须的，如果为false的时候errMsg也是必须的**

### 分享格式以及字段说明

```
	// 分享文字
	{	
		type: 'text', 
		text: 文字内容,
	}
	// 分享图片
	{	
		type: 'image',
		imageUrl: 图片地址,
		title : 标题,
		description : 描述,
	}
	// 分享网页
	{	
		type: 'news',
		title : 标题,
		description : 描述,	
		webpageUrl : 链接地址,
		imageUrl: 缩略图地址,
	}
```
