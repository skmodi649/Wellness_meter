package com.example.wellnesmeter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {
    private Context context;
    private List<CountryModel> list;
    private List<CountryModel> listFiltered;


    public MyCustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item,countryModelList);

        this.context = context;
        this.list = countryModelList;
        this.listFiltered = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);

        tvCountryName.setText(listFiltered.get(position).getCountry());

        return view;
    }

    @Override
    public int getCount() {
        return listFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return listFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.count = list.size();
                    filterResults.values = list;
                }else {
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for (CountryModel itemsModel : list) {
                        if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }

                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listFiltered = (List<CountryModel>) filterResults.values;
                AffectedCountries.countryModelList = (List<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
