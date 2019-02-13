
package com.sieuthai;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.os.PowerManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.view.WindowManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNUnlockDeviceModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNUnlockDeviceModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNUnlockDevice";
  }

  @ReactMethod
  public void unlock() {
    try {
      final Activity activity = getCurrentActivity();
      KeyguardManager keyguardManager = (KeyguardManager) reactContext.getSystemService(Context.KEYGUARD_SERVICE);
      KeyguardLock keyguardLock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
      keyguardLock.disableKeyguard();
      
      // Unlock the screen
      PowerManager powerManager = (PowerManager) reactContext.getSystemService(Context.POWER_SERVICE);
      PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK
              | PowerManager.ACQUIRE_CAUSES_WAKEUP
              | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "RNUnlockDeviceModule");
      wakeLock.acquire();

      activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    } catch (Exception e) {
    }
  }
}