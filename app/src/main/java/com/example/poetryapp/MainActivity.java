package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.poetryapp.Adapter.PoetryAdapter;
import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.Response.GetPoetryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PoetryAdapter poetryAdapter;
//    List<PoetryModel> poetryModelList = new ArrayList<>();
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Passing Dummy Data :-
//        poetryModelList.add(new PoetryModel(1, "aah ko chāhiye ik umr asar hote tak \n" +
//                "\n" +
//                "kaun jiitā hai tirī zulf ke sar hote tak", "Mirza Galib", "02-may-2020"));
//
//        poetryModelList.add(new PoetryModel(1, "aah ko chāhiye ik umr asar hote tak \n" +
//                "\n" +
//                "kaun jiitā hai tirī zulf ke sar hote tak", "Mirza Galib", "02-may-2020"));
//
//        poetryModelList.add(new PoetryModel(1, "aah ko chāhiye ik umr asar hote tak \n" +
//                "\n" +
//                "kaun jiitā hai tirī zulf ke sar hote tak", "Mirza Galib", "02-may-2020"));
//
//        poetryModelList.add(new PoetryModel(1, "aah ko chāhiye ik umr asar hote tak \n" +
//                "\n" +
//                "kaun jiitā hai tirī zulf ke sar hote tak", "Mirza Galib", "02-may-2020"));

        // Calling initialization method:-
        initialization();

        // calling getdata() :-
        getdata();



    }

    // Initialize all methods :-
    private void initialization() {
        recyclerView = findViewById(R.id.poetry_recyclerView);
        Retrofit retrofit = ApiClient.getclient(); // getClient Retrofit ka object return kar rha h.
        apiInterface = retrofit.create(ApiInterface.class);
    }

    // Ab hum Adapter ko set krege =>
    // yha par List set hogi.
    private void setadapter(List<PoetryModel> poetryModels) {
        poetryAdapter = new PoetryAdapter(poetryModels, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetryAdapter);
    }

    // Hum ak method bnayege jisme Api ki calling karvayege :-
    public void getdata(){
        apiInterface.getpoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(Call<GetPoetryResponse> call, Response<GetPoetryResponse> response) {
                try {
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){
                            // Data pass to setadapter :-
                            setadapter(response.body().getData());
                        }
                        else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            // failure k case m  jo bhi message likha hoga vo ye Toast m show karva dega => response.body().getMessage()
                        }
                    }
                }
                catch (Exception e){
                    Log.e("exp" , e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<GetPoetryResponse> call, Throwable t) {
                Log.e("failure" , t.getLocalizedMessage()); // getLocalizedMessage() = jo bhi failure hoga use print kar dega.
            }
        });


    }
}