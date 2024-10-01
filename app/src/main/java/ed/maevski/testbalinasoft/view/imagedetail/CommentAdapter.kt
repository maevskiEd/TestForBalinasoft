package ed.maevski.testbalinasoft.view.imagedetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ed.maevski.testbalinasoft.databinding.ItemCommentBinding
import ed.maevski.testbalinasoft.domain.models.Comment
import ed.maevski.testbalinasoft.utils.toTextDateByFormat

class CommentAdapter(
    private val onLongClick: (imageId: Int,commentId: Int) -> Unit,
) : RecyclerView.Adapter<CommentAdapter.InnerCommentViewHolder>() {
    private var comments: MutableList<Comment> = mutableListOf()

    inner class InnerCommentViewHolder(binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.root
        var text = binding.tvCommentText
        var date = binding.tvCommentDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerCommentViewHolder {
        val binding = ItemCommentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerCommentViewHolder, position: Int) {

        holder.text.text = comments[position].text
        holder.date.text = comments[position].date.toTextDateByFormat("yyyy-MM-dd")

//        holder.root.setOnClickListener {
//            comments[position].commentId?.let { id -> onLongClick(id) }
//        }
    }

    override fun getItemCount(): Int = comments.size

//    fun getItem(position: Int?): Image? {
//        return if (position == null) null
//        else images[position]
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(images: List<Comment>) {
        this.comments = images.toMutableList()
        notifyDataSetChanged()
    }
}