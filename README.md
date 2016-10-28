# Not-So-Friendly-Skies

TEMP README UNTIL I HAVE MORE TIME TO MAKE A PROPER README

XPosed module to assist Android devices to use United's in-flight entertainment system.

On some United Airlines flights, the company offers their entertainment system via the aircraft's internal WiFi system. The user has the option to connect their person device to the aircraft's WiFi system and watch streaming content that is available on the aircraft. This is achieved by using United's mobile application.

United also contracts the WiFi system and the entertainment system to a third party contractor. Based on what I found by decompiling the Android application (and looking briefly at the code on the second half of a 5 hour flight), there are (so far) two companies that United relies on for their inflight entertainment system: Thales Group and Panasonic.

It was discovered that if the aircraft is using the Thales Group system, then the application makes two checks before it allows the customer to stream the in-flight entertainment:

* checks for root access
* checks to see if the customer is using Android version 6.0 (Marshmallow) or above

The root check can already be bypassed by installing the Xposed module 'Root Cloak' http://repo.xposed.info/module/com.devadvance.rootcloak2. For that reason, 'Not-So-Friendly-Skies' will not address the root check issue. Instead, it addresses the Android version check issue.

Specifically, the application makes the following check:

```
protected void showPlayer() {
        Ensighten.evaluateEvent(this, "showPlayer", null);
        this.shouldExecuteOnResume = true;
        if (isThales(this.mVideo) && isMarshmallow()) {
            alert(Integer.valueOf(18), ErrorCodes.INT_0018_MESSAGE);
        } else if (isThales(this.mVideo)) {
            getPDEAuthorizationCall();
        } else {
            playerFragment(new VideoInfo(this.mVideo, this.mSubtitleUrl));
        }
    }
```
    
The 'isMarshmallow' method is a boolean that is dependant on the detected API version of the Android device which can be seen below:

```
private boolean isMarshmallow() {
        Ensighten.evaluateEvent(this, "isMarshmallow", null);
        return VERSION.SDK_INT > 22;
    }
```
    
This XPosed module forces the 'isMarshmallow' method to always return 'false' which results in the 'showPlayer' method to function as if the device wasn't running Marshmallow. This forces the player to function normally and lets the custoemr stream the in-flight content.
