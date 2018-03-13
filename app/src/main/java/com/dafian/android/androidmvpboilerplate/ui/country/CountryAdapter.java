package com.dafian.android.androidmvpboilerplate.ui.country;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafian.android.androidmvpboilerplate.R;
import com.dafian.android.androidmvpboilerplate.base.BaseAdapter;
import com.dafian.android.androidmvpboilerplate.data.entity.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Dafian on 13/03/18
 */
public class CountryAdapter extends BaseAdapter<CountryAdapter.CountryHolder> {

    private Context context;
    private List<Country> countryList;

    CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    class CountryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_flag)
        ImageView ivFlag;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_native_name)
        TextView tvNativeName;
        @BindView(R.id.tv_capital)
        TextView tvCapital;
        @BindView(R.id.tv_region_sub_region)
        TextView tvRegionSubRegion;
        @BindView(R.id.tv_population)
        TextView tvPopulation;

        CountryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_country, parent, false);
        return new CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        Country country = countryList.get(position);

        String regionSubRegion = country.getRegion() + " / " + country.getSubRegion();
        String people = country.getPopulation() + " People";

        holder.tvName.setText(country.getName());
        holder.tvNativeName.setText(country.getNativeName());
        holder.tvCapital.setText(country.getCapital());
        holder.tvRegionSubRegion.setText(regionSubRegion);
        holder.tvPopulation.setText(people);

        loadImagesFromURL(context, holder.ivFlag, country.getFlag());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}
