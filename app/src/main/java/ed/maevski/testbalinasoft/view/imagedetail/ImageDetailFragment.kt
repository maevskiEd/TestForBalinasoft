package ed.maevski.testbalinasoft.view.imagedetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.databinding.FragmentImageDetailBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageDetailFragment : Fragment() {
//    private val commentAdapter = CommentAdapter() { uri ->
//
//        val bundle = Bundle()
//        bundle.putString("file_uri", uri)
//        findNavController().navigate(R.id.imageDetailFragment, bundle)
//
//    }

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
        val id_image = arguments?.getLong("id_image")

        println("ImageDetailFragment id_image bundle $id_image")

        viewModel =
            ViewModelProvider(this, vmFactory)[ImageDetailViewModel::class.java]

//        binding.rvImages.adapter = imageAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.image.collect {

                println("image collect $it")

                binding.imgPhoto.setImageURI(it.uri)
            }
        }

        if (id_image != null) {
            viewModel.getImageById(id_image)
        }
    }
}