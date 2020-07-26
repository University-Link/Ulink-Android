package com.example.ulink.repository

import retrofit2.Call
import retrofit2.http.*

interface RequestInterface {
    // GET : get
    // POST : add
    // PUT : update
    // DELETE : delete

    //User
    //유저 로그인

    @POST("/user/signin")
    fun requestLogin(
        @Body body : RequestLogin
    ) : Call<ResponseLogin>

    //Schedule
    //시간표 - 메인 시간표 조회
    @GET("/schedule/main")
    fun getMainTimeTable(
            @Header("token") token : String
    ) : Call<ResponseMainTimeTable>

    //시간표 만들기
    @POST("/schedule")
    fun addTimeTable(
            @Header("token") token: String,
            @Body body : RequestAddTimeTable
    ) : Call<ResponseAddTimeTable>

    //시간표 - 일정 상세정보(통합) 조회
    @GET("/schedule/{idx}")
    fun getTimeTableWithId(
            @Header("token") token: String,
            @Path("idx") number : Int
    ) : Call<ResponseGetTimeTableWithId>

    //시간표 - 일정 상세정보(통합) 조회
    @GET("/schedule/specific/{idx}")
    fun getSpecificTimeTableWithId(
            @Header("token") token: String,
            @Path("idx") idx : String,
            @Query("isSubject") isSubject : Boolean
    ) : Call<ResponseTimeTable>

    //시간표 - 메인 시간표 이름 수정(변경)
    @PUT("/schedule/main/{idx}")
    fun updateMainTimeTableName(
        @Header("token") token: String,
        @Path("idx") idx: String
    ) : Call<ResponseTimeTable>

    //시간표 - 일정 삭제(통합)
    @DELETE("/schedule/specific/{idx}")
    fun deleteNotice(
            @Header("token") token: String,
            @Path("idx") idx : String,
            @Query("isSubject") isSubject : Boolean // 학교 true, 개인 false
    ) : Call<ResponseTimeTable>

