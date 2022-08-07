package com.example.poetryapp.Api;

import com.example.poetryapp.Response.DeleteResponse;
import com.example.poetryapp.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getPoetryApi.php") // endpoint ka address dete h Parameter m.
    // Call<> iske parameter vo dege hame jaise bhi API se response aa rha hoga hame vaisi hi Java class create karni hogi.
    //Creating Java Class :-
    Call<GetPoetryResponse> getpoetry(); //getpoetry() is function se hum Call ko call karvayege.


    // Postman m humne Body tab m form-data select kiya hua h.
    //	Hame form-data ko ApiInterface m define karna hoga.
    @FormUrlEncoded

    // Ab hum apni Pojo class(DeleteResponse) ko ApiInterface m define karne wale h :-
    @POST("deletePoetryApi.php")
    // deletepoetry() iske Parameter m vo likhna jo bhi request Parameter hum API par send kar rhe h.
    // Postman m kisi tarah ki String ko send karne k liy hum Field() ka use karte h.
    // Iske Parameter m vhi name dege jo ki hum Postman m name accept kar rhe h.
    // Hum Jitne bhi API Interface use krg unke sath @ ka sign lgana hoga.
    Call<DeleteResponse> deletepoetry(@Field("poetry_id") String poetry_id);


}
