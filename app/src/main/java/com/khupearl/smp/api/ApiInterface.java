package com.khupearl.smp.api;

import com.khupearl.smp.mentee.Mentee;
import com.khupearl.smp.mentee.Progress;
import com.khupearl.smp.mentor.Mentor;
import com.khupearl.smp.mentor.team.Team;
import com.khupearl.smp.notice.Notice;
import com.khupearl.smp.wbs.Work;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Login_mentee.php")
    Call<Mentee> loginMentee(
            @Field("email") String name,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("Login_mentor.php")
    Call<Mentor> loginMentor(
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
    @POST("Register_mentee_possible.php")
    Call<Mentee> RegisterMenteePossible(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("Register_mentor.php")
    Call<Mentor> RegisterMentor(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("Register_mentor_possible.php")
    Call<Mentor> RegisterMentorPossible(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("Register_team_member_possible.php")
    Call<Mentee> RegisterTeamMemberPossible(
            @Field("email") String email,
            @Field("fk_team") String fk_team
    );

//    @FormUrlEncoded
//    @POST("addteam_name_possible.php")
//    Call<Mentee> AddTeamMemberPossible(
//            @Field("email") String email
//    );
    @FormUrlEncoded
    @POST("add_team.php")
    Call<Team> AddTeam(
            @Field("name") String name,
            @Field("title") String title,
            @Field("content") String content
    );
    @FormUrlEncoded
    @POST("addteam_mentor.php")
    Call<Team> AddTeamMentor(
            @Field("name") String name,
            @Field("title") String title
    );
    @FormUrlEncoded
    @POST("addteam_mentee.php")
    Call<Mentee> AddTeamMentee(
            @Field("email") String email,
            @Field("fk_team") String fk_team
    );


    @FormUrlEncoded
    @POST("addteam_name_possible.php")
    Call<Team> AddTeamNamePossible(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("add_work.php")
    Call<Work> AddWork(
            @Field("fk_team") String fk_team,
            @Field("title") String title,
            @Field("content") String content,
            @Field("field") String field,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("add_work_members.php")
    Call<Work> AddWorkMembers(
            @Field("wbs_id") int wbs_id,
            @Field("mentee_email") String meentee_email
    );

    @FormUrlEncoded
    @POST("mentor_teamlist.php")
    Call<Mentor> getTeamListByMentor(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("wbs_team.php")
    Call<List<Work>> getWbsList(
            @Field("fk_team") String fk_team,
            @Field("state") String state
    );

    @FormUrlEncoded
    @POST("wbs_id.php")
    Call<Work> getWorkById(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("wbs_change_state.php")
    Call<Work> setState(
            @Field("id") int id,
            @Field("state") String state
    );

    @FormUrlEncoded
    @POST("Get_team_info.php")
    Call<Team> getTeamInfo(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("Get_team_count_wbs.php")
    Call<Progress> getTeamCountWbs(
            @Field("fk_team") String fk_team
    );

    @FormUrlEncoded
    @POST("mentee_notice_list.php")
    Call<Mentee> mentee_notice_list(
            @Field("fk_team") String fk_team
    );


}