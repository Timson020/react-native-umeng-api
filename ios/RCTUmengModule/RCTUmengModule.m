//
//  RCTUmengModule.m
//  RCTUmengModule
//
//  Created by 欧阳伟坚 on 2017/11/28.
//  Copyright © 2017年 欧阳伟坚. All rights reserved.
//

#import "RCTUmengModule.h"
#import <UMSocialCore/UMSocialCore.h>
#import <UShareUI/UShareUI.h>

@implementation RCTUmengModule {
    RCTPromiseResolveBlock _resolveBlock;
    RCTPromiseRejectBlock _rejectBlock;
}

RCT_EXPORT_MODULE();

// 检查手机是否安装微信
RCT_REMAP_METHOD(isWXAppInstalled,
                 resolverWX:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    if ([[UMSocialManager defaultManager] isInstall:UMSocialPlatformType_WechatSession]) {
        _resolveBlock(@1);
    }
    else{
        _resolveBlock(@0);
    }
}

// 微信登录
RCT_REMAP_METHOD(WXLogin,
                 resolverWXL:(RCTPromiseResolveBlock)resolve
                 rejecterWXL:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    [[UMSocialManager defaultManager]getUserInfoWithPlatform:UMSocialPlatformType_WechatSession currentViewController:nil completion:^(id result, NSError *error) {
        
        if (error) {
            
            NSMutableDictionary *errMuDic = [[NSMutableDictionary alloc]init];
            
            [errMuDic setObject:@0 forKey:@"isLogin"];
            
            [errMuDic setObject:[error.userInfo objectForKey: @"message"] forKey:@"errmsg"];
            
            NSString * errStr = [self dictionaryToJson:errMuDic];
            
            _resolveBlock(errStr);
        }
        else{
            UMSocialUserInfoResponse *resp = result;
            
            NSMutableDictionary *dataMuDic = [[NSMutableDictionary alloc]init];
            
            [dataMuDic setObject:@1 forKey:@"isLogin"];
            
            [dataMuDic setObject:resp.openid forKey:@"id"];
            
            [dataMuDic addEntriesFromDictionary:resp.originalResponse];
            
            NSString *data = [self dictionaryToJson:dataMuDic];
            
            NSLog(@"处理后的数据%@", data);
            
            _resolveBlock(data);
        }
    }];
}

// 微信朋友圈分享
RCT_REMAP_METHOD(shareWxTimeline,
                 WXShareMessage:(NSDictionary *)share
                 resolverWXT:(RCTPromiseResolveBlock)resolve
                 rejecterWXT:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    // 创建分享消息对象
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    NSString *type = [share objectForKey:@"type"];
    
    if ([type isEqualToString:@"text"]) {
        //设置文本
        messageObject.text = [share objectForKey:@"text"];
    }
    else if ([type isEqualToString:@"image"]){
        // 创建图片内容对象
        UMShareImageObject *shareObject = [[UMShareImageObject alloc]init];
        
        // 如果有缩略图，则设置缩略图
        shareObject.thumbImage = nil;
        [shareObject setShareImage:[share objectForKey:@"imageUrl"]];
        
        //分享消息对象设置分享内容对象
        messageObject.shareObject = shareObject;
    }
    else if ([type isEqualToString:@"news"]){
        // 创建网页内容对象
        NSString *thumbURL = [share objectForKey:@"imageUrl"];
        
        UMShareWebpageObject *shareObject = [UMShareWebpageObject shareObjectWithTitle:[share objectForKey:@"title"] descr:[share objectForKey:@"description"] thumImage:thumbURL];
        
        // 设置网页地址
        shareObject.webpageUrl = [share objectForKey:@"webpageUrl"];
        
        messageObject.shareObject = shareObject;
    }
    
    [self shareToPlatform:UMSocialPlatformType_WechatTimeLine messageObject:messageObject resolver:resolve];
}

// 微信好友分享
RCT_REMAP_METHOD(shareWxSession,
                 WXShareMessage:(NSDictionary *)share
                 resolverWXS:(RCTPromiseResolveBlock)resolve
                 rejecterWXS:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    // 创建分享消息对象
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    NSString *type = [share objectForKey:@"type"];
    
    if ([type isEqualToString:@"text"]) {
        //设置文本
        messageObject.text = [share objectForKey:@"text"];
    }
    else if ([type isEqualToString:@"image"]){
        // 创建图片内容对象
        UMShareImageObject *shareObject = [[UMShareImageObject alloc]init];
        
        // 如果有缩略图，则设置缩略图
        shareObject.thumbImage = nil;
        [shareObject setShareImage:[share objectForKey:@"imageUrl"]];
        
        //分享消息对象设置分享内容对象
        messageObject.shareObject = shareObject;
    }
    else if ([type isEqualToString:@"news"]){
        // 创建网页内容对象
        NSString *thumbURL = [share objectForKey:@"imageUrl"];
        
        UMShareWebpageObject *shareObject = [UMShareWebpageObject shareObjectWithTitle:[share objectForKey:@"title"] descr:[share objectForKey:@"description"] thumImage:thumbURL];
        
        // 设置网页地址
        shareObject.webpageUrl = [share objectForKey:@"webpageUrl"];
        
        messageObject.shareObject = shareObject;
    }
    
    [self shareToPlatform:UMSocialPlatformType_WechatSession messageObject:messageObject resolver:resolve];
}

