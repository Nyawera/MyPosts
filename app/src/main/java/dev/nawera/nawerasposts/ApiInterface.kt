package dev.nawera.nawerasposts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/posts/{postsId}")
    fun getPostById(@Path("postId")postId:Int): Call<Post>

    @GET("/posts")
    fun getComments(): Call<List<Comment>>

}
