package com.jmendez.mercadolibre.util;

import android.util.Log;

import com.jmendez.mercadolibre.BuildConfig;

/**
 * Created by jormendez on 9/10/15.
 */
public class MercadoLibreLogger {

  public static void logDebug(String tag, String message) {
    if(BuildConfig.DEBUG) {
      Log.d(tag, message);
    }
  }

  public static void logWarning(String tag, String message) {
    if(BuildConfig.DEBUG) {
      Log.w(tag, message);
    }
  }

  public static void logError(String tag, String message, Throwable e) {
    if(BuildConfig.DEBUG) {
      Log.e(tag, message, e);
    }
  }

}
