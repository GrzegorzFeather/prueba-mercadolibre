package com.jmendez.mercadolibre.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.jmendez.mercadolibre.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = HomeActivity.class.getSimpleName();

  @Bind(R.id.edit_search) EditText mSearchBox;
  @Bind(R.id.recycler_search_results) RecyclerView mRecyclerSearchResults;

  // RecyclerView Components
  private RecyclerView.Adapter mSearchResultsAdapter;
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

    mRecyclerSearchResults.setHasFixedSize(true);
    mRecyclerSearchResults.setLayoutManager(mSearchResultsLayoutManager);

    mSearchBox.addTextChangedListener(new TextWatcher() {

      @Override
      public void afterTextChanged(Editable s) {
        handleTextChanged(s.toString());
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) { }
    });
  }

  private void handleTextChanged(String query) {
    
  }

}
