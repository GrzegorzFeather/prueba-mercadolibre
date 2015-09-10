package com.jmendez.mercadolibre.architecture;

import com.jmendez.mercadolibre.model.SearchResponse;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jormendez on 9/10/15.
 */
public class MercadoLibreAPI {

  private static final String API_URL = "https://api.mercadolibre.com/sites/MLM/";

  private static MercadoLibreAPI mInstance = null;

  public static MercadoLibreService getService() {
    if(mInstance == null) {
      mInstance = new MercadoLibreAPI();
    }

    return mInstance.mAPIService;
  }

  private final MercadoLibreService mAPIService;

  private MercadoLibreAPI() {
    mAPIService = new Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MercadoLibreService.class);
  }

  public interface MercadoLibreService {

    @GET("search")
    Call<SearchResponse> search(@Query("q") String query);

  }

}
