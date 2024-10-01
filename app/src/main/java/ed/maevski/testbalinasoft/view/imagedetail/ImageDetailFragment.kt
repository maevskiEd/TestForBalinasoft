package ed.maevski.testbalinasoft.view.imagedetail

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
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailFragment : Fragment() {
    private val commentAdapter = CommentAdapter() { idImage, idComment ->

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

        if (id_image != null) {
            viewModel.getImageById(id_image)
        }
    }
}