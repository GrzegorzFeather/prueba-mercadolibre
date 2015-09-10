package com.jmendez.mercadolibre.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jmendez.mercadolibre.R;
import com.jmendez.mercadolibre.architecture.MercadoLibreAPI;
import com.jmendez.mercadolibre.architecture.MercadoLibrePrefs;
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

  @Bind(R.id.layout_content) View mLayoutContent;
  @Bind(R.id.edit_search) AutoCompleteTextView mSearchBox;
  @Bind(R.id.recycler_search_results) RecyclerView mRecyclerSearchResults;
  @Bind(R.id.lbl_no_results) TextView mLblNoResults;
  @Bind(R.id.progress) View mProgress;

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
    setupSearchResults();
    setupSearchBox();
    setupSearchHistory();
  }

  private void setupSearchResults() {
    //RecyclerView setup
    mSearchResultsLayoutManager = new LinearLayoutManager(this);
    mSearchResultsAdapter = new SearchResultsAdapter(this);

    mRecyclerSearchResults.setHasFixedSize(true);
    mRecyclerSearchResults.setLayoutManager(mSearchResultsLayoutManager);
    mRecyclerSearchResults.setAdapter(mSearchResultsAdapter);
  }

  private void setupSearchBox() {
    //Search Box setup
    mSearchBox.setThreshold(0);
    mSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          handleTextChanged(mSearchBox.getText().toString());
          return true;
        }
        return false;
      }
    });
    mSearchBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          showSearchHistory();
        }
      }
    });
    mSearchBox.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        showSearchHistory();
        return false;
      }
    });
    mSearchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String query = (String) mSearchBox.getAdapter().getItem(position);
        handleTextChanged(query);
      }
    });
  }

  private void setupSearchHistory() {

  }

  private void handleTextChanged(String query) {
    if(TextUtils.isEmpty(query)) {
      return;
    }

    mSearchBox.dismissDropDown();
    startProgress();
    MercadoLibrePrefs.addToSearchHistory(query);
    showSearchHistory();
    MercadoLibreAPI.getService().search(query).enqueue(mSearchRequestCallback);
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(mLayoutContent.getWindowToken(), 0);
  }

  private void showSearchHistory() {
    List<String> searchHistory = new ArrayList<>(MercadoLibrePrefs.getSearchHistory());
    ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(this,
        android.R.layout.simple_spinner_item, searchHistory);
    mSearchBox.setAdapter(searchAdapter);
    mSearchBox.showDropDown();
  }

  private void startProgress() {
    mRecyclerSearchResults.setVisibility(View.GONE);
    mLblNoResults.setVisibility(View.GONE);
    mProgress.setVisibility(View.VISIBLE);
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
    mProgress.setVisibility(View.GONE);
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
