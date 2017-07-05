# Not-So-Friendly-Skies

XPosed module to assist Android devices to use United's in-flight entertainment system.

On some United Airlines flights, the company offers their entertainment system via the aircraft's internal WiFi system. The user has the option to connect their person device to the aircraft's WiFi system and watch streaming content that is available on the aircraft. This is achieved by using United's mobile application.

United also contracts the WiFi system and the entertainment system to a third party contractor. Based on what I found by decompiling the Android application (and looking briefly at the code on the second half of a 5 hour flight), there are (so far) two companies that United relies on for their inflight entertainment system: Thales Group and Panasonic.

It was discovered that if the aircraft is using the Thales Group system, then the application makes three checks before it allows the customer to stream the in-flight entertainment:

* checks for root access
* checks to see if the customer is using Android version 6.0 (Marshmallow) or above
* checks to see if developent mode is enabled

Specifically, the application makes the following checks:

```
#checks for Marshmallow
private boolean isAboveMarshmallow() {
        Ensighten.evaluateEvent(this, "isAboveMarshmallow", null);
        return VERSION.SDK_INT > 22;
    }
    
#checks for Nougat
private boolean isAndroidNDevPre() {
        Ensighten.evaluateEvent(this, "isAndroidNDevPre", null);
        return VERSION.SDK_INT == 23 && "N".equals(VERSION.CODENAME);
    }
    
#checks for dev mode enabled
public static boolean isDevelopmentModeEnabled(Context context) {
        boolean z = true;
        if (VERSION.SDK_INT > 17) {
            if (Global.getInt(context.getContentResolver(), "adb_enabled", 0) != 1) {
                z = false;
            }
        } else if (Secure.getInt(context.getContentResolver(), "adb_enabled", 0) != 1) {
            z = false;
        }
        if (z) {
            Log.e(Integer.toString(2), "Debug mode turned on");
        }
        return z;
    }
    
#checks for root
public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    public static boolean checkRootMethod1() {
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            return false;
        }
        Log.e(Integer.toString(1), "Root device detected");
        return true;
    }

    public static boolean checkRootMethod2() {
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                Log.e(Integer.toString(1), "Root device detected");
            }
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkRootMethod3() {
        boolean z = new a().a(a.a) != null;
        if (z) {
            Log.e(Integer.toString(2), "Root device detected");
        }
        return z;
    }
```
    
This XPosed module forces all of the above methods to always return 'false' which results in the United player to continue functioning as normal. This module was tested on a Nexus 6 running Marshmallow while rooted.
