package com.khupearl.smp.api;

import com.khupearl.smp.mentee.Mentee;
import com.khupearl.smp.mentor.Mentor;
import com.khupearl.smp.wbs.Work;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Login.php")
    Call<Mentee> loginMentee(
            @Field("email") String name,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("Register_mentee.php")
    Call<Mentee> RegisterMentee(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password,
            @Field("major") String major,
            @Field("student_id") int student_id
    );
    @FormUrlEncoded
    @POST("Register_mentor.php")
    Call<Mentor> RegisterMentor(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("wbs_team.php")
    Call<List<Work>> getWbsListByTeam(
            @Field("fk_team") String fk_team
    );

    @FormUrlEncoded
    @POST("wbs_id.php")
    Call<Work> getWorkById(
            @Field("id") int id
    );

}

//    다음에 쓰려고 적어둠 (사실 잘못적었는데 지우기 아까움)
//
//    @Query("fk_team") String fk_team,
//    @Query("title") String title,
//    @Query("content") String content,
//    @Query("field") String field,