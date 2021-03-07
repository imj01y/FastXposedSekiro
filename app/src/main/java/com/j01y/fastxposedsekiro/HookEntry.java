package com.j01y.fastxposedsekiro;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.j01y.fastxposedsekiro.Utils.Utils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookLoadPackage {
    private static final String TAG = Utils.TAG;

    private static Context context;
    private static ClassLoader classLoader;
    private static boolean RegisterState = false;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d(TAG, String.format("hook packageName:%s  processName:%s", lpparam.packageName, lpparam.processName));
        if (lpparam.processName.equals(lpparam.packageName)) {
            XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    context = (Context) param.args[0];  // 获取context
                    classLoader = ((Context) param.args[0]).getClassLoader();  // 获取classLoader

                }
            });
        }
    }
}
