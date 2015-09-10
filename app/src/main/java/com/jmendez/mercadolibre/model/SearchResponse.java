package com.jmendez.mercadolibre.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jormendez on 9/10/15.
 */
public class SearchResponse implements MercadoLibreAPIResponse {

  @SerializedName("site_id") private String mSiteId;
  @SerializedName("query") private String mQuery;
  @SerializedName("paging") private Paging mPaging;
  @SerializedName("results") private ArrayList<SearchResult> mResults;
  @SerializedName("sort") private Sort mSort;

  public ArrayList<SearchResult> getResults() {
    return mResults;
  }

}
