package dev.nawera.nawerasposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.nawera.nawerasposts.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
    }
    fun fetchPosts(){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPosts()

        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    var posts = response.body()
                    Toast.makeText(baseContext,"fetched ${posts!!.size}posts", Toast.LENGTH_LONG).show()
                    var displayPostsRvAdaptor=PostRvAdapter(posts)
                    Log.d("Task",posts.toString())
                    binding.rvDisplay.layoutManager=LinearLayoutManager(baseContext)
                    binding.rvDisplay.adapter=displayPostsRvAdaptor

                }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()



            }
        })
    }

    fun displayPosts(postList: List<Post>){
        binding.rvDisplay.layoutManager = LinearLayoutManager(this)
        var postsAdapter = PostRvAdapter(postList)
        binding.rvDisplay.adapter = postsAdapter

    }
}