// 检查手机是否安装QQ
RCT_REMAP_METHOD(isQQAppInstalled,
                 resolverQQ:(RCTPromiseResolveBlock)resolve
                 rejecterQQ:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    if ([[UMSocialManager defaultManager] isInstall:UMSocialPlatformType_QQ]) {
        _resolveBlock(@1);
    }
    else{
        _resolveBlock(@0);
    }
}

// QQ登陆
RCT_REMAP_METHOD(QQLogin,
                 resolverQQL:(RCTPromiseResolveBlock)resolve
                 rejecterQQL:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    [[UMSocialManager defaultManager]getUserInfoWithPlatform:UMSocialPlatformType_QQ currentViewController:nil completion:^(id result, NSError *error) {
        
        if (error) {
            
            NSMutableDictionary *errMuDic = [[NSMutableDictionary alloc]init];
            
            [errMuDic setObject:@0 forKey:@"isLogin"];
            
            [errMuDic setObject:[error.userInfo objectForKey: @"messsage"] forKey:@"errmsg"];
            
            NSString * errStr = [self dictionaryToJson:errMuDic];
            
            _resolveBlock(errStr);
        }
        else{
            UMSocialUserInfoResponse *resp = result;
            
            NSMutableDictionary *dataMuDic = [[NSMutableDictionary alloc]init];
            
            [dataMuDic setObject:@1 forKey:@"isLogin"];
            
            [dataMuDic setObject:resp.openid forKey:@"id"];
            
            [dataMuDic addEntriesFromDictionary:resp.originalResponse];
            
            NSString *data = [self dictionaryToJson:dataMuDic];
            
            NSLog(@"QQ处理后数据: %@", data);
            
            _resolveBlock(data);
        }
    }];
}

// 分享到QQ空间
RCT_REMAP_METHOD(shareQzone,
                 QQShareMessage:(NSDictionary *)share
                 resolverQQz:(RCTPromiseResolveBlock)resolve
                 rejecterQQz:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    // 创建分享消息对象
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    NSString *type = [share objectForKey:@"type"];
    
    if ([type isEqualToString:@"text"]) {
        //设置文本
        messageObject.text = [share objectForKey:@"text"];
    }
    else if ([type isEqualToString:@"image"]){
        // 创建图片内容对象
        UMShareImageObject *shareObject = [[UMShareImageObject alloc]init];
        
        // 如果有缩略图，则设置缩略图
        shareObject.thumbImage = nil;
        [shareObject setShareImage:[share objectForKey:@"imageUrl"]];
        
        //分享消息对象设置分享内容对象
        messageObject.shareObject = shareObject;
    }
    else if ([type isEqualToString:@"news"]){
        // 创建网页内容对象
        NSString *thumbURL = [share objectForKey:@"imageUrl"];
        
        UMShareWebpageObject *shareObject = [UMShareWebpageObject shareObjectWithTitle:[share objectForKey:@"title"] descr:[share objectForKey:@"description"] thumImage:thumbURL];
        
        // 设置网页地址
        shareObject.webpageUrl = [share objectForKey:@"webpageUrl"];
        
        messageObject.shareObject = shareObject;
    }
    
    [self shareToPlatform:UMSocialPlatformType_Qzone messageObject:messageObject resolver:resolve];
}

// 分享到QQ
RCT_REMAP_METHOD(shareQQ,
                 QQShareMessage:(NSDictionary *)share
                 resolverQq:(RCTPromiseResolveBlock)resolve
                 rejecterQq:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    // 创建分享消息对象
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    NSString *type = [share objectForKey:@"type"];
    
    if ([type isEqualToString:@"text"]) {
        //设置文本
        messageObject.text = [share objectForKey:@"text"];
    }
    else if ([type isEqualToString:@"image"]){
        // 创建图片内容对象
        UMShareImageObject *shareObject = [[UMShareImageObject alloc]init];
        
        // 如果有缩略图，则设置缩略图
        shareObject.thumbImage = nil;
        [shareObject setShareImage:[share objectForKey:@"imageUrl"]];
        
        //分享消息对象设置分享内容对象
        messageObject.shareObject = shareObject;
    }
    else if ([type isEqualToString:@"news"]){
        // 创建网页内容对象
        NSString *thumbURL = [share objectForKey:@"imageUrl"];
        
        UMShareWebpageObject *shareObject = [UMShareWebpageObject shareObjectWithTitle:[share objectForKey:@"title"] descr:[share objectForKey:@"description"] thumImage:thumbURL];
        
        // 设置网页地址
        shareObject.webpageUrl = [share objectForKey:@"webpageUrl"];
        
        messageObject.shareObject = shareObject;
    }
    
    [self shareToPlatform:UMSocialPlatformType_QQ messageObject:messageObject resolver:resolve];
}