    //시간표 - 메인 시간표 수정(변경)
    @PUT("/schedule/main/{idx}")
    fun updateMainTimeTable(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseupdateMainTimeTable>

    //메인 시간표 삭제하기
    @DELETE("/schedule/main/{idx}")
    fun deleteMainTimeTable(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponsedeleteMainTimeTable>

    //시간표 - 시간표 이름 수정(변경)
    @PUT("/schedule/name/{idx}")
    fun updateTimeTableName(
        @Header("token") token : String,
        @Path("idx") idx : Int,
        @Body body: RequestupdateTimeTableName
    ) : Call<ResponseupdateTimeTableName>

    //모든 학기 시간표 목록 가져오기
    @GET("/schedule/list")
    fun getAllTimeTableList(
            @Header("token") token: String
    ) : Call<ResponseGetAllTimeTableList>

    //특정 학기 시간표 목록 가져오기
    @GET("/schedule/list")
    fun getTimeTableList(
            @Header("token") token: String,
            @Query ("semester") semester: String
    ) : Call<ResponseGetTimeTableList>

    //시간표 - 개인일정 만들기
    @POST("/schedule/personal")
    fun addPersonalPlan(
            @Header("token") token: String,
            @Body body : RequestAddPersonalPlan
    ) : Call<ResponseAddPersonalPlan>

    //시간표 - 학교수업일정 만들기
    @POST("/schedule/school")
    fun addSchoolPlan(
            @Header("token") token: String,
            @Body body : RequestAddSchoolPlan
    ) : Call<ResponseAddSchoolPlan>

    //특정 시간표 - 개인일정 상세정보 조회
    @GET("/schedule/personal/{idx}")
    fun getPersonalPlan(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseTimeTable>

    //특정 시간표 - 개인일정 수정
    @PUT("/schedule/personal/{idx}")
    fun updatePersonalPlan(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseTimeTable>

    //특정 시간표 - 개인일정 삭제
    @DELETE("/schedule/personal/{idx}")
    fun deletePersonalPlan(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseTimeTable>

    //특정 시간표 - 학교수업일정 상세정보 조회
    @GET("/schedule/school/{idx}")
    fun getSchoolPlan(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseTimeTable>

    //특정 시간표 - 학교수업일정 삭제
    @DELETE("/schedule/school/{idx}")
    fun deleteSchoolPlan(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseTimeTable>

    //일정 색상 변경
    @PUT("/schedule/specific/{idx}")
    fun updateChangeColor(
        @Header("token") token : String,
        @Path("idx") idx : String,
        @Query("isSubject") isSubject : Boolean,
        @Body body : RequestChangeColor
    ) : Call<ResponseChangeColor>

    //Chatting
    //채팅방 리스트 조회
    @GET("/chat")
    fun getChatList(
            @Header("token") token : String
    ) : Call<ResponseChatting>

    //Notice
    //메인 스케줄의 모든 공지 가져오기
    @GET("/notice")
    fun getAllNotice(
            @Header("token") token : String,
            @Query("start") start : String,
            @Query("end") end : String
    ) : Call<ResponseCalendar>

    //특정 과목의 공지 가져오기
    @GET("/notice/subject/{idx}")
    fun getClassNotice(
            @Header("token") token : String,
            @Path("idx") idx : String
    ) : Call<ResponseGetClassNotice>

    //공지 등록하기
    @POST("/notice/subject/{idx}")
    fun registerNotice(
            @Header("token") token : String,
            @Path("idx") idx : String,
            @Body body : RequestRegisterNotice
    ) : Call<ResponseRegisterNotice>

    //공지 상세조회
    @GET("/notice/{idx}")
    fun getSpecificNotice(
            @Header("token") token : String,
            @Path("idx") idx : String
    ) : Call<ResponseSpecificNotice>

    //공지 수정(업데이트)
    @PUT("/notice/{idx}")
    fun updateNotice(
            @Header("token") token : String,
            @Path("idx") idx : String,
            @Body body : RequestRegisterNotice
    ) : Call<ResponseUpdateNotice>

    //Cart
    //장바구니(후보) 목록 조회
    @GET("/cart")
    fun getCartList(
            @Header("token") token : String,
            @Query("semester") semester : String
    ) : Call<ResponseGetCartList>

    //장바구니 과목 추가하기
    @POST("/cart")
    fun addCartList(
            @Header("token") token : String,
            @Body body : RequestaddCartList
    ) : Call<ResponseaddCartList>

    //장바구니 과목 삭제하기
    @HTTP(method = "DELETE", path="/cart/{idx}",hasBody=true)
    fun deleteCartList(
            @Header("token") token : String,
            @Path("idx") idx : String,
            @Body body : RequestDeleteCartList
    ) : Call<ResponseDeleteCartList>

    //Subject
    //수업(과목) 목록 조회
    @GET("/subject")
    fun getSubjectList(
            @Header("token") token : String
    ) : Call<ResponseSubject>

    //학년 검색
    @GET("/subject")
    fun getSubjectByGrade(
        @Header("token") token :String,
        @Query("grade") grade : Int
    ) : Call<ResponseGetSubjectByGrade>

    //수업(과목)검색 자동완성 - 키워드
    @GET("/subject/recommend")
    fun getSubjectWithKeyword(
            @Header("token") token : String,
            @Query("name") name : String
    ) : Call<ResponsegetSubjectWithWord>

    //수업(과목)검색 자동완성 - 단어
    @GET("/subject/search")
    fun getSubjectWithWord(
            @Header("token") token : String,
            @Query("name") name : String
    ) : Call<ResponsegetSubjectWithWord>

    @GET("/subject/recommend")
    fun getSubjectRecommendWithKeyword(
            @Header("token") token: String,
            @Query("name") name: String
    ) : Call<ResponsegetSubjectWithKeyWord>

    @DELETE("/notice/{idx}")
    fun deleteNoticeWithIdx(
            @Header("token") token: String,
            @Path("idx") idx : String
    ) : Call<ResponseDeleteNoticeWithIdx>

    @DELETE("/schedule/specific/{idx}")
    fun deleteSubjectWithIdx(
            @Header("token") token : String,
            @Path("idx") idx : String,
            @Query("isSubject") isSubject: Boolean
    )
}