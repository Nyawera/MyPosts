package dev.nawera.nawerasposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nawera.nawerasposts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_comments)

    var postId = 0
    lateinit var binding:ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        setupToolbar()


    }

    fun obtainPostId(){
        postId = intent.extras?.getInt("POST-_ID")?: 0
    }

    fun fetchPostById(){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPostById(postId)
       
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful){
                    var post=response.body()
                    binding.tvPostBody.text=post?.body
                    binding.tvPostTittle.text=post?.title
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })



    }
    fun setupToolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

fun fetchComments(){
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val request = apiClient.getComments()


    request.enqueue(object : Callback<List<Comment>> {
        override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
            if (response.isSuccessful){
                var comment = response.body()?: emptyList()
                displayComment(comment)
            }

        }

        override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
            Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

        }


    })
}
    fun displayComment(commentList: List<Comment>){
        val commentsAdapter=CommentsAdapter(commentList)
        binding.rvComments.layoutManager=LinearLayoutManager(this)
        binding.rvComments.adapter=commentsAdapter
    }
}