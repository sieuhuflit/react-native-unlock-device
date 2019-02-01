
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

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNUnlockDeviceModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private Promise mUnlockPromise;

  public RNUnlockDeviceModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNUnlockDevice";
  }

  @Override
  public void unlock(final Promise promise) {
    
    mUnlockPromise = promise;
    try {
      KeyguardManager keyguardManager = (KeyguardManager) reactContext.getSystemService(Context.KEYGUARD_SERVICE);
      KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("MyKeyguardLock");
      keyguardLock.disableKeyguard();
      
      // Unlock the screen
      PowerManager powerManager = (PowerManager) reactContext.getSystemService(Context.POWER_SERVICE);
      PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK
              | PowerManager.ACQUIRE_CAUSES_WAKEUP
              | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
      wakeLock.acquire();
      promise.resolve("Success");
    } catch (Exception e) {
      mUnlockPromise.reject(e);
      mUnlockPromise = null;
    }
  }
}