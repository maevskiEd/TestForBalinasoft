package ed.maevski.testbalinasoft.view.photos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.android.support.AndroidSupportInjection
import ed.maevski.testbalinasoft.R
import ed.maevski.testbalinasoft.databinding.FragmentPhotosBinding
import ed.maevski.testbalinasoft.domain.models.Image
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosFragment : Fragment() {
    private val imageAdapter = ImageAdapter() { uri ->

        val bundle = Bundle()
        bundle.putString("file_uri", uri)
        findNavController().navigate(R.id.imageDetailFragment, bundle)

    }

    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PhotosViewModel

    @Inject
    lateinit var vmFactory: PhotosViewModel.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        println("onCreateView PhotosFragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated PhotosFragment")

        viewModel =
            ViewModelProvider(this, vmFactory)[PhotosViewModel::class.java]

        binding.rvImages.adapter = imageAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.images.collect {

                println("images collect $it")
                initRV(it)
            }
        }
        viewModel.getImages()
    }

    private fun initRV(data: List<Image>) {
        imageAdapter.setData(data)
    }

    fun updateRV() {
        println("updateRV")
        viewModel.getImages()
    }
}