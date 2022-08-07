package com.example.poetryapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poetryapp.Api.ApiClient;
import com.example.poetryapp.Api.ApiInterface;
import com.example.poetryapp.Models.PoetryModel;
import com.example.poetryapp.R;
import com.example.poetryapp.Response.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    List<PoetryModel> poetryModels;
    Context context;
    ApiInterface apiInterface;

    // Constructors :-
    public PoetryAdapter(List<PoetryModel> poetryModels, Context context) {
        this.poetryModels = poetryModels;
        this.context = context;
        // Ab hum ApiInterface ko initialize krege PoetryAdapter k constructor m :-
        Retrofit retrofit = ApiClient.getclient();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    // Methods :-
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Ye ak View return krega use hum vew m dal dege :-
        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_design, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Sara Data set krege:-
        holder.poetName.setText(poetryModels.get(position).getPoet_name());
        holder.poetry.setText(poetryModels.get(position).getPoetry_data());
        holder.date_time.setText(poetryModels.get(position).getDate_time());

        // Ab hum Delete k Button par Click event lgayege :-
        //	Us Click event par hum Delete API ko call krege.
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletepoetry(poetryModels.get(position).getId()+"" , position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView poetName, poetry, date_time;
        AppCompatButton updateBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize :-
            poetName = itemView.findViewById(R.id.textview_poetName);
            poetry = itemView.findViewById(R.id.textview_poetryData);
            date_time = itemView.findViewById(R.id.textview_poetryDateandtime);

            updateBtn = itemView.findViewById(R.id.update_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }
    }

    // Hum ak method bnayege jisme Delete API ki calling karvayege :-
    private void deletepoetry(String id , int pose){
        apiInterface.deletepoetry(id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                try {
                    if (response!=null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();

                        // Hame Delete karne k bad bar-bar apni Application ko update/Refresh karna pdega is problem ko remove karne k liy :-
                        // Agar reponse != null nhi h to hum check krege ki hamara record delete hua ki nhi.
                        if (response.body().getStatus().equals("1")){
                            poetryModels.remove(pose);
                            notifyDataSetChanged();
                        }
                    }
                }
                catch (Exception e){
                    Log.e("exp" , e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });

    }
}
