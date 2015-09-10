package com.jmendez.mercadolibre.architecture;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by jormendez on 9/10/15.
 */
public class MercadoLibrePrefs {

  private static final String PREFS_NAME = "mercado_libre_prefs";

  private static final String PREF_SEARCH_HISTORY = "search_history";

  private static SharedPreferences preferences;

  public static void init(Context context) {
    preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
  }

  public static void addToSearchHistory(String query) {
    Set<String> newSearchHistory = new LinkedHashSet<>();
    newSearchHistory.add(query);
    Set<String> oldSearchHistory = preferences.getStringSet(PREF_SEARCH_HISTORY, new LinkedHashSet<String>());
    newSearchHistory.addAll(oldSearchHistory);
    preferences.edit().putStringSet(PREF_SEARCH_HISTORY, newSearchHistory).commit();
  }

  public static Set<String> getSearchHistory() {
    return preferences.getStringSet(PREF_SEARCH_HISTORY, new LinkedHashSet<String>());
  }

}
