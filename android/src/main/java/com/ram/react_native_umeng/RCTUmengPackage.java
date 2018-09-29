package com.ram.react_native_umeng;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>com.ram.react_native_umeng
 * <p>Created by GaoXuanJi on 2017/11/27
 * <p>Des          :
 * <p>Modify Author:
 * <p>Modify Date  :
 */
public class RCTUmengPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new RCTUmengModule(reactContext));
        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
