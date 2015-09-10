package com.jmendez.mercadolibre.architecture;

import android.app.Application;

/**
 * Created by jormendez on 9/10/15.
 */
public class MercadoLibreApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    MercadoLibrePrefs.init(this);
  }
}
