package com.jmendez.mercadolibre.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jormendez on 9/10/15.
 */
public class Paging {

  @SerializedName("total") private int mTotal;
  @SerializedName("offset") private int mOffset;
  @SerializedName("limit") private int mLimit;

}
