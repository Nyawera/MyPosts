package dev.nawera.nawerasposts
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.nawera.nawerasposts.databinding.PostListItemBinding

class PostRvAdapter(var postlist:List<Post>): RecyclerView.Adapter<postViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postViewHolder{
        var binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return postViewHolder(binding)
    }

    override fun onBindViewHolder(holder: postViewHolder, position: Int) {
        var currentPost = postlist.get(position)
        val postBinding = holder.binding
        postBinding.tvUserId.text = currentPost.userId.toString()
        postBinding.tvId.text = currentPost.id.toString()
        postBinding.tvTitle.text = currentPost.title
        postBinding.tvBody.text = currentPost.body
        val context=holder.itemView.context
        holder.binding.cvPosts.setOnClickListener{
            val intent=Intent(context,CommentsActivity::class.java)
            intent.putExtra("POST_ID",currentPost.id)

            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return postlist.size


    }
}
class postViewHolder(var binding:PostListItemBinding):RecyclerView.ViewHolder(binding.root){


}