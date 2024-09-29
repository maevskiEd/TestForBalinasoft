package ed.maevski.testbalinasoft.view.imagedetail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.FragmentImageDetailBinding
import ed.maevski.testbalinasoft.view.photos.ImageAdapter

class ImageDetailFragment : Fragment() {
    private val commentAdapter = CommentAdapter() { uri ->

        val bundle = Bundle()
        bundle.putString("file_uri", uri)
        findNavController().navigate(R.id.imageDetailFragment, bundle)

    }

    private var _binding: FragmentImageDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ImageDetailViewModel

//    @Inject
//    lateinit var vmFactory: AuthViewModel.Factory

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
        val uriString = arguments?.getString("file_uri")
        val imageUri: Uri? = uriString?.let { Uri.parse(it) }

        imageUri?.let { binding.imgPhoto.setImageURI(it) }

    }
}