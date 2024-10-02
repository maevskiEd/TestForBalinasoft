package ed.maevski.testbalinasoft.view.imagedetail

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.databinding.FragmentImageDetailBinding
import ed.maevski.testbalinasoft.domain.models.Comment
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailFragment : Fragment() {
    private val commentAdapter = CommentAdapter() { idComment ->
        showDialog(idComment)
    }

    private var _binding: FragmentImageDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ImageDetailViewModel

    @Inject
    lateinit var vmFactory: ImageDetailViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id_image = arguments?.getInt("id_image")

        println("ImageDetailFragment id_image bundle $id_image")

        viewModel =
            ViewModelProvider(this, vmFactory)[ImageDetailViewModel::class.java]

        binding.rvComments.adapter = commentAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.image.collect {

                println("image collect $it")

                if (it.uri != null) binding.imgPhoto.setImageURI(it.uri)
                else {
                    Glide.with(binding.root)
                        .load(it.url)
                        .centerCrop()
                        .into(binding.imgPhoto)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.comments.collect {

                println("comments collect $it")
                initRV(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.idComment.collect {

                println("removeCommentById $it")

                commentAdapter.removeCommentById(it)
            }
        }

        if (id_image != null) {
            viewModel.imageId = id_image
            viewModel.getImageById(id_image)
        }

        binding.btnSend.setOnClickListener {
            if (id_image != null) {
                val comment = Comment(
                    imageId = id_image,
                    date = System.currentTimeMillis(),
                    text = binding.etComment.text.toString()
                )
                viewModel.sendComment(comment)
                binding.etComment.setText("")
            }
        }
    }

    private fun initRV(data: List<Comment>) {
        commentAdapter.setData(data)
    }

    private fun showDialog(commentId: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Удалить комментарий:")
        builder.setMessage("Вы уверены?")

        builder.setPositiveButton("Да") { dialog, which ->
            viewModel.delComment(imageId = viewModel.imageId, commentId = commentId)
        }

        builder.setNegativeButton("Нет") { dialog, which ->
            // Действие при нажатии "Нет"
        }

        builder.show()
    }
}