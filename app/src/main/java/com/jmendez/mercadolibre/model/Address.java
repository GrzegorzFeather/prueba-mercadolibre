package com.jmendez.mercadolibre.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jormendez on 9/10/15.
 */
public class Address {

  @SerializedName("state_id") private String mStateId;
  @SerializedName("state_name") private String mStateName;
  @SerializedName("city_id") private String mCityId;
  @SerializedName("city_name") private String mCityName;

  public String getStateName() {
    return mStateName;
  }

  public String getCityName() {
    return mCityName;
  }
}
