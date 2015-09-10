package com.jmendez.mercadolibre.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jmendez.mercadolibre.R;
import com.jmendez.mercadolibre.model.SearchResult;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jormendez on 9/10/15.
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

  //Preinitialized to give count of 0
  private List<SearchResult> mSearchResults = new ArrayList<>();

  private Context mContext;

  public SearchResultsAdapter(Context context) {
    mContext = context;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_search_result, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    SearchResult searchResult = mSearchResults.get(position);

    holder.mLblTitle.setText(searchResult.getTitle());
    holder.mLblLocation.setText(searchResult.getAddressAsString());

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    holder.mLblPrice.setText(currencyFormat.format(searchResult.getPrice()));

    Glide.with(mContext)
        .load(searchResult.getThumbnail())
        .placeholder(R.drawable.img_no_photo)
        .centerCrop()
        .crossFade()
        .into(holder.mImgThumbnail);
  }

  @Override
  public int getItemCount() {
    return mSearchResults.size();
  }

  public void setSearchResults(List<SearchResult> newResults) {
    mSearchResults = new ArrayList<>(newResults);
    notifyDataSetChanged();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.img_thumbnail) ImageView mImgThumbnail;
    @Bind(R.id.lbl_title) TextView mLblTitle;
    @Bind(R.id.lbl_price) TextView mLblPrice;
    @Bind(R.id.lbl_location) TextView mLblLocation;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

  }

}
