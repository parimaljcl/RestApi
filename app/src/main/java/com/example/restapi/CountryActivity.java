package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryActivity extends AppCompatActivity {
private static final String BASE_URL="http://192.168.0.6/restaurant/";
    private ListView countryLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        countryLV=findViewById(R.id.countryLV);

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryService service=retrofit.create(CountryService.class);
        Call<List<Country>>countryList=service.getCountry();

        countryList.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.code()==200)
                {
                    List<Country>countries=response.body();
                    CountryAdapter countryAdapter=new CountryAdapter(CountryActivity.this,countries);
                    countryLV.setAdapter(countryAdapter);
                    Log.d("Tag",countries.get(0).getName());
                    //Toast.makeText(MainActivity.this,"Country: "+countries.get(0).getName(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.d("Tag",t.getMessage());
            }
        });


    }
}
