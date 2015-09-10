package com.jmendez.mercadolibre.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmendez.mercadolibre.R;
import com.jmendez.mercadolibre.architecture.MercadoLibreAPI;
import com.jmendez.mercadolibre.model.SearchResponse;
import com.jmendez.mercadolibre.util.MercadoLibreLogger;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = HomeActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }

  @Override
  protected void onResume() {
    super.onResume();
    Call<SearchResponse> call = MercadoLibreAPI.getService().search("computadora");
    call.enqueue(new Callback<SearchResponse>() {
      @Override
      public void onResponse(Response<SearchResponse> response) {
        MercadoLibreLogger.logDebug(TAG, response.body().toString());
      }

      @Override
      public void onFailure(Throwable t) {
        MercadoLibreLogger.logError(TAG, "Error", t);
      }
    });
  }
}
