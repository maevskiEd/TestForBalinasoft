package ed.maevski.testbalinasoft.view.photos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ed.maevski.testbalinasoft.databinding.ItemImageBinding
import ed.maevski.testbalinasoft.domain.models.Image
import ed.maevski.testbalinasoft.utils.toTextDateByFormat

class ImageAdapter(
    private val onImgClick: (id: Int, type: String) -> Unit,
) : RecyclerView.Adapter<ImageAdapter.InnerImageViewHolder>() {
    private var images: MutableList<Image> = mutableListOf()

    inner class InnerImageViewHolder(binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var root = binding.itemContainer
        var image = binding.image
        var date = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return InnerImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerImageViewHolder, position: Int) {

        Glide.with(holder.root)
            .load(images[position].uri ?: images[position].url)
            .centerCrop()
            .into(holder.image)

        holder.date.text = images[position].date.toTextDateByFormat("yyyy-MM-dd")

        holder.image.setOnClickListener {
            println("ImageAdapter onImgClick id =  ${images[position].id}")
            images[position].id?.let { id -> onImgClick(id, CLICK) }
        }
        holder.image.setOnLongClickListener {
            println("ImageAdapter onImgLongClick id =  ${images[position].id}")
            images[position].id?.let { id -> onImgClick(id, LONGCLICK) }
            true
        }
    }

    override fun getItemCount(): Int = images.size

//    fun getItem(position: Int?): Image? {
//        return if (position == null) null
//        else images[position]
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(images: List<Image>) {
        this.images = images.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeImageById(id: Int){
        val position = images.indexOfFirst { it.id == id }
        if (position != -1) {
            images.removeAt(position)
            notifyDataSetChanged()
        }
    }

    companion object {
        private const val CLICK = "click"
        private const val LONGCLICK = "longclick"
    }
}