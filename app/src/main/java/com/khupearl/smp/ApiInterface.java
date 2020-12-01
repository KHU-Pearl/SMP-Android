package com.khupearl.smp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;

public interface ApiInterface {
    @GET("Login.php")
    Call<Mentee> getMentee(
            @Query("email") String email,
            @Query("fk_team") String fk_team,
            @Query("name") String name,
            @Query("password") String password,
            @Query("major") String major,
            @Query("student_id") String student_id
    );
    @FormUrlEncoded
    @POST("Login.php")
    Call<Mentee> loginMentee(
            @Field("email") String name,
            @Field("password") String password
    );


}