// 检查手机是否安装微博
RCT_REMAP_METHOD(isWBAppInstalled,
                 resolverWB:(RCTPromiseResolveBlock)resolve
                 rejecterWB:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    if ([[UMSocialManager defaultManager] isInstall:UMSocialPlatformType_Sina]) {
        _resolveBlock(@1);
    }
    else{
        _resolveBlock(@0);
    }
}

// 微博登陆
RCT_REMAP_METHOD(WBLogin,
                 resolverWBL:(RCTPromiseResolveBlock)resolve
                 rejecterWBL:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    [[UMSocialManager defaultManager]getUserInfoWithPlatform:UMSocialPlatformType_Sina currentViewController:nil completion:^(id result, NSError *error) {
        
        if (error) {
            
            NSMutableDictionary *errMuDic = [[NSMutableDictionary alloc]init];
            
            [errMuDic setObject:@0 forKey:@"isLogin"];
            
            [errMuDic setObject:[error.userInfo objectForKey: @"message"] forKey:@"errmsg"];
            
            NSString * errStr = [self dictionaryToJson:errMuDic];
            
            _resolveBlock(errStr);
        }
        else{
            UMSocialUserInfoResponse *resp = result;
            
            NSMutableDictionary *dataMuDic = [[NSMutableDictionary alloc]init];
            
            [dataMuDic setObject:@1 forKey:@"isLogin"];
            
            [dataMuDic addEntriesFromDictionary:resp.originalResponse];
            
            NSString *data = [self dictionaryToJson:dataMuDic];
            
            NSLog(@"微博处理后数据:%@", data);
            
            _resolveBlock(data);
        }
    }];
}

// 分享到微博
RCT_REMAP_METHOD(shareWB,
                 WBShareMessage:(NSDictionary *)share
                 resolverWb:(RCTPromiseResolveBlock)resolve
                 rejecterWb:(RCTPromiseRejectBlock)reject)
{
    _resolveBlock=resolve;
    
    _rejectBlock=reject;
    
    // 创建分享消息对象
    UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
    
    NSString *type = [share objectForKey:@"type"];
    
    if ([type isEqualToString:@"text"]) {
        //设置文本
        messageObject.text = [share objectForKey:@"text"];
    }
    else if ([type isEqualToString:@"image"]){
        // 创建图片内容对象
        UMShareImageObject *shareObject = [[UMShareImageObject alloc]init];
        
        // 如果有缩略图，则设置缩略图
        shareObject.thumbImage = nil;
        [shareObject setShareImage:[share objectForKey:@"imageUrl"]];
        
        //分享消息对象设置分享内容对象
        messageObject.shareObject = shareObject;
    }
    else if ([type isEqualToString:@"news"]){
        // 创建网页内容对象
        NSString *thumbURL = [share objectForKey:@"imageUrl"];
        
        UMShareWebpageObject *shareObject = [UMShareWebpageObject shareObjectWithTitle:[share objectForKey:@"title"] descr:[share objectForKey:@"description"] thumImage:thumbURL];
        
        // 设置网页地址
        shareObject.webpageUrl = [share objectForKey:@"webpageUrl"];
        
        messageObject.shareObject = shareObject;
    }
    
    [self shareToPlatform:UMSocialPlatformType_Sina messageObject:messageObject resolver:resolve];
}

//字典转json格式字符串：
- (NSString*)dictionaryToJson:(NSDictionary *)dic
{
    NSError *parseError = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&parseError];
    
    return [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
}


//调用分享接口
-(void)shareToPlatform:(UMSocialPlatformType)platForm messageObject:(UMSocialMessageObject *)message resolver:(RCTPromiseResolveBlock)resolve{
    // 当用户分享成功后留在第三方平台时调用的回调
    [[NSUserDefaults standardUserDefaults]setBool:YES forKey:@"isShare"];
    
    [[UMSocialManager defaultManager]shareToPlatform:platForm messageObject:message currentViewController:nil completion:^(id result, NSError *error) {
        if (error) {
            NSLog(@"%@",error);
            
            NSMutableDictionary *errMuDic = [[NSMutableDictionary alloc]init];
            
            [errMuDic setObject:@0 forKey:@"isShare"];
            
            [errMuDic setObject:[error.userInfo objectForKey: @"message"] forKey:@"errmsg"];
            
            NSString * errStr = [self dictionaryToJson:errMuDic];
            
            resolve(errStr);
        }
        else {
            if ([result isKindOfClass:[UMSocialShareResponse class]]) {
                
                UMSocialShareResponse * resp = result;
                //分享结果消息
                NSLog(@"response message is %@",resp.message);
                //第三方原始返回的数据
                NSLog(@"response originalResponse data is %@",resp.originalResponse);
                
                NSMutableDictionary *dataMuDic = [[NSMutableDictionary alloc]init];
                
                [dataMuDic setObject:@1 forKey:@"isShare"];
                
                
                NSString *data = [self dictionaryToJson:dataMuDic];
                
                resolve(data);
            }
            else {
                NSLog(@"response data is %@",result);
            }
        }
    }];
}

@end
