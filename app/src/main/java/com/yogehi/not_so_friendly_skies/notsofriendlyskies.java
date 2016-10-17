package com.yogehi.not_so_friendly_skies;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class notsofriendlyskies implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        /** checks for United app **/
        if (!lpparam.packageName.equals("com.united.mobile.android"))
            return;
        XposedBridge.log("==United app loaded==");

        Class<?> UnifiedPlayerActivity = XposedHelpers.findClass("com.united.mobile.android.activities.UnifiedPlayer.UnifiedPlayerActivity", lpparam.classLoader);
        XposedBridge.log("=class loaded - United UnifiedPlayerActivity");

        findAndHookMethod(UnifiedPlayerActivity, "isMarshmallow", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                XposedBridge.log(" ");
                XposedBridge.log("=method hooked: UnifiedPlayerActivity - isMarshmallow");
                XposedBridge.log("-UnifiedPlayer asked if you're running Marshmallow. I told it 'NO!'");
                return false;
            }
        });

    }
}
