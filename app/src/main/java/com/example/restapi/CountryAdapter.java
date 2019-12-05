package com.example.restapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private Context context;
    private List<Country>countryList;

    public CountryAdapter(@NonNull Context context, List<Country> countryList) {
        super(context, R.layout.country_single,countryList);
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context); //(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.country_single,parent,false);
        TextView codeTV=convertView.findViewById(R.id.codeTV);
        TextView nameTV=convertView.findViewById(R.id.nameTV);

        codeTV.setText(countryList.get(position).getCode());
        nameTV.setText(countryList.get(position).getName());
        return convertView;

    }
}
