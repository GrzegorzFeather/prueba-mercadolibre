package com.jmendez.mercadolibre.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jmendez.mercadolibre.R;
import com.jmendez.mercadolibre.architecture.MercadoLibreAPI;
import com.jmendez.mercadolibre.model.SearchResponse;
import com.jmendez.mercadolibre.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = HomeActivity.class.getSimpleName();

  @Bind(R.id.edit_search) EditText mSearchBox;
  @Bind(R.id.recycler_search_results) RecyclerView mRecyclerSearchResults;
  @Bind(R.id.lbl_no_results) TextView mLblNoResults;

  // RecyclerView Components
  private SearchResultsAdapter mSearchResultsAdapter;
  private RecyclerView.LayoutManager mSearchResultsLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    setupViews();
  }

  private void setupViews() {
    ButterKnife.bind(this);

    //RecyclerView setup
    mSearchResultsLayoutManager = new LinearLayoutManager(this);
    mSearchResultsAdapter = new SearchResultsAdapter(this);

    mRecyclerSearchResults.setHasFixedSize(true);
    mRecyclerSearchResults.setLayoutManager(mSearchResultsLayoutManager);
    mRecyclerSearchResults.setAdapter(mSearchResultsAdapter);

    //Search Box setup
    mSearchBox.addTextChangedListener(new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {
        handleTextChanged(s.toString());
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }
    });
  }

  private void handleTextChanged(String query) {
    if(!TextUtils.isEmpty(query)) {
      MercadoLibreAPI.getService().search(query).enqueue(mSearchRequestCallback);
    }
  }

  private void setEmptyResults() {
    showOrHideEmptyMessage(true);
    mSearchResultsAdapter.setSearchResults(new ArrayList<SearchResult>());
  }

  private void showSearchResults(List<SearchResult> searchResults) {
    showOrHideEmptyMessage(false);
    mSearchResultsAdapter.setSearchResults(searchResults);
  }

  private void showOrHideEmptyMessage(boolean shouldShowEmpty) {
    mRecyclerSearchResults.setVisibility(shouldShowEmpty ? View.GONE : View.VISIBLE);
    mLblNoResults.setVisibility(shouldShowEmpty ? View.VISIBLE : View.GONE);
  }

  private Callback<SearchResponse> mSearchRequestCallback = new Callback<SearchResponse>() {
    @Override
    public void onResponse(Response<SearchResponse> response) {
      if (response == null || response.body() == null
          || response.body().getResults() == null
          || response.body().getResults().isEmpty()) {
        setEmptyResults();
      } else {
        showSearchResults(response.body().getResults());
      }
    }

    @Override
    public void onFailure(Throwable t) {
      setEmptyResults();
      Toast.makeText(HomeActivity.this, getString(R.string.service_failed), Toast.LENGTH_SHORT)
          .show();
    }
  };

}
