package com.jmendez.mercadolibre.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jormendez on 9/10/15.
 */
public class SearchResult {

  /**
   * {
   "id": "MLM511371155",
   "site_id": "MLM",
   "title": "Ipod Touch 5g 16gb A1421 Gris Nuevo, Garantía Y Envio",
   "subtitle": null,
   "seller": {},
   "price": 3499,
   "currency_id": "MXN",
   "available_quantity": 1,
   "sold_quantity": 16,
   "buying_mode": "buy_it_now",
   "listing_type_id": "gold_pro",
   "stop_time": "2015-10-19T19:23:52.000Z",
   "condition": "new",
   "permalink": "http://articulo.mercadolibre.com.mx/MLM-511371155-ipod-touch-5g-16gb-a1421-gris-nuevo-garantia-y-envio-_JM",
   "thumbnail": "http://mlm-s1-p.mlstatic.com/354201-MLM20292896857_052015-I.jpg",
   "accepts_mercadopago": true,
   "installments": {},
   "address": {},
   "shipping": {},
   "seller_address": {},
   "attributes": [
   ],
   "differential_pricing": {},
   "original_price": null,
   "category_id": "MLM32614",
   "official_store_id": null
   }
   */

  @SerializedName("id") private String mId;
  @SerializedName("site_id") private String mSiteId;
  @SerializedName("title") private String mTitle;
  @SerializedName("price") private float mPrice;
  @SerializedName("thumbnail") private String mThumbnail;

}
