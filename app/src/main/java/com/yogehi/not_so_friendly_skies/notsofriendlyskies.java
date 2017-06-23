package com.yogehi.not_so_friendly_skies;

import android.content.Context;

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
        /**XposedBridge.log("==United app loaded==");**/

        Class<?> UnifiedPlayerActivity = XposedHelpers.findClass("com.united.mobile.android.activities.UnifiedPlayer.UnifiedPlayerActivity", lpparam.classLoader);
        /**XposedBridge.log("=class loaded - United UnifiedPlayerActivity");**/

        Class<?> SecurityUtilsActivity = XposedHelpers.findClass("com.ideanovatech.inplay.utils.SecurityUtils", lpparam.classLoader);
        /**XposedBridge.log("=class loaded - United SecurityUtilsActivity");**/

        findAndHookMethod(UnifiedPlayerActivity, "isAboveMarshmallow", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: UnifiedPlayerActivity - isAboveMarshmallow");
                XposedBridge.log("-UnifiedPlayer asked if you're running above Marshmallow. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(UnifiedPlayerActivity, "isAndroidNDevPre", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: UnifiedPlayerActivity - isAndroidNDevPre");
                XposedBridge.log("-UnifiedPlayer asked if you're running Nougat. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(SecurityUtilsActivity, "isDevelopmentModeEnabled", Context.class, new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: SecurityUtilsActivity - isDevelopmentModeEnabled");
                XposedBridge.log("-SecurityUtils asked if Development Mode is on. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(SecurityUtilsActivity, "isDeviceRooted", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: SecurityUtilsActivity - isDeviceRooted");
                XposedBridge.log("-SecurityUtils asked if your device is rooted. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(SecurityUtilsActivity, "checkRootMethod1", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: SecurityUtilsActivity - checkRootMethod1");
                XposedBridge.log("-SecurityUtils asked to invoke checkRootMethod1. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(SecurityUtilsActivity, "checkRootMethod2", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: SecurityUtilsActivity - checkRootMethod2");
                XposedBridge.log("-SecurityUtils asked to invoke checkRootMethod2. I told it 'NO!'");**/
                return false;
            }
        });

        findAndHookMethod(SecurityUtilsActivity, "checkRootMethod3", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
                /**XposedBridge.log(" ");
                XposedBridge.log("=method hooked: SecurityUtilsActivity - checkRootMethod3");
                XposedBridge.log("-SecurityUtils asked to invoke checkRootMethod3. I told it 'NO!'");**/
                return false;
            }
        });

    }
}