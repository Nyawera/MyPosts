package dev.nawera.nawerasposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.nawera.nawerasposts.databinding.CommentsListBinding

class CommentsAdapter(var commentsList: List<Comment>):RecyclerView.Adapter<CommentsViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var binding=CommentsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  CommentsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComments=commentsList.get(position)
        with(holder.binding){
            tvUsserId.text=currentComments.userId.toString()
            tvID.text=currentComments.id.toString()
            tvEmail.text=currentComments.email
            tvName.text=currentComments.name
            tvBoody.text=currentComments.body

        }
    }
override fun getItemCount(): Int{
    return  commentsList.size
}
}
class CommentsViewHolder(var binding: CommentsListBinding):RecyclerView.ViewHolder(binding.root){

}